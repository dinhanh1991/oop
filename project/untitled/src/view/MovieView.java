package view;

import model.Movie;

import java.util.List;
import java.util.Scanner;

/**
 * MovieView: hiển thị và nhập dữ liệu liên quan đến phim
 */
public class MovieView {

    private Scanner sc = new Scanner(System.in);

    /**
     * Hiển thị danh sách phim
     * @param list danh sách Movie
     */
    public void showMovies(List<Movie> list) {
        System.out.println("=== DANH SÁCH PHIM ===");
        for (Movie m : list) {
            System.out.println(m.getId() + " - " + m.getTitle() + " (" + m.getDuration() + "p)");
        }
    }

    /**
     * Nhập thông tin phim từ bàn phím
     * @return đối tượng Movie
     */
    public Movie inputMovie() {
        System.out.print("ID: ");
        String id = sc.nextLine();

        System.out.print("Tên: ");
        String name = sc.nextLine();

        System.out.print("Thời lượng: ");
        int d = Integer.parseInt(sc.nextLine());

        return new Movie(id, name, d);
    }

    /**
     * Nhập ID phim
     * @return chuỗi ID
     */
    public String inputId() {
        System.out.print("Nhập ID: ");
        return sc.nextLine();
    }
}