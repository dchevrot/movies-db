package fr.decathlon.moviesdb.repository;

import fr.decathlon.moviesdb.MovieSource;
import fr.decathlon.moviesdb.domain.Movie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Movie entity.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie>, MovieSource {

}
