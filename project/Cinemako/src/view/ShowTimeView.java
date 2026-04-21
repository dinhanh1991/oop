package view;

import model.Movie;
import model.ShowTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShowTimeView {
    private Scanner sc = new Scanner(System.in);

    public void displayShowTimes(List<ShowTime> list) {
        System.out.println("\n" + "=".repeat(20) + " LỊCH CHIẾU PHIM " + "=".repeat(20));
        System.out.printf("%-10s | %-20s | %-10s | %-8s\n", "Mã Suất", "Tên Phim", "Giờ", "Thời lượng");
        System.out.println("-".repeat(55));

        for (ShowTime st : list) {
            System.out.printf("%-10s | %-20s | %-10s | %-8d ph\n",
                    st.getId(),
                    st.getMovie().getTitle(),
                    st.getTime(),
                    st.getMovie().getDuration());
        }
        System.out.println("=".repeat(55));
    }

    public String inputShowId() {
        System.out.print("👉 Nhập mã Suất chiếu để thực hiện: ");
        return sc.nextLine();
    }
    public ShowTime inputShowTime(List<Movie> movies) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập ID: ");
        String id = sc.nextLine();

        System.out.print("Nhập giờ (vd: 18:30): ");
        String time = sc.nextLine();

        System.out.println("Danh sách phim:");
        for (Movie m : movies) {
            System.out.println(m.getId() + " - " + m.getTitle());
        }

        System.out.print("Chọn ID phim: ");
        String movieId = sc.nextLine();

        Movie movie = movies.stream()
                .filter(m -> m.getId().equals(movieId))
                .findFirst()
                .orElse(null);

        return new ShowTime(id, time, movie, new ArrayList<>());
    }
    public String inputTime() {
        System.out.print("Nhập giờ cần tìm: ");
        return sc.nextLine();
    }
    public void displayShowTimesWithIndex(List<ShowTime> list) {
        System.out.println("\n===== DANH SÁCH SUẤT CHIẾU =====");

        for (int i = 0; i < list.size(); i++) {
            ShowTime st = list.get(i);
            System.out.printf("%d. %s | %s | %s (%d phút)\n",
                    i + 1,
                    st.getId(),
                    st.getTime(),
                    st.getMovie().getTitle(),
                    st.getMovie().getDuration());
        }
    }
    public int inputIndex(int max) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            try {
                System.out.print("👉 Chọn (1 - " + max + "): ");
                choice = Integer.parseInt(sc.nextLine());

                if (choice >= 1 && choice <= max) {
                    return choice - 1; // trả về index
                }
            } catch (Exception ignored) {}

            System.out.println("❌ Lựa chọn không hợp lệ");
        }
    }
}
