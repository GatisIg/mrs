package com.movierating.mrs.service;

import com.movierating.mrs.model.MementoCareTaker;
import com.movierating.mrs.model.MovieMemento;
import com.movierating.mrs.model.MovieStateAwarded;
import com.movierating.mrs.model.Movies;
import com.movierating.mrs.model.MoviesDTO;
import com.movierating.mrs.repository.MoviesRepository;
import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MoviesService implements MoviesServiceInterface {

    private final MoviesRepository moviesRepository;

    private final MementoCareTaker mementoCareTaker;

    @Autowired
    public MoviesService(MoviesRepository moviesRepository, MementoCareTaker mementoCareTaker) {
        this.moviesRepository = moviesRepository;
        this.mementoCareTaker = mementoCareTaker;
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
                moviesDTO.getYear(), this.mementoCareTaker);

        moviesRepository.save(movie);
    }

    @Transactional
    @Override
    public void updateMovie(Long moviesId, int rating) {
        Movies movies = moviesRepository.findById(moviesId)
                .orElseThrow(() -> new IllegalStateException("Movie with Id" + moviesId + " doesn't exist."));

        MovieMemento memento = new MovieMemento(moviesId, (int) movies.getRating());
        this.mementoCareTaker.addMemento(memento);

        movies.setRatingCount(movies.getRatingCount() + 1);
        movies.setRating(rateMovie(rating, movies.getRatingCount(), movies.getRating()));

        moviesRepository.save(movies);
    }

    private double rateMovie(int newRating, int existingRatingCount, double existingRating) {
        return ((existingRating * (existingRatingCount - 1)) + newRating) / (existingRatingCount);
    }

    public void undoLastRating(Long moviesId) {
        Movies movies = moviesRepository.findById(moviesId)
                .orElseThrow(() -> new IllegalStateException("Movie with Id" + moviesId + " doesn't exist."));

        MovieMemento lastMemento = this.mementoCareTaker.getMemento(moviesId);
        if (lastMemento != null) {
            movies.restoreFromMemento(lastMemento);
            this.mementoCareTaker.removeMemento(moviesId);
        }

        this.moviesRepository.save(movies);
    }

    public Movies awardMovie(Long moviesId) {
        Movies movies = moviesRepository.findById(moviesId)
                .orElseThrow(() -> new IllegalStateException("Movie with Id" + moviesId + " doesn't exist."));
        movies.setState(new MovieStateAwarded());
        return movies;
    }
}
