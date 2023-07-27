package com.movierating.mrs.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "movie_type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@AllArgsConstructor
public class Movies implements MovieSubject {

   //@Transient
    private static List<MovieObserver> observers = new ArrayList<>();

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
        notifyRatingUpdate(rating);
    }

    public int getRatingCount() {
        return ratingCount;
    }
    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    @Override
    public void addObserver(MovieObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(MovieObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyRatingUpdate(double newRating) {
        for (MovieObserver observer : observers) {
            observer.onRatingUpdate(newRating);
        }
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
