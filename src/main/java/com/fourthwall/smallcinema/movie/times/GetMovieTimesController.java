package com.fourthwall.smallcinema.movie.times;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.SortedSet;

@RestController
class GetMovieTimesController {

    private final MovieTimesService movieTimesService;

    GetMovieTimesController(MovieTimesService movieTimesService) {
        this.movieTimesService = movieTimesService;
    }

    @RequestMapping(value = "/movie-times")
    SortedSet<MovieTimesView> getMovieTimes(@RequestParam("date")
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return movieTimesService.getMovieTimes(date);
    }

}
