package repository.Impl;

import model.Movie;

import java.util.List;

public interface IMovieImp {

    List<Movie> getAllMovies();
    void addMovies(Movie movies);
    void updateMovies(Movie movies);
    void deleteMovies(String id);
    List<Movie> findAllMoviesByName(String name);
}
