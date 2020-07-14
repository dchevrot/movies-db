package fr.decathlon.moviesdb.service;

public class UnexistingMovieException extends MovieServiceException {
    public UnexistingMovieException(String message) {
        super(message);
    }

    public UnexistingMovieException(String message, Throwable cause) {
        super(message, cause);
    }
}
