package com.fourthwall.smallcinema.movie.rating;

class RatingValidator {

    void validate(Integer rate) {
        if (rate == null) {
            throw new IllegalArgumentException("Movie rate may not be null.");
        }
        if (rate < 1 || rate > 5) {
            throw new IllegalArgumentException("Movie rate must be between 1 and 5.");
        }
    }

}
