package com.fourthwall.smallcinema.movie.times;

import com.fourthwall.smallcinema.movie.dao.MovieDao;
import com.fourthwall.smallcinema.movie.dao.ShowTimeDao;
import com.fourthwall.smallcinema.movie.model.Movie;
import com.fourthwall.smallcinema.movie.model.ShowTime;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
class MovieTimesService {

    private final MovieDao movieDao;

    private final ShowTimeDao showTimeDao;

    MovieTimesService(MovieDao movieDao, ShowTimeDao showTimeDao) {
        this.movieDao = movieDao;
        this.showTimeDao = showTimeDao;
    }

    SortedSet<MovieTimesView> getMovieTimes(LocalDate date) {
        return ((Collection<Movie>) movieDao.findAll()).stream()
                .map(movie -> getShowTimesByMovieIdAndDate(movie.getId(), date))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    MovieTimesView getShowTimesByMovieIdAndDate(Long movieId, LocalDate date) {
        Set<ShowTime> showTimes = showTimeDao.getByMovieIdAndDateWithin(movieId, date);
        String title = movieDao.findById(movieId)
                .map(Movie::getTitle)
                .orElseThrow(() -> new IllegalArgumentException("Movie with given id: " + movieId + " is not present."));
        SortedSet<LocalTime> times = showTimes.stream()
                .map(showTime -> showTime.getDateTime().toLocalTime())
                .collect(Collectors.toCollection(TreeSet::new));

        return new MovieTimesView(movieId, title, times);
    }

}
