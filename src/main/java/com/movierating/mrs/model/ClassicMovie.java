package com.movierating.mrs.model;

import java.util.Random;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("Classic")
public class ClassicMovie extends Movies {

    public ClassicMovie() {
    }

    public ClassicMovie(String title, int year, MementoCareTaker mementoCareTaker) {
        super(title, year, getRandomRating(), getRandomRatingCount(), mementoCareTaker);
    }

    private static double getRandomRating() {
        Random random = new Random();
        return 1 + random.nextDouble() * 4;
    }

    private static int getRandomRatingCount() {
        Random random = new Random();
        return 500 + random.nextInt(10000);
    }
}