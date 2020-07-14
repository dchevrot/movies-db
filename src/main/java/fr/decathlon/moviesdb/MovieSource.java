package fr.decathlon.moviesdb;

import fr.decathlon.moviesdb.domain.Movie;

import java.util.Optional;

public interface MovieSource {

    Optional<Movie> findByTitle(String title);
}
