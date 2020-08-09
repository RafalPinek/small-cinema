package com.fourthwall.smallcinema.movie.dao.movie;

import com.fourthwall.smallcinema.movie.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieCrudDao extends CrudRepository<Movie, Long> {
}
