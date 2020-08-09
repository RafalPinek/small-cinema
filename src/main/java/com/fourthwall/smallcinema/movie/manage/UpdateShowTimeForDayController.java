package com.fourthwall.smallcinema.movie.manage;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
class UpdateShowTimeForDayController {

    private final UpdateShowTimesService updateShowTimesService;

    UpdateShowTimeForDayController(UpdateShowTimesService updateShowTimesService) {
        this.updateShowTimesService = updateShowTimesService;
    }

    @PutMapping(value = "/movies/{movieId}/movie-times")
    void updateMovieShowTimesForDay(@PathVariable("movieId") Long movieId,
                                    @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                    @RequestBody Set<UpdateMovieShowTimeForDayRequest> requests) {
        updateShowTimesService.updateShowTimes(movieId, date, requests);
    }
}
