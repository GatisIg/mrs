package com.movierating.mrs.model;

public class MovieMemento {

    private final Long movieId;

    private final int rating;

    public MovieMemento(Long movieId, int rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public Long getMovieId() {
        return this.movieId;
    }

    public int getRating() {
        return this.rating;
    }
}
