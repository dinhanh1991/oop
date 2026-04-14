package repository.impl;

import model.Movie;

import java.util.List;

public interface MoviesImpl {
    List<Movie> findAll();
    public void add(Movie m);
    public void delete(String id);
    public void update(Movie movie);
}
