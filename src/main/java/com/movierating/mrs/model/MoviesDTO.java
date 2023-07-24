package com.movierating.mrs.model;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class MoviesDTO {

    private String title;

    private int year;


    @Override
    public String toString() {
        return "Movies{" +
                ", title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
