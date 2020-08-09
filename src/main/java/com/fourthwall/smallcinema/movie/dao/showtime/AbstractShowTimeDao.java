package com.fourthwall.smallcinema.movie.dao.showtime;

import com.fourthwall.smallcinema.movie.dao.GenericDao;
import com.fourthwall.smallcinema.movie.model.ShowTime;

import java.time.LocalDate;
import java.util.Set;

public interface AbstractShowTimeDao extends GenericDao<ShowTime> {

    Set<ShowTime> getByMovieIdAndDateWithin(Long movieId, LocalDate date);

}
