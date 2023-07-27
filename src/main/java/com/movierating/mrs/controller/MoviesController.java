package com.movierating.mrs.controller;

import com.movierating.mrs.model.Movies;
import com.movierating.mrs.model.MoviesDTO;
import com.movierating.mrs.service.MoviesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/movies-list")
public class MoviesController {

    private final MoviesService moviesService;

    @Autowired
    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping
    public List<Movies> getMovies() {
        return moviesService.getMovies();
    }

    @GetMapping("/rating")
    public List<Movies> getMoviesSorted() {
        return moviesService.getMoviesSorted("rating");
    }

    @PostMapping
    public void addNewMovie(@RequestBody MoviesDTO moviesDTO) {
        moviesService.addNewMovie(moviesDTO);
    }


    @PutMapping(path = "{moviesId}")
    public void updateMovie(
        @PathVariable("moviesId") Long moviesId,
        @RequestParam("rating") double rating) {
            moviesService.updateMovie(moviesId, (int) rating);
        }

}
