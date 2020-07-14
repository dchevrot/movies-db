package fr.decathlon.moviesdb.exception;

/**
 * Parent class for all exceptions thrown by the project
 */
public class MoviesDBException extends RuntimeException {

    public MoviesDBException(String message) {
        super(message);
    }

    public MoviesDBException(String message, Throwable cause) {
        super(message, cause);
    }
}
