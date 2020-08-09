package com.fourthwall.smallcinema.movie.initialize;

import com.fourthwall.smallcinema.movie.dao.MovieDao;
import com.fourthwall.smallcinema.movie.dao.showtime.AbstractShowTimeDao;
import com.fourthwall.smallcinema.movie.model.ShowTime;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShowTimesGenerator {

    public static final int NUMBER_OF_TIMES = 5;

    private final MovieDao movieDao;

    private final AbstractShowTimeDao showTimeDao;

    ShowTimesGenerator(MovieDao movieDao, AbstractShowTimeDao showTimeDao) {
        this.movieDao = movieDao;
        this.showTimeDao = showTimeDao;
    }

    @PostConstruct
    @Transactional
    public void afterContextInitialized() {
        movieDao.findAll().forEach(movie -> createShowTimes(movie.getId()));
    }

    private void createShowTimes(Long movieId) {
        List<ShowTime> showTimes = new ArrayList<>();
        for (int i = 8; i < 31; i++) {
            for (int j = 10; j <= NUMBER_OF_TIMES * 10; j = j + 10) {
                ShowTime showTime = new ShowTime(movieId, BigDecimal.valueOf(j),
                        LocalDateTime.of(2020, 8, i, 20, j));
                showTimes.add(showTime);
            }
        }

        showTimeDao.saveAll(showTimes);
    }
}
