package com.movierating.mrs.config;

import com.movierating.mrs.model.Movies;
import com.movierating.mrs.repository.MoviesRepository;
import com.movierating.mrs.service.MovieRatingChannel;
import java.util.List;
import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MoviesConfig {

    @Bean
    CommandLineRunner commandLineRunner(MoviesRepository moviesRepository) {
        return args ->{
            Movies a = new Movies(
                    "The Dark Knight",
                    2008,
                    0,
                    0
            );

            Movies b = new Movies(
                    "The Shawshank Redemption",
                    1994,
                    0,
                    0
            );

            Movies c = new Movies(
                    "The Godfather",
                    1972,
                    0,
                    0
            );

            Movies d = new Movies(
                    "The Godfather Part II",
                    1974,
                    0,
                    0
            );

            Movies e = new Movies(
                    "12 Angry Men",
                    1957,
                    0,
                    0
            );

            Movies f = new Movies(
                    "Schindler's List",
                    1993,
                    0,
                    0
            );

            Movies g = new Movies(
                    "The Lord of the Rings: The Return of the King",
                    2003,
                    0,
                    0
            );

            Movies h = new Movies(
                    "Pulp Fiction",
                    1994,
                    0,
                    0
            );

            Movies i = new Movies(
                    "The Lord of the Rings: The Fellowship of the Ring",
                    2001,
                    0,
                    0
            );

            Movies j = new Movies(
                    "The Good, the Bad and the Ugly",
                    1966,
                    0,
                    0
            );

            MovieRatingChannel observer = new MovieRatingChannel();
            a.addObserver(observer);

            moviesRepository.saveAll(List.of(a, b, c, d, e, f, g, h, i, j));

            //rate for Observer test, should return only a: 5*
            a.setRating(5);
            b.setRating(4);
        };
    }

}
