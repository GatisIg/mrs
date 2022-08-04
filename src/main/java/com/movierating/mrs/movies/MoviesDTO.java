package com.movierating.mrs.movies;

import lombok.Data;
import lombok.Setter;

@Data
public class MoviesDTO {

    @Setter
    private String title;
    
    @Setter
    private int year;

    public MoviesDTO(String title, int year) {
        this.title = title;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movies{" +
                ", title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
