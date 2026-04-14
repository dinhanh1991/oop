package service.impl;

import model.Movie;

import java.util.List;

public interface MovieServiceImpl {
    List<Movie> getAll();
    public void add(Movie m);
    public void delete(String id);
    public void update(Movie m);
}
