package com.movierating.mrs.model;

public class MovieStateAwarded implements MovieState {

    @Override
    public String getTitleWithState(String title) {
        return title + " (Award Winner)";
    }
}
