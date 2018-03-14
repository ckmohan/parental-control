package uk.sky.vod.parental.control;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ParentalControlLevelTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldReturn_18_ForCode() {
        final ParentalControlLevel level = ParentalControlLevel.fromCode(ParentalControlLevel.EIGHTEEN.getCode());

        assertThat(level, is(ParentalControlLevel.EIGHTEEN));
    }

    @Test
    public void shouldReturnExceptionForInvalidCode() {

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalid code");

        ParentalControlLevel.fromCode("UPG");
    }
}