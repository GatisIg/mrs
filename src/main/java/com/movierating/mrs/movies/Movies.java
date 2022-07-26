package com.movierating.mrs.movies;

import javax.persistence.*;

@Entity
@Table
public class Movies {

    @Id
    @SequenceGenerator(name = "movie_sequence", sequenceName = "movie_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_sequence")
    private Long id;
    private String title;
    private int year;
    private double rating;
    private int count;


    public Movies() {
    }

    public Movies(Long id, String title, int year, double rating, int count) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.count = count;
    }

    public Movies(String title, int year, double rating, int count) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = (getRating() + rating) / getCount();
    }
    public int getCount() {
        return count;
    }
    public void setCount() {
        this.count = getCount() + 1;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", xount=" + count +
                '}';
    }
}
