package com.fourthwall.smallcinema.movie.manage;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Updates show times for given movie for given day. Requires login and admin role.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated show times"),
            @ApiResponse(code = 400, message = "The provided parameters are invalid"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @PutMapping(value = "/movies/{movieId}/movie-times")
    void updateMovieShowTimesForDay(@ApiParam("movie id") @PathVariable("movieId") Long movieId,
                                    @ApiParam("date for show times, in format yyyy-MM-dd") @RequestParam("date")
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                    @ApiParam("array of information about price and time (in format hh:mm)")
                                    @RequestBody Set<UpdateMovieShowTimeForDayRequest> requests) {
        updateShowTimesService.updateShowTimes(movieId, date, requests);
    }
}
