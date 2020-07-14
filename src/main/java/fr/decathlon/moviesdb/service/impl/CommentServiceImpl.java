package fr.decathlon.moviesdb.service.impl;

import fr.decathlon.moviesdb.service.CommentOnUnexistingMovie;
import fr.decathlon.moviesdb.service.CommentService;
import fr.decathlon.moviesdb.domain.Comment;
import fr.decathlon.moviesdb.repository.CommentRepository;
import fr.decathlon.moviesdb.service.MovieService;
import fr.decathlon.moviesdb.service.dto.CommentDTO;
import fr.decathlon.moviesdb.service.dto.MovieDTO;
import fr.decathlon.moviesdb.service.dto.TopDTO;
import fr.decathlon.moviesdb.service.mapper.CommentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Comment}.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    private final MovieService movieService;


    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, MovieService movieService) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.movieService = movieService;
    }

    @Override
    public CommentDTO save(CommentDTO commentDTO) {
        log.debug("Request to save Comment : {}", commentDTO);
        Optional<MovieDTO> movie = movieService.findOne(commentDTO.getMovieId());
        movie.orElseThrow(() -> new CommentOnUnexistingMovie("movie id " + commentDTO.getMovieId() + " does not exist"));

        Comment comment = commentMapper.toEntity(commentDTO);
        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CommentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Comments");
        return commentRepository.findAll(pageable)
            .map(commentMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CommentDTO> findOne(Long id) {
        log.debug("Request to get Comment : {}", id);
        return commentRepository.findById(id)
            .map(commentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Comment : {}", id);
        commentRepository.deleteById(id);
    }

    @Override
    public Page<TopDTO> getTop(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        log.debug("Request top commented movies");
        return commentRepository.top(startDate, endDate, pageable);
    }
}
