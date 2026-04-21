package view;

import model.Movie;
import util.DataValidator;
import java.util.List;
import java.util.Scanner;

public class MovieView {
    private Scanner sc = new Scanner(System.in);

    public int viewMenu() {
        System.out.println("\n--- QUẢN LÝ PHIM ---");
        System.out.println("1. Danh sách phim");
        System.out.println("2. Thêm phim mới");
        System.out.println("3. Sửa phim");
        System.out.println("4. Xóa phim");
        System.out.println("5. Tìm kiếm phim");
        System.out.println("0. Quay lại");
        System.out.print("👉 Chọn: ");
        return Integer.parseInt(sc.nextLine());
    }

    public void displayMovies(List<Movie> movies) {
        if (movies.isEmpty()) {
            System.out.println("📭 Danh sách trống.");
        } else {
            System.out.printf("%-10s | %-25s | %-10s\n", "ID", "Tên Phim", "Thời lượng");
            movies.forEach(m -> System.out.printf("%-10s | %-25s | %-10d ph\n",
                    m.getId(), m.getTitle(), m.getDuration()));
        }
    }

    public Movie inputMovieData() {
        System.out.print("Nhập ID phim: "); String id = sc.nextLine();
        System.out.print("Nhập tên phim: "); String title = sc.nextLine();
        System.out.print("Nhập thời lượng (phút): "); int duration = Integer.parseInt(sc.nextLine());
        return new Movie(id, title, duration);
    }

    public String inputId() {
        System.out.print("👉 Nhập ID phim: ");
        return sc.nextLine();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
    public String inputSearchName() {
        System.out.print("👉 Nhập từ khóa: ");
        return sc.nextLine();
    }
}