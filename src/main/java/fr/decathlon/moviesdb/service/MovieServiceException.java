package fr.decathlon.moviesdb.service;

import fr.decathlon.moviesdb.exception.MoviesDBException;

public class MovieServiceException extends MoviesDBException {
    public MovieServiceException(String message) {
        super(message);
    }

    public MovieServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
