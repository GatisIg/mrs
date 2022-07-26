package com.movierating.mrs.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/movies-list")
public class MoviesController {

    private final MoviesService moviesService;

    @Autowired
    public MoviesController(MoviesService moviesService) {this.moviesService = moviesService;}

    @GetMapping
    public List<Movies> getMovies() { return moviesService.getMovies();}

    @GetMapping("/rating")
    public List<Movies> getMoviesSorted() {
        return moviesService.getMoviesSorted("rating");
    }

    @PostMapping
    public void addNewMovie(@RequestBody Movies movies) { moviesService.addNewMovie(movies);}

    @DeleteMapping(path = "{moviesId}")
    public void deleteMovie(@PathVariable("moviesId") Long moviesId) {
        moviesService.deleteMovie(moviesId);
    }

    @PutMapping(path = "{moviesId}")
    public void updateMovie(
        @PathVariable("moviesId") Long moviesId,
        @RequestParam("rating") double rating) {
            moviesService.updateMovie(moviesId, (int) rating);
        }

}
