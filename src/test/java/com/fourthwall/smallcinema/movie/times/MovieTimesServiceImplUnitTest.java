package com.fourthwall.smallcinema.movie.times;

import com.fourthwall.smallcinema.movie.dao.movie.AbstractMovieDao;
import com.fourthwall.smallcinema.movie.dao.showtime.AbstractShowTimeDao;
import com.fourthwall.smallcinema.movie.model.Movie;
import com.fourthwall.smallcinema.movie.model.ShowTime;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieTimesServiceImplUnitTest {

    private static final Long MOVIE_ID = 1L;

    private AbstractMovieDao movieDao = mock(AbstractMovieDao.class);

    private AbstractShowTimeDao showTimeDao = mock(AbstractShowTimeDao.class);

    private MovieTimesService<MovieTimesView> service = new MovieTimesServiceImpl(movieDao, showTimeDao);

    @Test
    public void shouldGetMovieTimesByDate() {
        // given
        final String imdbId = "some imdb id";
        final String title = "some title";
        Movie movie = new Movie(imdbId, title);
        when(movieDao.findById(MOVIE_ID)).thenReturn(Optional.of(movie));
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        when(showTimeDao.getByMovieIdAndDateWithin(MOVIE_ID, localDate)).thenReturn(prepareShowTimes(localTime));

        // when
        MovieTimesView movieTimesView = service.getShowTimesByMovieIdAndDate(MOVIE_ID, localDate);

        // then
        assertThat(movieTimesView.getMovieId()).isEqualTo(MOVIE_ID);
        assertThat(movieTimesView.getTitle()).isEqualTo(title);
        assertThat(movieTimesView.getTimes()).hasSize(10);
        assertThat(movieTimesView.getTimes()).contains(localTime);
    }

    @Test
    public void shouldGetAllMovieTimes() {
        // given
        final Long id1 = 1L;
        final Long id2 = 2L;
        final String title1 = "title1";
        final String title2 = "title2";
        Movie movie1 = mock(Movie.class);
        Movie movie2 = mock(Movie.class);
        prepareMock(movie1, id1, title1);
        prepareMock(movie2, id2, title2);
        when(movieDao.findAll()).thenReturn(Arrays.asList(movie1, movie2));
        LocalDate localDate = LocalDate.now();
        Set<ShowTime> showTimes = prepareShowTimes(LocalTime.now());
        when(showTimeDao.getByMovieIdAndDateWithin(id1, localDate)).thenReturn(showTimes);
        when(showTimeDao.getByMovieIdAndDateWithin(id2, localDate)).thenReturn(showTimes);

        // when
        Set<MovieTimesView> movieTimesViews = service.getMovieTimes(localDate);

        // then
        assertThat(movieTimesViews).hasSize(2);
        movieTimesViews.forEach(view -> assertThat(view.getTimes()).hasSize(10));
        checkTitlesAndIds(movieTimesViews, id1, id2, title1, title2);
    }

    private Set<ShowTime> prepareShowTimes(LocalTime firstLocalTime) {
        Set<ShowTime> showTimes = new HashSet<>();
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < 10; i++) {
            LocalTime localTime = firstLocalTime.plus(i, ChronoUnit.HOURS);
            showTimes.add(new ShowTime(MOVIE_ID, BigDecimal.ONE, LocalDateTime.of(localDate, localTime)));
        }
        return showTimes;
    }

    private void prepareMock(Movie movieMock, Long id, String title) {
        when(movieMock.getId()).thenReturn(id);
        when(movieMock.getTitle()).thenReturn(title);
        when(movieDao.findById(id)).thenReturn(Optional.of(movieMock));
    }

    private void checkTitlesAndIds(Set<MovieTimesView> movieTimesViews, Long id1, Long id2, String title1, String title2) {
        assertThat(movieTimesViews.stream()
                .map(MovieTimesView::getMovieId)
                .anyMatch(id1::equals)).isTrue();
        assertThat(movieTimesViews.stream()
                .map(MovieTimesView::getMovieId)
                .anyMatch(id2::equals)).isTrue();
        assertThat(movieTimesViews.stream()
                .map(MovieTimesView::getTitle)
                .anyMatch(title1::equals)).isTrue();
        assertThat(movieTimesViews.stream()
                .map(MovieTimesView::getTitle)
                .anyMatch(title2::equals)).isTrue();
    }
}
