package com.fourthwall.smallcinema.movie.details;

interface MovieDetailsService<T> {

    /**
     * Gets details of a specific movie.
     *
     * @param movieId - movie id
     * @return movie details
     */
    T getMovieDetails(Long movieId);
}
