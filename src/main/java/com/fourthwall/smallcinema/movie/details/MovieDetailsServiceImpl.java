package com.fourthwall.smallcinema.movie.details;

import com.fourthwall.smallcinema.movie.dao.movie.AbstractMovieDao;
import com.fourthwall.smallcinema.movie.model.Movie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class MovieDetailsServiceImpl implements MovieDetailsService<MovieDetailsView> {

    private final AbstractMovieDao movieDao;

    private final OmdbConnector omdbConnector;

    MovieDetailsServiceImpl(AbstractMovieDao movieDao, OmdbConnector omdbConnector) {
        this.movieDao = movieDao;
        this.omdbConnector = omdbConnector;
    }

    @Override
    @Transactional(readOnly = true)
    public MovieDetailsView getMovieDetails(Long movieId) {
        Movie movie = movieDao.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie with id: " + movieId + " doesn't exist."));

        OmdbMovieDetailsDto dto = omdbConnector.getMovieDetails(movie.getImdbId());
        return new MovieDetailsMapper().toView(movie, dto);
    }

}
