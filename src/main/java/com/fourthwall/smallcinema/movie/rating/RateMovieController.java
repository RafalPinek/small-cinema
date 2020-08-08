package com.fourthwall.smallcinema.movie.rating;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RateMovieController {

    private final RatingService ratingService;

    RateMovieController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping(value = "/movies/{id}/rate")
    void rateMovie(@PathVariable("id") Long id, @RequestBody Integer rating) {
        ratingService.rate(id, rating);
    }
}
