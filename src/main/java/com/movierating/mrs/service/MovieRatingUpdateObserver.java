package com.movierating.mrs.service;

import com.movierating.mrs.model.Movies;
import java.util.Observable;

public class MovieRatingUpdateObserver implements MovieRatingObserver {

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Movies) {
            Movies movie = (Movies) o;
            System.out.println("Movie rating for: " + movie.getTitle() + ", has changed to: " + movie.getRating());
        }
    }
}
