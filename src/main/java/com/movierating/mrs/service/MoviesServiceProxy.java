package com.movierating.mrs.service;

public class MoviesServiceProxy implements MoviesServiceInterface {

    private final MoviesServiceInterface moviesServiceInterface;

    private boolean isAdmin = false;

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public MoviesServiceProxy(MoviesServiceInterface moviesServiceInterface) {
        this.moviesServiceInterface = moviesServiceInterface;
    }

    @Override
    public void updateMovie(Long moviesId, int rating) throws Exception {
        if (this.isAdmin) {
            this.moviesServiceInterface.updateMovie(moviesId, rating);
        } else {
            throw new Exception("You need admin privileges to run this command.");
        }
    }
}
