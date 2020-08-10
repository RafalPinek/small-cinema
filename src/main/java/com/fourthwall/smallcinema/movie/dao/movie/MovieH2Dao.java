package com.fourthwall.smallcinema.movie.dao.movie;

import com.fourthwall.smallcinema.movie.dao.AbstractH2Dao;
import com.fourthwall.smallcinema.movie.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MovieH2Dao implements AbstractMovieDao, AbstractH2Dao<Movie> {

    private final MovieCrudDao movieCrudDao;

    public MovieH2Dao(MovieCrudDao movieCrudDao) {
        this.movieCrudDao = movieCrudDao;
    }

    @Override
    public CrudRepository<Movie, Long> getCrudRepository() {
        return movieCrudDao;
    }
}
