package com.fourthwall.smallcinema.movie.times;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.SortedSet;

@RestController
class GetMovieTimesController {

    private final MovieTimesService<MovieTimesView> movieTimesService;

    GetMovieTimesController(MovieTimesService<MovieTimesView> movieTimesService) {
        this.movieTimesService = movieTimesService;
    }

    @ApiOperation(value = "Gets all movies show times for a given date")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned all movies show times"),
            @ApiResponse(code = 400, message = "The provided parameters are invalid"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @GetMapping(value = "/movie-times")
    SortedSet<MovieTimesView> getMovieTimes(@ApiParam("date for show times, in format yyyy-MM-dd") @RequestParam("date")
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return movieTimesService.getMovieTimes(date);
    }

}
