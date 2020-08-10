package com.fourthwall.smallcinema.movie.manage;

import java.time.LocalDate;
import java.util.Set;

interface UpdateShowTimesService {

    /**
     * Updates show times of specific movie for a given day.
     *
     * @param movieId movie id
     * @param date date for which you want to update show times
     * @param requests set of {@link UpdateMovieShowTimeForDayRequest} containing information about price
     *                 and time for specific show time
     */
    void updateShowTimes(Long movieId, LocalDate date, Set<UpdateMovieShowTimeForDayRequest> requests);
}
