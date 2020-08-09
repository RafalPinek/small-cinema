package com.fourthwall.smallcinema.movie.details;

import com.fourthwall.smallcinema.movie.dao.MovieDao;
import com.fourthwall.smallcinema.movie.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MovieDetailsServiceImplIT {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void shouldObtainMovieDetails() {
        // given
        OmdbConnector omdbConnector = new OmdbConnector(restTemplate);
        MovieDetailsService<MovieDetailsView> movieDetailsService = new MovieDetailsServiceImpl(movieDao, omdbConnector);
        Movie movie = movieDao.findAll().iterator().next();

        // when
        MovieDetailsView movieDetailsView = movieDetailsService.getMovieDetails(movie.getId());

        // then
        assertThat(movieDetailsView).isNotNull();
        assertThat(movieDetailsView.getTitle()).isEqualTo(movie.getTitle());
        assertThat(movieDetailsView.getDescription()).isNotBlank();
        assertThat(movieDetailsView.getImdbVotes()).isGreaterThan(0);
    }
}
