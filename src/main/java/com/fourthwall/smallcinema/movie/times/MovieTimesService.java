package com.fourthwall.smallcinema.movie.times;

import java.time.LocalDate;
import java.util.SortedSet;

interface MovieTimesService<T extends Comparable<T>> {

    SortedSet<T> getMovieTimes(LocalDate date);

    T getShowTimesByMovieIdAndDate(Long movieId, LocalDate date);
}
