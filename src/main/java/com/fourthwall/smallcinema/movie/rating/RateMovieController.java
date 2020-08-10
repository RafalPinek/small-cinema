package com.fourthwall.smallcinema.movie.rating;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Rates given movie.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully rated movie"),
            @ApiResponse(code = 400, message = "The provided parameters are invalid"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @PostMapping(value = "/movies/{id}/rate")
    void rateMovie(@ApiParam("movie id") @PathVariable("id") Long id,
                   @ApiParam("rating, range is 1 - 5") @RequestBody Integer rating) {
        ratingService.rate(id, rating);
    }
}
