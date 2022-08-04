package com.movierating.mrs.movies;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.util.List;

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

    public void addNewMovie(MoviesDTO moviesDTO) {
        Boolean titleExists = moviesRepository
                .selectTitleIfExists(moviesDTO.getTitle());
        if (titleExists) {
            throw new BadRequestException(
                    "Movie with this title is already registered");
        }
        moviesRepository.save(movies);
    }

/*    public void addNewMovie(Movies movies) {
        Boolean titleExists = moviesRepository
                .selectTitleIfExists(movies.getTitle());
        if (titleExists) {
            throw new BadRequestException(
                    "Movie with this title is already registered");
        }
        moviesRepository.save(movies);
    }*/

    public double rateMovie(Movies movies, int rating) {
        return ((movies.getRating() * (movies.getRatingCount() - 1)) + rating) / (movies.getRatingCount());
    }

    @Transactional
    public void updateMovie(Long moviesId, int rating) {
        Movies movies = moviesRepository.findById(moviesId)
                .orElseThrow(() -> new IllegalStateException("Movie with Id" + moviesId + " doesn't exist."));

        movies.setRatingCount();
        movies.setRating(rateMovie(movies, rating));
    }
}
