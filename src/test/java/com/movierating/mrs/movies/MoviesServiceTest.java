package com.movierating.mrs.movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MoviesServiceTest {
    @Mock
    private MoviesRepository moviesRepository;
    private MoviesService underTest;

    @BeforeEach
    void setUp() {
        underTest = new MoviesService(moviesRepository);
    }

    @Test
    void canGetAllMovies() {
        underTest.getMovies();

        verify(moviesRepository).findAll();
    }

    @Test
    void canAddNewMovie() {
        Movies movies = new Movies(
                "Test movie",
                2022,
                0,
                0
        );

        underTest.addNewMovie(movies);

        ArgumentCaptor<Movies> moviesArgumentCaptor = ArgumentCaptor.forClass(Movies.class);

        verify(moviesRepository).save(moviesArgumentCaptor.capture());

        Movies capturedMovies = moviesArgumentCaptor.getValue();
        assertThat(capturedMovies).isEqualTo(movies);

    }

    /*
    @Test
    @Disabled
    void deleteMovie() {
    }
    */
}