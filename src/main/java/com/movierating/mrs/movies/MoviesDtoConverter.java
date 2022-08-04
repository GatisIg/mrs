package com.movierating.mrs.movies;

import org.modelmapper.ModelMapper;

public class MoviesDtoConverter {
    private ModelMapper modelMapper;

    public MoviesDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private Movies mapToEntity(MoviesDTO moviesDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Movies movies = modelMapper.map(moviesDTO, Movies.class);
        movies.setRating(0);
        movies.setRatingCount(0);

        return movies;
    }


}
