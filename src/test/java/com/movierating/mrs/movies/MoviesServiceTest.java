package com.movierating.mrs.movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.BadRequestException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

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

    @Test
    void throwExceptionIfTitleTaken() {
        Movies movies = new Movies(
                "The Dark Knight",
                2022,
                0,
                0
        );

        given(moviesRepository.selectTitleIfExists(movies.getTitle())).willReturn(true);

        assertThatThrownBy(() -> underTest.addNewMovie(movies))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Movie with this title is already registered");

        verify(moviesRepository, never()).save(any());
    }

}