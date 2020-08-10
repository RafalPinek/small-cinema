package com.fourthwall.smallcinema.movie.rating;

import org.springframework.stereotype.Component;

@Component
class SimpleRatingValidator implements RatingValidator {

    public void validate(Integer rate) throws IllegalArgumentException {
        if (rate == null) {
            throw new IllegalArgumentException("Movie rate may not be null.");
        }
        if (rate < 1 || rate > 5) {
            throw new IllegalArgumentException("Movie rate must be between 1 and 5.");
        }
    }

}
