package com.fourthwall.smallcinema.movie.times;

import com.fourthwall.smallcinema.movie.dao.movie.AbstractMovieDao;
import com.fourthwall.smallcinema.movie.dao.showtime.AbstractShowTimeDao;
import com.fourthwall.smallcinema.movie.initialize.MoviesGenerator;
import com.fourthwall.smallcinema.movie.initialize.ShowTimesGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MovieTimesServiceImplIT {

    @Autowired
    private AbstractMovieDao movieDao;

    @Autowired
    private AbstractShowTimeDao showTimeDao;

    @Test
    public void shouldGetCorrectAmountOfMovieTimes() {
        // given
        MovieTimesService<MovieTimesView> movieTimesService = new MovieTimesServiceImpl(movieDao, showTimeDao);
        LocalDate localDate = LocalDate.of(2020, 8, 11);

        // when
        Set<MovieTimesView> movieTimesViews = movieTimesService.getMovieTimes(localDate);

        // then
        assertThat(movieTimesViews).hasSize(MoviesGenerator.GENERATED_MOVIES_COUNT);
        movieTimesViews.forEach(view -> assertThat(view.getTimes()).hasSize(ShowTimesGenerator.NUMBER_OF_TIMES));
    }

}
