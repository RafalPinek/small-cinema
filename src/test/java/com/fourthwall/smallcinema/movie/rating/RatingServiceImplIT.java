package com.fourthwall.smallcinema.movie.rating;

import com.fourthwall.smallcinema.movie.dao.movie.AbstractMovieDao;
import com.fourthwall.smallcinema.movie.model.Movie;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RatingServiceImplIT {

    private static final Offset<Double> ACCEPTED_RATING_OFFSET = Offset.offset(0.01);

    @Autowired
    private AbstractMovieDao movieDao;

    @Test
    public void shouldSetRateOnce() {
        // given
        RatingService ratingService = new RatingServiceImpl(movieDao, new SimpleRatingValidator());
        Movie movie = movieDao.findAll().iterator().next();
        final int exampleRate = 3;
        double previousRate = movie.getRating();
        assertThat(previousRate).isEqualTo(0.0, ACCEPTED_RATING_OFFSET);

        // when
        ratingService.rate(movie.getId(), exampleRate);

        // then
        double newRate = movieDao.findById(movie.getId())
                .orElseThrow(() -> new IllegalStateException("Movie should be persisted."))
                .getRating();
        assertThat(newRate).isEqualTo(exampleRate, ACCEPTED_RATING_OFFSET);
    }

}
