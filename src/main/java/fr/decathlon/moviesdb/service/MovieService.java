package fr.decathlon.moviesdb.service;

import fr.decathlon.moviesdb.service.dto.MovieCreationDTO;
import fr.decathlon.moviesdb.service.dto.MovieDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link fr.decathlon.moviesdb.domain.Movie}.
 */
public interface MovieService {

    /**
     * Save a movie.
     *
     * @param movieCreationDTO the entity to save.
     * @return the persisted entity.
     */
    MovieDTO save(MovieCreationDTO movieCreationDTO);

    /**
     * update a movie.
     *
     * @param movieDTO the entity to update.
     * @return the persisted entity.
     */
    MovieDTO update(MovieDTO movieDTO);

    /**
     * Get all the movies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MovieDTO> findAll(Pageable pageable);


    /**
     * Get the "id" movie.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MovieDTO> findOne(Long id);

    /**
     * Get the movie by title
     *
     * @param title of the entity.
     * @return the entity.
     */
    Optional<MovieDTO> findByTitle(String title);

    /**
     * Delete the "id" movie.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
