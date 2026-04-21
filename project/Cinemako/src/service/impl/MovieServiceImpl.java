package service.impl;

import model.Movie;

import java.util.List;

public interface MovieServiceImpl {
    List<Movie> getAll();
    void addMovie(Movie movie);
    void updateMovie(Movie movie);
    void deleteMovie(String id);
    List<Movie> searchByName(String name);

}
