package com.movierating.mrs.service;

import com.movierating.mrs.model.MovieStateAwarded;
import com.movierating.mrs.model.Movies;
import com.movierating.mrs.model.MoviesDTO;
import com.movierating.mrs.repository.MoviesRepository;
import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MoviesService implements MoviesServiceInterface {

    private final MoviesRepository moviesRepository;

    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public List<Movies> getMovies() {
        return moviesRepository.findAll();
    }

    public List<Movies> getMoviesSorted(String rating) {
        return moviesRepository.findAll(Sort.by(Sort.Direction.DESC, rating));
    }

    public void addNewMovie(MoviesDTO moviesDTO) {
        Boolean titleExists = moviesRepository
                .selectTitleIfExists(moviesDTO.getTitle());
        if (titleExists) {
            throw new BadRequestException(
                    "Movie with this title is already registered");
        }
        Movies movie = MovieFactory.createMovie(moviesDTO.getTitle(),
                moviesDTO.getYear());

        moviesRepository.save(movie);
    }

    @Transactional
    @Override
    public void updateMovie(Long moviesId, int rating) {
        Movies movies = moviesRepository.findById(moviesId)
                .orElseThrow(() -> new IllegalStateException("Movie with Id" + moviesId + " doesn't exist."));

        movies.setRatingCount(movies.getRatingCount()+1);
        movies.setRating(rateMovie(rating, movies.getRatingCount(), movies.getRating()));
    }

    private double rateMovie(int newRating, int existingRatingCount, double existingRating) {
        return ((existingRating * (existingRatingCount - 1)) + newRating) / (existingRatingCount);
    }

    public Movies awardMovie(Long moviesId) {
        Movies movies = moviesRepository.findById(moviesId)
                .orElseThrow(() -> new IllegalStateException("Movie with Id" + moviesId + " doesn't exist."));
        movies.setState(new MovieStateAwarded());
        return movies;
    }
}
