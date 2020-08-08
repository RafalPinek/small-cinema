package com.fourthwall.smallcinema.movie.times;

import java.time.LocalTime;
import java.util.Objects;
import java.util.SortedSet;

class MovieTimesView implements Comparable<MovieTimesView> {

    private final Long movieId;
    private final String title;
    private final SortedSet<LocalTime> times;

    MovieTimesView(Long movieId, String title, SortedSet<LocalTime> times) {
        this.movieId = movieId;
        this.title = title;
        this.times = times;
    }

    Long getMovieId() {
        return movieId;
    }

    String getTitle() {
        return title;
    }

    SortedSet<LocalTime> getTimes() {
        return times;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieTimesView)) return false;
        MovieTimesView that = (MovieTimesView) o;
        return getMovieId().equals(that.getMovieId()) &&
                getTitle().equals(that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieId(), getTitle());
    }

    @Override
    public int compareTo(MovieTimesView movieView) {
        return this.title.compareTo(movieView.title);
    }
}
