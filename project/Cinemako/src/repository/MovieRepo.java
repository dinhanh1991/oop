package repository;

import model.Movie;
import repository.Impl.IMovieImp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MovieRepo implements IMovieImp {
    private final String PATH = "D:\\learning\\oop\\project\\Cinemako\\src\\data\\movies.csv";
    public MovieRepo() {
        ensureFileExists();
    }
    // ================= CHỨC NĂNG TỰ TẠO FILE =================
    private void ensureFileExists() {
        try {
            File file = new File(PATH);
            // 1. Kiểm tra và tạo thư mục nếu chưa có
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            // 2. Kiểm tra và tạo file nếu chưa có
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("ℹ️ Hệ thống: Đã tự động tạo file movies.csv mới.");
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi: Không thể khởi tạo file dữ liệu phim.");
        }
    }
    // =====================LẤY DANH SÁCH PHIM ================
    public List<Movie> getAllMovies(){
        ensureFileExists();
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                   movies.add(new Movie(data[0],data[1],Integer.parseInt(data[2]) ));
                }
            }
        }catch (Exception e) {
            System.out.println("❌ Lỗi: Không tìm thấy file movies.csv");
            e.printStackTrace();
        }
        return movies;
}
    @Override
    public void addMovies(Movie movie) {
        ensureFileExists();
        try (FileWriter fw = new FileWriter(PATH, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            String data = String.format("%s,%s,%d%s",
                    movie.getId(),
                    movie.getTitle(),
                    movie.getDuration(),
                    System.lineSeparator());
            bw.write(data);
        } catch (IOException e) {
            System.out.println("❌ Lỗi: Không thể thêm phim.");
        }

    }
    // ==================SỬA THÔNG TIN PHIM===============
    @Override
    public void updateMovies(Movie updatedMovie) {
        List<Movie> movies = getAllMovies();
        boolean found = false;
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getId().equals(updatedMovie.getId())) {
                movies.set(i, updatedMovie); // Thay thế đối tượng cũ bằng đối tượng mới
                found = true;
                break;
            }
        }
        if (found) {
            saveAll(movies);
        } else {
            System.out.println("❌ Không tìm thấy phim ID: " + updatedMovie.getId());
        }
    }

    @Override
    public void deleteMovies(String id) {
        List<Movie> movies = getAllMovies();
        List<Movie> filteredMovies = new ArrayList<>();

        boolean found = false;

        for (Movie m : movies) {
            if (m.getId() != null && m.getId().trim().equalsIgnoreCase(id.trim())) {
                found = true;
            } else {
                filteredMovies.add(m);
            }
        }

        if (found) {
            saveAll(filteredMovies);
            System.out.println("✅ Đã xóa phim thành công.");
        } else {
            System.out.println("❌ Không tìm thấy phim để xóa.");
        }
    }

    @Override
    public List<Movie> findAllMoviesByName(String name) {
        return getAllMovies().stream()
                .filter(m -> m.getTitle().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    //================= Hàm phụ trợ để ghi đè file================
    private void saveAll(List<Movie> movies) {
        try (FileWriter fw = new FileWriter(PATH);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (Movie m : movies) {
                bw.write(String.format("%s,%s,%d\n", m.getId(), m.getTitle(), m.getDuration()));
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi: Không thể cập nhật dữ liệu.");
        }
    }
}
