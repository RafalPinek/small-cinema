package com.fourthwall.smallcinema.movie.initialize;

import com.fourthwall.smallcinema.movie.dao.MovieDao;
import com.fourthwall.smallcinema.movie.dao.ShowTimeDao;
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

    private final MovieDao movieDao;

    private final ShowTimeDao showTimeDao;

    ShowTimesGenerator(MovieDao movieDao, ShowTimeDao showTimeDao) {
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
        for (int i = 10; i < 50; i = i + 10) {
            ShowTime showTime = new ShowTime(movieId, BigDecimal.valueOf(i),
                    LocalDateTime.of(2020, 8, 11, 14, i));
            showTimes.add(showTime);
        }
        showTimeDao.saveAll(showTimes);
    }
}
