package com.fourthwall.smallcinema.movie.manage;

import com.fourthwall.smallcinema.movie.dao.showtime.AbstractShowTimeDao;
import com.fourthwall.smallcinema.movie.dao.showtime.ShowTimeHashMapDao;
import com.fourthwall.smallcinema.movie.model.ShowTime;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateShowTimesServiceImplUnitTest {

    private static final BigDecimal NEW_PRICE_LOW = BigDecimal.valueOf(300);
    private static final BigDecimal NEW_PRICE_HIGH = BigDecimal.valueOf(400);
    private static final LocalTime EARLY_TIME = LocalTime.of(14, 30);
    private static final LocalTime LATE_TIME = LocalTime.of(20, 15);

    private AbstractShowTimeDao showTimeDao = new ShowTimeHashMapDao();
    private UpdateShowTimesService service = new UpdateShowTimesServiceImpl(showTimeDao);

    @Test
    public void shouldUpdateMovieShowTimes() {
        // given
        Long exampleMovieId = 1L;
        LocalDate exampleDate = LocalDate.now();

        // when
        service.updateShowTimes(exampleMovieId, exampleDate, prepareRequests());

        // then
        Set<ShowTime> updatedShowTimes = showTimeDao.getByMovieIdAndDateWithin(exampleMovieId, exampleDate);
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
