package fr.decathlon.moviesdb.external.sources;

import fr.decathlon.moviesdb.exception.MoviesDBException;

public class ExternalSourceQueryException extends MoviesDBException {
    public ExternalSourceQueryException(String message) {
        super(message);
    }

    public ExternalSourceQueryException(String message, Throwable cause) {
        super(message, cause);
    }
}
