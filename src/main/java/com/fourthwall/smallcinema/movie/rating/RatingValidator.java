package com.fourthwall.smallcinema.movie.rating;

interface RatingValidator {

    /**
     * Validates if given rate is valid
     *
     * @param rate rate to be validated
     * @throws IllegalArgumentException if given rate is invalid
     */
    void validate(Integer rate) throws IllegalArgumentException;

}
