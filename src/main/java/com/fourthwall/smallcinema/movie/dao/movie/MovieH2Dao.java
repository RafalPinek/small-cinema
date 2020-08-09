package com.fourthwall.smallcinema.movie.dao.movie;

import com.fourthwall.smallcinema.movie.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class MovieH2Dao implements AbstractMovieDao {

    private final MovieCrudDao movieCrudDao;

    public MovieH2Dao(MovieCrudDao movieCrudDao) {
        this.movieCrudDao = movieCrudDao;
    }

    @Override
    public void save(Movie entity) {
        movieCrudDao.save(entity);
    }

    @Override
    public void saveAll(Collection<Movie> entities) {
        movieCrudDao.saveAll(entities);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieCrudDao.findById(id);
    }

    @Override
    public Iterable<Movie> findAll() {
        return movieCrudDao.findAll();
    }
}
