package com.fourthwall.smallcinema.movie.details;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GetMovieDetailsController {

    private final MovieDetailsService<MovieDetailsView> movieDetailsService;

    GetMovieDetailsController(MovieDetailsService<MovieDetailsView> movieDetailsService) {
        this.movieDetailsService = movieDetailsService;
    }

    @RequestMapping(value = "/movies/{id}")
    MovieDetailsView getMovieDetails(@PathVariable("id") Long id) {
        return movieDetailsService.getMovieDetails(id);
    }

}
