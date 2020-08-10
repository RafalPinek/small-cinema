package com.fourthwall.smallcinema.movie.rating;

interface RatingService {

    /**
     * Rates given movie.
     *
     * @param movieId movie id to be rated
     * @param rate rate - can be between 1 and 5
     */
    void rate(Long movieId, Integer rate);

}
