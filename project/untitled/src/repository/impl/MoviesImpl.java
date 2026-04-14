package repository.impl;

import model.Movie;

import java.util.List;

public interface MoviesImpl {
    List<Movie> findAll();

    void add(Movie m);

    void delete(String id);

    void update(Movie movie);
}
