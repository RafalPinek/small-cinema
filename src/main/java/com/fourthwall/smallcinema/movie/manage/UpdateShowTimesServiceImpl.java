package com.fourthwall.smallcinema.movie.manage;

import com.fourthwall.smallcinema.movie.dao.showtime.AbstractShowTimeDao;
import com.fourthwall.smallcinema.movie.model.ShowTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class UpdateShowTimesServiceImpl implements UpdateShowTimesService {

    private final AbstractShowTimeDao showTimeDao;

    UpdateShowTimesServiceImpl(AbstractShowTimeDao showTimeDao) {
        this.showTimeDao = showTimeDao;
    }

    @Override
    @Transactional
    public void updateShowTimes(Long movieId, LocalDate date, Set<UpdateMovieShowTimeForDayRequest> requests) {
        Set<ShowTime> currentShowTimes = showTimeDao.getByMovieIdAndDateWithin(movieId, date);
        currentShowTimes.forEach(showTime -> showTimeDao.deleteById(showTime.getId()));
        Set<ShowTime> showTimesToSave = requests.stream()
                .map(request -> buildShowTime(request, date, movieId))
                .collect(Collectors.toSet());
        showTimeDao.saveAll(showTimesToSave);
    }

    private ShowTime buildShowTime(UpdateMovieShowTimeForDayRequest request, LocalDate date, Long movieId) {
        return new ShowTime(movieId, request.getPrice(), LocalDateTime.of(date, request.getTime()));
    }
}
