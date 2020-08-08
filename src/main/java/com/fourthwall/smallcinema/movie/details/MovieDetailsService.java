package com.fourthwall.smallcinema.movie.details;

import com.fourthwall.smallcinema.movie.dao.MovieDao;
import com.fourthwall.smallcinema.movie.model.Movie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class MovieDetailsService {

    private final MovieDao movieDao;

    private final OmdbConnector omdbConnector;

    MovieDetailsService(MovieDao movieDao, OmdbConnector omdbConnector) {
        this.movieDao = movieDao;
        this.omdbConnector = omdbConnector;
    }

    @Transactional(readOnly = true)
    MovieDetailsView getMovieDetails(Long movieId) {
        Movie movie = movieDao.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie with id: " + movieId + " doesn't exist."));

        OmdbMovieDetailsDto dto = omdbConnector.getMovieDetails(movie.getImdbId());
        return new MovieDetailsMapper().toView(movie, dto);
    }

}
