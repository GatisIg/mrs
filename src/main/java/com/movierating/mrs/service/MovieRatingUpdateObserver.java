package com.movierating.mrs.service;

import com.movierating.mrs.model.MovieObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovieRatingUpdateObserver implements MovieObserver {

    private String movieTitle;

    private static final Logger logger = LoggerFactory.getLogger(MovieRatingUpdateObserver.class);

    public MovieRatingUpdateObserver(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    @Override
    public void onRatingUpdate(double newRating) {
        logger.info(movieTitle + " received a new rating. The rating is now: " + newRating);
    }
}
