package com.movierating.mrs.movies;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MoviesService {

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

    public void addNewMovie(Movies movies) {
        Optional<Movies> moviesOptional = moviesRepository.getMoviesByTitle(movies.getTitle());
        if (moviesOptional.isPresent()) {
            throw new IllegalStateException("Movie with this title is already registered");
        }
        moviesRepository.save(movies);
    }

    public void deleteMovie(Long moviesId) {
        boolean exists = moviesRepository.existsById(moviesId);
        if (!exists) {
            throw new IllegalStateException("Movie with Id" + moviesId + " doesn't exist.");
        }
        moviesRepository.deleteById(moviesId);
    }

    public double rateMovie(Movies movies, int rating) {
        return ((movies.getRating() * (movies.getCount() - 1)) + rating) / (movies.getCount());
    }

    @Transactional
    public void updateMovie(Long moviesId, int rating) {
        Movies movies = moviesRepository.findById(moviesId)
                .orElseThrow(() -> new IllegalStateException("Movie with Id" + moviesId + " doesn't exist."));

        movies.setCount();
        movies.setRating(rateMovie(movies, rating));
    }
}
