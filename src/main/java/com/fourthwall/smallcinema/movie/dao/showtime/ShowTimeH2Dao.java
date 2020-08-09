package com.fourthwall.smallcinema.movie.dao.showtime;

import com.fourthwall.smallcinema.movie.model.ShowTime;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public class ShowTimeH2Dao implements AbstractShowTimeDao {

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
    public void save(ShowTime entity) {
        showTimeCrudDao.save(entity);
    }

    @Override
    public void saveAll(Collection<ShowTime> entities) {
        showTimeCrudDao.saveAll(entities);
    }

    @Override
    public Optional<ShowTime> findById(Long id) {
        return showTimeCrudDao.findById(id);
    }

    @Override
    public Iterable<ShowTime> findAll() {
        return showTimeCrudDao.findAll();
    }
}
