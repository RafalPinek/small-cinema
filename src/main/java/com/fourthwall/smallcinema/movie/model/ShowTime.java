package com.fourthwall.smallcinema.movie.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class ShowTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long movieId;

    @Column
    private BigDecimal price;

    @Column
    private LocalDateTime dateTime;

    public ShowTime(Long movieId, BigDecimal price, LocalDateTime dateTime) {
        this.movieId = movieId;
        this.price = price;
        this.dateTime = dateTime;
    }

    public ShowTime() {}

    public Long getId() {
        return id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowTime)) return false;
        ShowTime showTime = (ShowTime) o;
        if (this.id == null && showTime.id == null) {
            return movieId.equals(showTime.movieId) &&
                    price.equals(showTime.price) &&
                    dateTime.equals(showTime.dateTime);
        }
        if (this.id != null && showTime.id != null) {
            return this.id.equals(showTime.id);
        }
        return false;
    }

    /*
         This implementation of hashCode() may look at the worse implementation ever,
         but please see {@link Movie#hashCode()} for explanation
         */
    @Override
    public int hashCode() {
        return 42;
    }
}
