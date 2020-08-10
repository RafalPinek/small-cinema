package com.fourthwall.smallcinema.movie.manage;

import com.fourthwall.smallcinema.movie.dao.showtime.AbstractShowTimeDao;
import com.fourthwall.smallcinema.movie.model.ShowTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UpdateShowTimesServiceImplIT {

    private static final BigDecimal NEW_PRICE_LOW = BigDecimal.valueOf(300);
    private static final BigDecimal NEW_PRICE_HIGH = BigDecimal.valueOf(400);
    private static final LocalTime EARLY_TIME = LocalTime.of(14, 30);
    private static final LocalTime LATE_TIME = LocalTime.of(20, 15);

    @Autowired
    private AbstractShowTimeDao showTimeDao;

    @Test
    public void shouldUpdateMovieShowTimes() {
        // given
        UpdateShowTimesService service = new UpdateShowTimesServiceImpl(showTimeDao);
        ShowTime exampleShowTime = showTimeDao.findAll().iterator().next();
        Long movieId = exampleShowTime.getMovieId();
        LocalDate date = exampleShowTime.getDateTime().toLocalDate();

        // when
        service.updateShowTimes(movieId, date, prepareRequests());

        // then
        Set<ShowTime> updatedShowTimes = showTimeDao.getByMovieIdAndDateWithin(movieId, date);
        assertThat(updatedShowTimes).hasSize(2);
        updatedShowTimes.forEach(showTime -> assertThat(isUpdatedShowTime(showTime)).isTrue());
    }

    private Set<UpdateMovieShowTimeForDayRequest> prepareRequests() {
        Set<UpdateMovieShowTimeForDayRequest> requests = new HashSet<>();
        requests.add(new UpdateMovieShowTimeForDayRequest(NEW_PRICE_LOW, EARLY_TIME));
        requests.add(new UpdateMovieShowTimeForDayRequest(NEW_PRICE_HIGH, LATE_TIME));
        return requests;
    }

    private boolean isUpdatedShowTime(ShowTime showTime) {
        return isEarlyShowTime(showTime) || isLateShowTime(showTime);
    }

    private boolean isEarlyShowTime(ShowTime showTime) {
        return showTime.getDateTime().toLocalTime().equals(EARLY_TIME) && showTime.getPrice().compareTo(NEW_PRICE_LOW) == 0;
    }

    private boolean isLateShowTime(ShowTime showTime) {
        return showTime.getDateTime().toLocalTime().equals(LATE_TIME) && showTime.getPrice().compareTo(NEW_PRICE_HIGH) == 0;
    }
}
