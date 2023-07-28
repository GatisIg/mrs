package com.movierating.mrs.service;

import com.movierating.mrs.model.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovieRatingChannel implements Channel {

    private double rating;

    private static final Logger logger = LoggerFactory.getLogger(MovieRatingChannel.class);

    @Override
    public void update(Object rating) {
        this.setRating((double) rating);
        logger.info("Observed movie received a new rating. The rating is now: " + rating);
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
