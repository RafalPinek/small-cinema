package com.fourthwall.smallcinema.movie.details;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GetMovieDetailsController {

    private final MovieDetailsService<MovieDetailsView> movieDetailsService;

    GetMovieDetailsController(MovieDetailsService<MovieDetailsView> movieDetailsService) {
        this.movieDetailsService = movieDetailsService;
    }

    @ApiOperation(value = "Gets details about specific movie.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned movie"),
            @ApiResponse(code = 400, message = "The provided parameters are invalid"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @GetMapping(value = "/movies/{id}")
    MovieDetailsView getMovieDetails(@ApiParam("movie id") @PathVariable("id") Long id) {
        return movieDetailsService.getMovieDetails(id);
    }

}
