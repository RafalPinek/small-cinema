package com.fourthwall.smallcinema.movie.dao.showtime;

import com.fourthwall.smallcinema.movie.dao.AbstractH2Dao;
import com.fourthwall.smallcinema.movie.model.ShowTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Repository
public class ShowTimeH2Dao implements AbstractShowTimeDao, AbstractH2Dao<ShowTime> {

    private final ShowTimeCrudDao showTimeCrudDao;

    public ShowTimeH2Dao(ShowTimeCrudDao showTimeCrudDao) {
        this.showTimeCrudDao = showTimeCrudDao;
    }

    @Override
    public Set<ShowTime> getByMovieIdAndDateWithin(Long movieId, LocalDate date) {
        return showTimeCrudDao.findAllByMovieIdAndDateTimeBetween(movieId, date.atStartOfDay(),
                date.plus(1, ChronoUnit.DAYS).atStartOfDay());
    }

    @Override
    public CrudRepository<ShowTime, Long> getCrudRepository() {
        return showTimeCrudDao;
    }
}
