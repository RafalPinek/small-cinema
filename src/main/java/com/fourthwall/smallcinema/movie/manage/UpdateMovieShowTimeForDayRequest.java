package com.fourthwall.smallcinema.movie.manage;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Objects;

class UpdateMovieShowTimeForDayRequest {

    private BigDecimal price;
    private LocalTime time;

    public UpdateMovieShowTimeForDayRequest(BigDecimal price, LocalTime time) {
        this.price = price;
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateMovieShowTimeForDayRequest)) return false;
        UpdateMovieShowTimeForDayRequest that = (UpdateMovieShowTimeForDayRequest) o;
        return getPrice().equals(that.getPrice()) &&
                getTime().equals(that.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getTime());
    }
}
