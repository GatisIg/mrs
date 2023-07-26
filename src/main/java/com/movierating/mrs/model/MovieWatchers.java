package com.movierating.mrs.model;

import com.movierating.mrs.service.MovieRatingObserver;
import java.util.Observable;

public class MovieWatchers implements MovieRatingObserver {

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Movies) {
            Movies movie = (Movies) o;
            System.out.println("Movie rating updated: " + movie.getTitle() + ", Rating: " + movie.getRating());
        }
    }
}
