package com.fourthwall.smallcinema.movie.details;

import com.fourthwall.smallcinema.movie.model.Movie;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieDetailsMapperUnitTest {

    private static final String IMDB_ID = "tt123456";
    private static final String IMDB_RATING = "3.4";
    private static final String IMDB_VOTES = "20";
    private static final String TITLE = "Titanic";
    private static final String PLOT = "Romance on a ship";
    private static final String RELEASED = "12 Jun 1998";
    private static final String RUNTIME = "300 min";
    private static final Double RATING = 4.0;
    private static final Integer VOTES = 9;

    @Test
    public void shouldMapCorrectlyToMovieDetails() {
        // given
        OmdbMovieDetailsDto omdbMovieDetailsDto = prepareDto();
        Movie movie = prepareMovie();

        // when
        MovieDetailsView detailsView = new MovieDetailsMapper().toView(movie, omdbMovieDetailsDto);

        // then
        assertThat(detailsView.getTitle()).isEqualTo(TITLE);
        assertThat(detailsView.getDescription()).isEqualTo(PLOT);
        assertThat(detailsView.getReleaseDate()).isEqualTo(RELEASED);
        assertThat(detailsView.getRuntime()).isEqualTo(RUNTIME);
        assertThat(detailsView.getRating()).isEqualTo(RATING);
        assertThat(detailsView.getVotes()).isEqualTo(VOTES);
        assertThat(detailsView.getImdbRating().toString()).isEqualTo(IMDB_RATING);
        assertThat(detailsView.getImdbVotes().toString()).isEqualTo(IMDB_VOTES);
    }

    private Movie prepareMovie() {
        Movie movie = new Movie(IMDB_ID, TITLE);
        for (int i = 0; i < VOTES; i++) {
            movie.rate(RATING.intValue());
        }
        return movie;
    }

    private OmdbMovieDetailsDto prepareDto() {
        OmdbMovieDetailsDto omdbMovieDetailsDto = new OmdbMovieDetailsDto();
        omdbMovieDetailsDto.setImdbRating(IMDB_RATING);
        omdbMovieDetailsDto.setImdbVotes(IMDB_VOTES);
        omdbMovieDetailsDto.setTitle(TITLE);
        omdbMovieDetailsDto.setPlot(PLOT);
        omdbMovieDetailsDto.setReleased(RELEASED);
        omdbMovieDetailsDto.setRuntime(RUNTIME);

        return omdbMovieDetailsDto;
    }
}
