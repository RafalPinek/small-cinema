package com.fourthwall.smallcinema.movie.dao;

import com.fourthwall.smallcinema.movie.model.ShowTime;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public interface ShowTimeDao extends CrudRepository<ShowTime, Long> {

    default Set<ShowTime> getByMovieIdAndDateWithin(Long movieId, LocalDate date) {
        return findAllByMovieIdAndDateTimeBetween(movieId, date.atStartOfDay(),
                date.plus(1, ChronoUnit.DAYS).atStartOfDay());
    }

    Set<ShowTime> findAllByMovieIdAndDateTimeBetween(Long movieId, LocalDateTime from, LocalDateTime to);

}
