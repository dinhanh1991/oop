package service;

import model.Movie;
import repository.MoviesRepo;
import service.impl.MovieServiceImpl;

import java.util.List;

/**
 * Service xử lý logic liên quan đến Movie (phim)
 * Đóng vai trò trung gian giữa Controller và Repository
 */
public class MovieService implements MovieServiceImpl {

    // Kết nối tới tầng Repository để thao tác dữ liệu phim
    MoviesRepo moviesRepo = new MoviesRepo();

    /**
     * Lấy toàn bộ danh sách phim
     * @return danh sách Movie
     */
    @Override
    public List<Movie> getAll() {
        return moviesRepo.findAll();
    }

    /**
     * Thêm một phim mới
     * @param m đối tượng Movie cần thêm
     */
    @Override
    public void add(Movie m) {
        moviesRepo.add(m);
    }

    /**
     * Xóa phim theo ID
     * @param id mã phim cần xóa
     */
    @Override
    public void delete(String id) {
        moviesRepo.delete(id);
    }

    /**
     * Cập nhật thông tin phim
     * @param m đối tượng Movie chứa thông tin mới
     */
    @Override
    public void update(Movie m) {
        moviesRepo.update(m);
    }
}