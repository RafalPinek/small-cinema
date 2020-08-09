package com.fourthwall.smallcinema.movie.details;

interface MovieDetailsService<T> {

    T getMovieDetails(Long movieId);
}
