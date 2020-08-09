package com.fourthwall.smallcinema.movie.manage;

import java.time.LocalDate;
import java.util.Set;

interface UpdateShowTimesService {

    void updateShowTimes(Long movieId, LocalDate date, Set<UpdateMovieShowTimeForDayRequest> requests);
}
