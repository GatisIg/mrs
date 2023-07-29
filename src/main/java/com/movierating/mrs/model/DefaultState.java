package com.movierating.mrs.model;

public class DefaultState implements MovieState {

    @Override
    public String getTitleWithState(String title) {
        return title;
    }
}
