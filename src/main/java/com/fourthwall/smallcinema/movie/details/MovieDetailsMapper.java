package com.fourthwall.smallcinema.movie.details;

import com.fourthwall.smallcinema.movie.model.Movie;

class MovieDetailsMapper {

    MovieDetailsView toView(Movie movie,  OmdbMovieDetailsDto omdbMovieDetailsDto) {
        MovieDetailsView detailsView = new MovieDetailsView();
        detailsView.setTitle(omdbMovieDetailsDto.getTitle());
        detailsView.setDescription(omdbMovieDetailsDto.getPlot());
        detailsView.setReleaseDate(omdbMovieDetailsDto.getReleased());
        detailsView.setImdbRating(Double.valueOf(omdbMovieDetailsDto.getImdbRating()));
        detailsView.setImdbVotes(Integer.valueOf(omdbMovieDetailsDto.getImdbVotes().replace(",", "")));
        detailsView.setRuntime(omdbMovieDetailsDto.getRuntime());

        detailsView.setRating(movie.getRating());
        detailsView.setVotes(movie.getVotes());
        detailsView.setVersion(movie.getVersion());

        return detailsView;
    }
}
