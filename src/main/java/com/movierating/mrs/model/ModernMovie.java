package com.movierating.mrs.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Modern")
public class ModernMovie extends Movies {

    public ModernMovie() {
    }

    public ModernMovie(String title, int year, double rating, int ratingCount) {
        super(title, year, rating, ratingCount);
    }
}
