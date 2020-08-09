package com.fourthwall.smallcinema.movie.initialize;

import com.fourthwall.smallcinema.movie.dao.movie.AbstractMovieDao;
import com.fourthwall.smallcinema.movie.model.Movie;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class MoviesGenerator {

    public static final int GENERATED_MOVIES_COUNT = 8;

    private final AbstractMovieDao movieDao;

    MoviesGenerator(AbstractMovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @PostConstruct
    @Transactional
    public void afterContextInitialized() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("tt0232500", "The Fast and the Furious"));
        movies.add(new Movie("tt0322259", "2 Fast 2 Furious"));
        movies.add(new Movie("tt0463985", "The Fast and the Furious: Tokyo Drift"));
        movies.add(new Movie("tt1013752", "Fast & Furious"));
        movies.add(new Movie("tt1596343", "Fast Five"));
        movies.add(new Movie("tt1905041", "Fast & Furious 6"));
        movies.add(new Movie("tt2820852", "Furious 7"));
        movies.add(new Movie("tt4630562", "The Fate of the Furious"));

        movieDao.saveAll(movies);
    }

}
