package com.fourthwall.smallcinema.movie.dao;

import com.fourthwall.smallcinema.movie.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieDao extends CrudRepository<Movie, Long> {
}
