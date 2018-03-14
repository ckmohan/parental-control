package uk.sky.vod.movie.metadata.exception;

public class TechnicalFailureException extends Exception {

    public TechnicalFailureException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
