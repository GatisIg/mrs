package com.movierating.mrs.util;

import com.movierating.mrs.model.Movies;
import com.movierating.mrs.model.MoviesDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoviesDTOConverter {

    public static Movies mapToEntity(MoviesDTO moviesDTO) {
        Movies movies = new Movies();
        movies.setTitle(moviesDTO.getTitle());
        movies.setYear(moviesDTO.getYear());

        return movies;
    }


}
