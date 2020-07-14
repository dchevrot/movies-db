package fr.decathlon.moviesdb.service.impl;

import fr.decathlon.moviesdb.MovieSource;
import fr.decathlon.moviesdb.domain.Movie;
import fr.decathlon.moviesdb.exception.MoviesDBException;
import fr.decathlon.moviesdb.repository.MovieRepository;
import fr.decathlon.moviesdb.service.MovieService;
import fr.decathlon.moviesdb.service.UnexistingMovieException;
import fr.decathlon.moviesdb.service.dto.MovieCreationDTO;
import fr.decathlon.moviesdb.service.dto.MovieDTO;
import fr.decathlon.moviesdb.service.mapper.MovieMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Movie}.
 */
@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieRepository movieRepository;
    private final List<MovieSource> externalMovieSources;
    private final MovieMapper movieMapper;

    public MovieServiceImpl(MovieRepository movieRepository, List<MovieSource> externalMovieSources, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.externalMovieSources = externalMovieSources;
        this.movieMapper = movieMapper;
    }

    @Override
    public MovieDTO save(MovieCreationDTO movieCreationDTO) {
        log.debug("Request to save Movie : {}", movieCreationDTO);

        if (movieCreationDTO == null) {
            throw new IllegalArgumentException("movie to be create must not be null");
        }
        if (StringUtils.isEmpty(movieCreationDTO.getTitle())) {
            throw new IllegalArgumentException("title must not be empty");
        }

        Optional<Movie> movie;
        for (MovieSource externalMovieSource : externalMovieSources) {
            movie = externalMovieSource.findByTitle(movieCreationDTO.getTitle());
            if (movie.isPresent()) {
                try {
                    return movieMapper.toDto(movieRepository.save(movie.get()));
                } catch (DataAccessException e) {
                    log.error("Impossible to add the new movie {} in database. exception : {}", movieCreationDTO.getTitle(), e);
                }
            }
        }

        throw new UnexistingMovieException("Movie with title :'" + movieCreationDTO.getTitle() + "' does not exist");
    }

    @Override
    public MovieDTO update(MovieDTO movieDTO) {
        log.debug("Request to save Movie : {}", movieDTO);
        Movie movie = movieMapper.toEntity(movieDTO);
        movie = movieRepository.save(movie);
        return movieMapper.toDto(movie);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MovieDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Movies");
        return movieRepository.findAll(pageable)
            .map(movieMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MovieDTO> findOne(Long id) {
        log.debug("Request to get Movie : {}", id);
        try {
            return movieRepository.findById(id)
                .map(movieMapper::toDto);
        } catch (DataAccessException e) {
            throw new MoviesDBException("error retrieving movie with id " + id);
        }
    }

    @Override
    public Optional<MovieDTO> findByTitle(String title) {
        log.debug("Request to get Movie with title : {}", title);
        Optional<Movie> movie;
        try {
            movie = movieRepository.findByTitle(title);
        } catch (DataAccessException e) {
            throw new MoviesDBException("error retrieving movie with title" + title);
        }
        if (movie.isPresent()) {
            return movie.map(movieMapper::toDto);
        }


        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Movie : {}", id);
        movieRepository.deleteById(id);
    }
}
