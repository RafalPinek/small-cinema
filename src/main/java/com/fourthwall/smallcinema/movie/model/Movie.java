package com.fourthwall.smallcinema.movie.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String imdbId;

    @Column
    private String title;

    @Column
    private Double rating;

    @Column
    private Integer votes;

    public Movie(String imdbId, String title) {
        this.imdbId = imdbId;
        this.title = title;
        rating = 0.0;
        votes = 0;
    }

    public void rate(int rate) {
        double tmp = (rating * votes + (double) rate) / (double) (votes + 1);
        rating = (double) Math.round(tmp * 100.0) / 100.0;
        votes++;
    }

    public Long getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getTitle() {
        return title;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getVotes() {
        return votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        if (this.id == null && movie.id == null) {
            return getImdbId().equals(movie.getImdbId()) &&
                    getTitle().equals(movie.getTitle()) &&
                    Objects.equals(getRating(), movie.getRating()) &&
                    Objects.equals(getVotes(), movie.getVotes());
        }
        if (this.id != null && movie.id != null) {
            return this.id.equals(movie.id);
        }
        return false;
    }

    /*
     This implementation of hashCode() may look at the worse implementation ever, but this is a special case because
     the class is an entity, and the object might be in different JPA entity state - before saving, the object
     doesn't have id, after saving it has... to accommodate to all possible scenarios, constant hashCode of course makes worst
     time complexity in hashMaps / hashSets, but this time complexity should be taken into account for minimum 1000+
     objects. And if we would have 1000+ entities to fetch, the bottle-neck will be somewhere else..
     */
    @Override
    public int hashCode() {
        return 42;
    }
}
