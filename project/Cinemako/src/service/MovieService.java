package service;

import model.Movie;
import repository.MovieRepo;
import service.impl.MovieServiceImpl;

import java.util.List;

public class MovieService implements MovieServiceImpl {
    MovieRepo movieRepo = new MovieRepo();
    //=============Đọc danh Sách phim=============
    public List<Movie> getAll(){
        return movieRepo.getAllMovies();
    }
    //============Thêm phim vào =================
    public void addMovie(Movie movie) {
        // Logic: Kiểm tra ID trùng trước khi thêm
        List<Movie> currentMovies = movieRepo.getAllMovies();
        for (Movie m : currentMovies) {
            if(m.getId().equalsIgnoreCase(movie.getId())) {
                System.out.println("❌ Lỗi: ID phim " + movie.getId() + " đã tồn tại!");
                return;
            }
        }
        movieRepo.addMovies(movie);
        System.out.println("✅ Thêm phim mới thành công.");
    }
    //==============Sửa phim================
    public void updateMovie(Movie movie) {
        movieRepo.updateMovies(movie);
        System.out.println("✅ Cập nhật thông tin phim thành công.");
    }
    //=============Xóa Phim
    public void deleteMovie(String id) {
        movieRepo.deleteMovies(id);
    }
    // Tìm kiếm gần đúng (Search)
    public List<Movie> searchByName(String name) {
        return movieRepo.findAllMoviesByName(name);
    }
}
