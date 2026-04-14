package view;

import model.Movie;

import java.util.List;
import java.util.Scanner;

public class MovieView {

    private Scanner sc = new Scanner(System.in);

    public void showMovies(List<Movie> list) {
        System.out.println("=== DANH SÁCH PHIM ===");
        for (Movie m : list) {
            System.out.println(m.getId() + " - " + m.getTitle() + " (" + m.getDuration() + "p)");
        }
    }

    public Movie inputMovie() {
        System.out.print("ID: ");
        String id = sc.nextLine();

        System.out.print("Tên: ");
        String name = sc.nextLine();

        System.out.print("Thời lượng: ");
        int d = Integer.parseInt(sc.nextLine());

        return new Movie(id, name, d);
    }

    public String inputId() {
        System.out.print("Nhập ID: ");
        return sc.nextLine();
    }
}