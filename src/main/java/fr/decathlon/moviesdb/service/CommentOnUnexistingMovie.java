package fr.decathlon.moviesdb.service;

public class CommentOnUnexistingMovie extends CommentServiceException {
    public CommentOnUnexistingMovie(String message) {
        super(message);
    }

    public CommentOnUnexistingMovie(String message, Throwable cause) {
        super(message, cause);
    }
}
