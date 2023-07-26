package com.movierating.mrs.model;

import java.util.Observable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "movie_type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@AllArgsConstructor
public class Movies extends Observable {

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
        setChanged();
        notifyObservers();
    }

    public int getRatingCount() {
        return ratingCount;
    }
    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", count=" + ratingCount +
                '}';
    }
}
