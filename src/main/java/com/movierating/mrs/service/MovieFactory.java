package com.movierating.mrs.service;

import com.movierating.mrs.model.ClassicMovie;
import com.movierating.mrs.model.MementoCareTaker;
import com.movierating.mrs.model.ModernMovie;
import com.movierating.mrs.model.Movies;

public class MovieFactory {

    public static Movies createMovie(String title, int year, MementoCareTaker mementoCareTaker) {
        if (year <= 1990) {
            // Classic movies import rating;
            return new ClassicMovie(title, year, mementoCareTaker);
        } else {
            return new ModernMovie(title, year, 0.0, 0, mementoCareTaker);
        }
    }
}
