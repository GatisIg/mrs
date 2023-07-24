package com.movierating.mrs.model;

import com.movierating.mrs.repository.MoviesRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
@Ignore
@DataJpaTest
class MoviesRepositoryTest {

    @Autowired
    private MoviesRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void selectTitleIfExists() {
        String title = "The Dark Knight";
        Movies movies = new Movies(
                1L,
                title,
                2008,
                0,
                0
        );
        underTest.save(movies);

        boolean exists = underTest.selectTitleIfExists(title);

        assertThat(exists).isTrue();
    }

    @Test
    void trySelectNonExistingTitle() {
        String title = "The Dark Knight";

        boolean exists = underTest.selectTitleIfExists(title);

        assertThat(exists).isFalse();
    }

}