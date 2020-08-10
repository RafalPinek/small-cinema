package com.fourthwall.smallcinema.movie.times;

import java.time.LocalDate;
import java.util.SortedSet;

interface MovieTimesService<T extends Comparable<T>> {

    /**
     * Gets movies show times for a given day.
     *
     * @param date for which day you want to get movies show times
     * @return sorted set of show times
     */
    SortedSet<T> getMovieTimes(LocalDate date);

    /**
     * Gets show times for given day, for a specific movie.
     *
     * @param movieId movie id
     * @param date for which day you want to get movie show times
     * @return information about movie title and its show times
     */
    T getShowTimesByMovieIdAndDate(Long movieId, LocalDate date);
}
