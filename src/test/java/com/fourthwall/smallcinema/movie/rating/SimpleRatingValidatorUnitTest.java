package com.fourthwall.smallcinema.movie.rating;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleRatingValidatorUnitTest {

    private final RatingValidator validator = new SimpleRatingValidator();

    @Test
    public void shouldRevokeNullRate() {
        // when
        // then
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(null));

        assertThat(thrown.getMessage()).containsIgnoringCase("Movie rate");
    }

    @Test
    public void shouldRevokeNegativeRate() {
        // when
        // then
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(-4));

        assertThat(thrown.getMessage()).containsIgnoringCase("Movie rate");
    }

    @Test
    public void shouldRevokeZeroRate() {
        // when
        // then
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(0));

        assertThat(thrown.getMessage()).containsIgnoringCase("Movie rate");
    }

    @Test
    public void shouldRevokeRateOutsideRange() {
        // when
        // then
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(10));

        assertThat(thrown.getMessage()).containsIgnoringCase("Movie rate");
    }
}
