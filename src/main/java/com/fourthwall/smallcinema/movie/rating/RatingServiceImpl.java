package com.fourthwall.smallcinema.movie.rating;

import com.fourthwall.smallcinema.movie.dao.MovieDao;
import com.fourthwall.smallcinema.movie.model.Movie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class RatingServiceImpl implements RatingService {

    private final RatingValidator ratingValidator = new RatingValidator();

    private final MovieDao movieDao;

    RatingServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Transactional
    public void rate(Long movieId, Integer rate) {
        ratingValidator.validate(rate);
        Movie movie = movieDao.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie with id: " + movieId + " does not exist"));

        movie.rate(rate);
        movieDao.save(movie);
    }

}
