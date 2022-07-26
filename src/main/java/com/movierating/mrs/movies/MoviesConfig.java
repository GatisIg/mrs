package com.movierating.mrs.movies;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MoviesConfig {

    @Bean
    CommandLineRunner commandLineRunner(MoviesRepository moviesRepository) {
        return args ->{
            Movies A = new Movies(
                    "The Dark Knight",
                    2008,
                    0,
                    0
            );

            Movies B = new Movies(
                    "The Shawshank Redemption",
                    1994,
                    0,
                    0
            );

            Movies C = new Movies(
                    "The Godfather",
                    1972,
                    0,
                    0
            );

            Movies D = new Movies(
                    "The Godfather Part II",
                    1974,
                    0,
                    0
            );

            Movies E = new Movies(
                    "12 Angry Men",
                    1957,
                    0,
                    0
            );

            Movies F = new Movies(
                    "Schindler's List",
                    1993,
                    0,
                    0
            );

            Movies G = new Movies(
                    "The Lord of the Rings: The Return of the King",
                    2003,
                    0,
                    0
            );

            Movies H = new Movies(
                    "Pulp Fiction",
                    1994,
                    0,
                    0
            );

            Movies I = new Movies(
                    "The Lord of the Rings: The Fellowship of the Ring",
                    2001,
                    0,
                    0
            );

            Movies J = new Movies(
                    "The Good, the Bad and the Ugly",
                    1966,
                    0,
                    0
            );

            moviesRepository.saveAll(List.of(A, B, C, D, E, F, G, H, I, J));

        };
    }

}
