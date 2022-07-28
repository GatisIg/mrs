package com.movierating.mrs.movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MoviesRepositoryTest {

    @Autowired
    private MoviesRepository underTest;

    @Test
    void selectTitleIfExists() {
        String title = "The Dark Knight";
        Movies movies = new Movies(
                title,
                2008,
                0,
                0
        );
        underTest.save(movies);

        boolean exists = underTest.selectTitleIfExists(title);

        assertThat(exists).isTrue();
    }
}