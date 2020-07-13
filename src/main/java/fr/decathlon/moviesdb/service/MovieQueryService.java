package fr.decathlon.moviesdb.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import fr.decathlon.moviesdb.domain.Movie;
import fr.decathlon.moviesdb.domain.*; // for static metamodels
import fr.decathlon.moviesdb.repository.MovieRepository;
import fr.decathlon.moviesdb.service.dto.MovieCriteria;
import fr.decathlon.moviesdb.service.dto.MovieDTO;
import fr.decathlon.moviesdb.service.mapper.MovieMapper;

/**
 * Service for executing complex queries for {@link Movie} entities in the database.
 * The main input is a {@link MovieCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MovieDTO} or a {@link Page} of {@link MovieDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MovieQueryService extends QueryService<Movie> {

    private final Logger log = LoggerFactory.getLogger(MovieQueryService.class);

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;

    public MovieQueryService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    /**
     * Return a {@link List} of {@link MovieDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MovieDTO> findByCriteria(MovieCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Movie> specification = createSpecification(criteria);
        return movieMapper.toDto(movieRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MovieDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MovieDTO> findByCriteria(MovieCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Movie> specification = createSpecification(criteria);
        return movieRepository.findAll(specification, page)
            .map(movieMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MovieCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Movie> specification = createSpecification(criteria);
        return movieRepository.count(specification);
    }

    /**
     * Function to convert {@link MovieCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Movie> createSpecification(MovieCriteria criteria) {
        Specification<Movie> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Movie_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Movie_.title));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), Movie_.year));
            }
            if (criteria.getRate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRate(), Movie_.rate));
            }
            if (criteria.getReleaseDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReleaseDate(), Movie_.releaseDate));
            }
            if (criteria.getRuntime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRuntime(), Movie_.runtime));
            }
            if (criteria.getGenre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGenre(), Movie_.genre));
            }
            if (criteria.getDirector() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDirector(), Movie_.director));
            }
            if (criteria.getWriters() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWriters(), Movie_.writers));
            }
            if (criteria.getActors() != null) {
                specification = specification.and(buildStringSpecification(criteria.getActors(), Movie_.actors));
            }
            if (criteria.getPlot() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPlot(), Movie_.plot));
            }
            if (criteria.getLanguage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLanguage(), Movie_.language));
            }
            if (criteria.getCountry() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCountry(), Movie_.country));
            }
            if (criteria.getAwards() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAwards(), Movie_.awards));
            }
            if (criteria.getPosterLink() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPosterLink(), Movie_.posterLink));
            }
            if (criteria.getRatings() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRatings(), Movie_.ratings));
            }
            if (criteria.getMetascore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMetascore(), Movie_.metascore));
            }
            if (criteria.getImdbRating() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getImdbRating(), Movie_.imdbRating));
            }
            if (criteria.getImdbVotes() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getImdbVotes(), Movie_.imdbVotes));
            }
            if (criteria.getImdbId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImdbId(), Movie_.imdbId));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), Movie_.type));
            }
            if (criteria.getDvdReleaseDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDvdReleaseDate(), Movie_.dvdReleaseDate));
            }
            if (criteria.getBoxOffice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBoxOffice(), Movie_.boxOffice));
            }
        }
        return specification;
    }
}
