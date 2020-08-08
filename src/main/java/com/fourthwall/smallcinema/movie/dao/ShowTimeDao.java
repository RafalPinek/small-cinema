package com.fourthwall.smallcinema.movie.dao;

import com.fourthwall.smallcinema.movie.model.ShowTime;
import org.springframework.data.repository.CrudRepository;

public interface ShowTimeDao extends CrudRepository<ShowTime, Long> {
}
