package com.movierating.mrs.model;

import com.movierating.mrs.repository.MoviesRepository;
import com.movierating.mrs.service.MoviesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.BadRequestException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MoviesServiceTest {
    @Mock
    private MoviesRepository moviesRepositoryMock;
    @InjectMocks
    private MoviesService moviesService;

    @Test
    void canGetAllMovies() {
        moviesService.getMovies();

        verify(moviesRepositoryMock).findAll();
    }

    @Test
    void canAddNewMovie() {
        MoviesDTO moviesDTO = new MoviesDTO();

        moviesDTO.setTitle("Test movie");
        moviesDTO.setYear(1990);

        moviesService.addNewMovie(moviesDTO);

        ArgumentCaptor<Movies> moviesArgumentCaptor = ArgumentCaptor.forClass(Movies.class);

        verify(moviesRepositoryMock).save(moviesArgumentCaptor.capture());

        Movies capturedMovies = moviesArgumentCaptor.getValue();

        assertEquals(moviesDTO.getTitle(), capturedMovies.getTitle());
        assertEquals(moviesDTO.getYear(), capturedMovies.getYear());
        assertEquals(0, capturedMovies.getRating());
        assertEquals(0, capturedMovies.getRatingCount());

    }

    @Test
    void throwExceptionIfTitleTaken() {
        MoviesDTO moviesDTO = new MoviesDTO();

        moviesDTO.setTitle("Test movie");
        moviesDTO.setYear(1990);

        given(moviesRepositoryMock.selectTitleIfExists(moviesDTO.getTitle())).willReturn(true);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> moviesService.addNewMovie(moviesDTO));

        verify(moviesRepositoryMock, never()).save(any());
        assertEquals("Movie with this title is already registered", exception.getLocalizedMessage());
    }

}