package com.movierating.mrs.movies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Movies {

    @Id
    @SequenceGenerator(name = "movie_sequence", sequenceName = "movie_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_sequence")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private int year;
    private double rating;
    private int ratingCount;

    public Movies(String title, int year, double rating, int ratingCount) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.ratingCount = ratingCount;
    }

    private static final DecimalFormat df = new DecimalFormat("0.0");
    public double getRating() {
        return (Double.parseDouble(df.format(rating)));
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }
    public void setRatingCount(int ratingCount) {
        this.ratingCount = getRatingCount() + 1;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", xount=" + ratingCount +
                '}';
    }
}
