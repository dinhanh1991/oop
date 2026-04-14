package service;

import model.Movie;
import repository.MoviesRepo;
import service.impl.MovieServiceImpl;

import java.util.List;

public class MovieService implements MovieServiceImpl {
    MoviesRepo moviesRepo = new MoviesRepo();

    @Override
    public List<Movie> getAll() {
        return moviesRepo.findAll();
    }

    @Override
    public void add(Movie m) {
        moviesRepo.add(m);
    }
    @Override
    public void delete(String id) {
    moviesRepo.delete(id);
    }

    @Override
    public void update(Movie m) {
        moviesRepo.update(m);
    }
}
