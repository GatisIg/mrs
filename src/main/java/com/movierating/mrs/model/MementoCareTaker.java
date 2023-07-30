package com.movierating.mrs.model;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class MementoCareTaker {

    private final Map<Long, MovieMemento> mementos = new HashMap<>();

    public void addMemento(MovieMemento memento) {
        this.mementos.put(memento.getMovieId(), memento);
    }

    public MovieMemento getMemento(Long movieId) {
        return this.mementos.get(movieId);
    }

    public void removeMemento(Long movieId) {
        this.mementos.remove(movieId);
    }
}
