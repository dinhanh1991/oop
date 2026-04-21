package controller;

import model.Movie;
import service.MovieService;
import view.MovieView;

import java.util.List;

public class MovieController {
    private MovieService movieService = new MovieService();
    private MovieView movieView = new MovieView();

    public void run() {
        int choice;
        do {
            choice = movieView.viewMenu();
            switch (choice) {
                case 1 -> movieView.displayMovies(movieService.getAll());
                case 2 -> handleAdd();
                case 3 -> handleUpdate();
                case 4 -> handleDelete();
                case 5 -> handleSearch();
            }
        } while (choice != 0);
    }

    private void handleAdd() {
        Movie m = movieView.inputMovieData();
        movieService.addMovie(m);
        movieView.showMessage("✅ Thực hiện xong!");
    }

    private void handleDelete() {
        List<Movie> movies = movieService.getAll();

        if (movies.isEmpty()) {
            movieView.showMessage("📭 Không có phim để xóa.");
            return;
        }

        // B1: Hiển thị danh sách
        movieView.showMessage("📋 Danh sách phim:");
        movieView.displayMovies(movies);

        // B2: Nhập ID từ danh sách
        String id = movieView.inputId();

        // B3: Xóa
        movieService.deleteMovie(id);
    }

    // --- Xử lý Cập nhật Phim ---
    private void handleUpdate() {
        List<Movie> movies = movieService.getAll();

        if (movies.isEmpty()) {
            movieView.showMessage("📭 Không có phim để sửa.");
            return;
        }

        // B1: Hiển thị danh sách
        movieView.showMessage("📋 Danh sách phim:");
        movieView.displayMovies(movies);

        // B2: Chọn ID
        String id = movieView.inputId();

        // B3: Kiểm tra tồn tại (nên dùng trim + ignoreCase)
        Movie existingMovie = movies.stream()
                .filter(m -> m.getId().trim().equalsIgnoreCase(id.trim()))
                .findFirst()
                .orElse(null);

        if (existingMovie != null) {
            movieView.showMessage("🔔 Nhập thông tin mới:");

            Movie updatedMovie = movieView.inputMovieData();
            updatedMovie.setId(existingMovie.getId()); // giữ ID cũ

            movieService.updateMovie(updatedMovie);
        } else {
            movieView.showMessage("❌ Không tìm thấy phim.");
        }
    }

    // --- Xử lý Tìm kiếm theo tên gần đúng ---
    private void handleSearch() {
        //  Gọi View để lấy từ khóa tìm kiếm
        movieView.showMessage("🔍 Nhập tên phim cần tìm (hoặc một phần tên): ");
        String keyword = movieView.inputSearchName();

        // Gọi Service thực hiện lọc dữ liệu
        List<Movie> results = movieService.searchByName(keyword);

        // Gọi View hiển thị kết quả
        movieView.showMessage("📊 Kết quả tìm kiếm cho '" + keyword + "':");
        movieView.displayMovies(results);
    }
}
