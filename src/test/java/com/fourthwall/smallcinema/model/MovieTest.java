package com.fourthwall.smallcinema.model;

import com.fourthwall.smallcinema.movie.model.Movie;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

public class MovieTest {

    private static final Offset<Double> ACCEPTED_RATING_OFFSET = Offset.offset(0.01);

    @Test
    public void shouldCalculateRateOnce() {
        // given
        Movie movie = new Movie("someImdbId", "some title");

        // when
        movie.rate(1);

        // then
        double rating = movie.getRating();
        Assertions.assertThat(rating).isEqualTo(1.0, ACCEPTED_RATING_OFFSET);
    }

    @Test
    public void shouldCalculateRateMultipleTimes() {
        // given
        Movie movie = new Movie("someImdbId", "some title");

        // when
        movie.rate(1);
        movie.rate(3);
        movie.rate(4);

        // then
        double rating = movie.getRating();
        Assertions.assertThat(rating).isEqualTo(2.67, ACCEPTED_RATING_OFFSET);
    }
}
