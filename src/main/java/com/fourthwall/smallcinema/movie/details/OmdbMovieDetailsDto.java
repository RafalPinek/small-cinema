package com.fourthwall.smallcinema.movie.details;

import com.fasterxml.jackson.annotation.JsonProperty;

class OmdbMovieDetailsDto {

    private String Title;
    private String Plot;
    private String Released;
    private String imdbRating;
    private String imdbVotes;
    private String Runtime;

    public String getTitle() {
        return Title;
    }

    @JsonProperty("Title")
    public void setTitle(String title) {
        Title = title;
    }

    public String getPlot() {
        return Plot;
    }

    @JsonProperty("Plot")
    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getReleased() {
        return Released;
    }

    @JsonProperty("Released")
    public void setReleased(String released) {
        Released = released;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getRuntime() {
        return Runtime;
    }

    @JsonProperty("Runtime")
    public void setRuntime(String runtime) {
        Runtime = runtime;
    }
}
