package uk.sky.vod.movie.metadata.service;

import uk.sky.vod.movie.metadata.exception.TechnicalFailureException;
import uk.sky.vod.movie.metadata.exception.TitleNotFoundException;

public interface MovieService {

    String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException;
}
