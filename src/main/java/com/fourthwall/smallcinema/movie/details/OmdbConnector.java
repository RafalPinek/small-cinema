package com.fourthwall.smallcinema.movie.details;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class OmdbConnector {

    private static final String OMDB_API_URL_TEMPLATE = "http://www.omdbapi.com/?apikey=%s&i=%s";

    private final RestTemplate restTemplate;

    OmdbConnector(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    OmdbMovieDetailsDto getMovieDetails(String imdbId) {
        return restTemplate.getForObject(String.format(OMDB_API_URL_TEMPLATE, resolveApiKey(), imdbId),
                OmdbMovieDetailsDto.class);
    }

    private String resolveApiKey() {
        return System.getenv("imdb_api_key");
    }

}
