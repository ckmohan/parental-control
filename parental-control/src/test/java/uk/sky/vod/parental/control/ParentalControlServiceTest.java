package uk.sky.vod.parental.control;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.sky.vod.movie.metadata.exception.TechnicalFailureException;
import uk.sky.vod.movie.metadata.exception.TitleNotFoundException;
import uk.sky.vod.movie.metadata.service.MovieService;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceTest {

    @InjectMocks
    private ParentalControlService parentalControlService;

    @Mock
    private MovieService movieService;

    @Test
    public void shouldReturnFalseWhenPreferredParentalLevel_UAndParentalControlLevel_15() throws Exception {
        final String movieId = "movieIdForU";
        given(movieService.getParentalControlLevel(movieId)).willReturn(ParentalControlLevel.FIFTEEN.getCode());

        final boolean isAccessible = parentalControlService.isAccessible(ParentalControlLevel.U, movieId);

        assertThat(isAccessible, is(false));
    }

    @Test
    public void shouldReturnFalseWhenMoreThanPreferredParentalLevel_UAndMovieParentalControlLevel_12() throws Exception {

        final String movieId = "movieIdForU";

        given(movieService.getParentalControlLevel(movieId)).willReturn(ParentalControlLevel.TWELVE.getCode());

        final boolean isAccessible = parentalControlService.isAccessible(ParentalControlLevel.U, movieId);

        assertThat(isAccessible, is(false));
    }

    @Test
    public void shouldReturnTrueWhenPreferredControlLevelIsSameAsTheParentalControlLevel() throws Exception {
        final String movieId = "movieIdParentalControlMatchCase";
        given(movieService.getParentalControlLevel(movieId)).willReturn(ParentalControlLevel.PG.getCode());

        final boolean isAccessible = parentalControlService.isAccessible(ParentalControlLevel.PG, movieId);

        assertThat(isAccessible, is(true));
    }

    @Test
    public void shouldReturnTrueWhenPreferredControlLevelIs_PG_AndParentalControlLevel_U() throws Exception {

        final String movieId = "movieId_PG";

        given(movieService.getParentalControlLevel(movieId)).willReturn(ParentalControlLevel.U.getCode());

        final boolean isAccessible = parentalControlService.isAccessible(ParentalControlLevel.PG, movieId);

        assertThat(isAccessible, is(true));
    }

    @Test
    public void shouldReturnFalseWhenPreferredParentalLevel_15AndParentalControlLevel_18() throws Exception {
        final String movieId = "movieIdFor15";
        given(movieService.getParentalControlLevel(movieId)).willReturn(ParentalControlLevel.EIGHTEEN.getCode());

        final boolean isAccessible = parentalControlService.isAccessible(ParentalControlLevel.FIFTEEN, movieId);

        assertThat(isAccessible, is(false));
    }


    @Test
    public void shouldReturnTrueWhenPreferredParentalLevel_18AndParentalControlLevel_PG() throws Exception {
        final String movieId = "movieIdFor18";
        given(movieService.getParentalControlLevel(movieId)).willReturn(ParentalControlLevel.PG.getCode());

        final boolean isAccessible = parentalControlService.isAccessible(ParentalControlLevel.EIGHTEEN, movieId);

        assertThat(isAccessible, is(true));
    }

    @Test
    public void shouldReturnTrueWhenPreferredParentalLevel_18AndParentalControlLevel_12() throws Exception {
        final String movieId = "movieIdFor18";
        given(movieService.getParentalControlLevel(movieId)).willReturn(ParentalControlLevel.TWELVE.getCode());

        final boolean isAccessible = parentalControlService.isAccessible(ParentalControlLevel.EIGHTEEN, movieId);

        assertThat(isAccessible, is(true));
    }

    @Test
    public void shouldReturnFalseWhenPreferredControlLevelParamIsNull() throws Exception {

        final boolean isAccessible = parentalControlService.isAccessible(null, "idNotFound");

        assertThat(isAccessible, is(false));
    }

    @Test
    public void shouldReturnFalseWhenMovieIdParamIsNull() throws Exception {

        final boolean isAccessible = parentalControlService.isAccessible(ParentalControlLevel.PG, null);

        assertThat(isAccessible, is(false));
    }

    @Test(expected = TitleNotFoundException.class)
    public void shouldThrowTitleNotFoundExceptionWhenAccessingMovieThatIsNotExisting() throws Exception {
        final String movieId = "movieIdNotFound";
        given(movieService.getParentalControlLevel(movieId)).willThrow(TitleNotFoundException.class);

        parentalControlService.isAccessible(ParentalControlLevel.PG, movieId);

        fail("TitleNotFoundException");
    }

    @Test
    public void shouldReturnFalseWhenThereIsATechnicalFailureCallingMovieService() throws Exception {
        final String movieId = "movieIdTechFail";
        given(movieService.getParentalControlLevel(movieId)).willThrow(TechnicalFailureException.class);

        final boolean isAccessible = parentalControlService.isAccessible(ParentalControlLevel.PG, movieId);

        assertThat(isAccessible, is(false));
    }

}