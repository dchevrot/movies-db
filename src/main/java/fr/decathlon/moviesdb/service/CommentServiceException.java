package fr.decathlon.moviesdb.service;

import fr.decathlon.moviesdb.exception.MoviesDBException;

public class CommentServiceException extends MoviesDBException {
    public CommentServiceException(String message) {
        super(message);
    }

    public CommentServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
