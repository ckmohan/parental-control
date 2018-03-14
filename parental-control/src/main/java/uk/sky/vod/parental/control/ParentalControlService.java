package uk.sky.vod.parental.control;


import static java.util.Objects.isNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import uk.sky.vod.movie.metadata.exception.TechnicalFailureException;
import uk.sky.vod.movie.metadata.exception.TitleNotFoundException;
import uk.sky.vod.movie.metadata.service.MovieService;


public class ParentalControlService {

    private static final Logger LOGGER = Logger.getLogger(ParentalControlService.class.getName());

    private MovieService movieService;

    public boolean isAccessible(final ParentalControlLevel preferredControlLevel,
                                final String movieId) throws TitleNotFoundException {

        if (isNull(preferredControlLevel) || isNull(movieId)) {
            return false;
        }

        try {

            final String levelForMovie = movieService.getParentalControlLevel(movieId);
            final ParentalControlLevel parentalControlLevel = ParentalControlLevel.fromCode(levelForMovie);
            return parentalControlLevel.getMinAge() <= preferredControlLevel.getMinAge();

        } catch (TechnicalFailureException tfe) {
            LOGGER.log(Level.WARNING, "Error getting parental control level for movieId : " + movieId, tfe);
            return false;
        }
    }
}
