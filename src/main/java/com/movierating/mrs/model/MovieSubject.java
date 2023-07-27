package com.movierating.mrs.model;

public interface MovieSubject {

    void addObserver(MovieObserver observer);

    void removeObserver(MovieObserver observer);

    void notifyRatingUpdate(double newRating);
}
