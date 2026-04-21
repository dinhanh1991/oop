package controller;

import model.Movie;
import model.ShowTime;
import repository.MovieRepo;
import service.ShowTimeService;
import view.ShowTimeView;

import java.util.List;
import java.util.Scanner;

public class ShowTimeController {
    private final Scanner sc = new Scanner(System.in);
    private final ShowTimeService showTimeService = new ShowTimeService();
    private final ShowTimeView showTimeView = new ShowTimeView();
    private final MovieRepo movieRepo = new MovieRepo();

    public void run() {
        int choice;

        do {
            System.out.println("\n===== QUẢN LÝ SUẤT CHIẾU =====");
            System.out.println("1. Hiển thị tất cả");
            System.out.println("2. Thêm suất chiếu");
            System.out.println("3. Cập nhật suất chiếu");
            System.out.println("4. Xóa suất chiếu");
            System.out.println("5. Tìm theo giờ");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> displayAll();
                case 2 -> addShowTime();
                case 3 -> updateShowTime();
                case 4 -> deleteShowTime();
                case 5 -> findByTime();
                case 0 -> System.out.println("Thoát...");
                default -> System.out.println("❌ Lựa chọn không hợp lệ");
            }

        } while (choice != 0);
    }

    public void displayAll() {
        showTimeView.displayShowTimes(showTimeService.getAll());
    }

    public void addShowTime() {
        List<Movie> movies = movieRepo.getAllMovies();
        ShowTime st = showTimeView.inputShowTime(movies);

        if (st.getMovie() == null) {
            System.out.println("❌ Phim không tồn tại!");
            return;
        }

        showTimeService.add(st);
        System.out.println("✅ Thêm thành công");
    }

    public void updateShowTime() {
        List<ShowTime> list = showTimeService.getAll();

        if (list.isEmpty()) {
            System.out.println("❌ Không có dữ liệu");
            return;
        }

        // Hiển thị có index
        showTimeView.displayShowTimesWithIndex(list);

        // chọn theo số
        int index = showTimeView.inputIndex(list.size());
        ShowTime old = list.get(index);

        // hiển thị lại
        System.out.println("👉 Bạn chọn:");
        showTimeView.displayShowTimes(List.of(old));

        // nhập mới
        List<Movie> movies = movieRepo.getAllMovies();
        ShowTime newSt = showTimeView.inputShowTime(movies);
        newSt.setId(old.getId());

        // confirm
        System.out.print("Bạn có chắc muốn cập nhật? (y/n): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            showTimeService.update(newSt);
            System.out.println("✅ Cập nhật thành công");
        } else {
            System.out.println("❌ Đã hủy");
        }
    }

    public void deleteShowTime() {
        List<ShowTime> list = showTimeService.getAll();

        if (list.isEmpty()) {
            System.out.println("❌ Không có dữ liệu");
            return;
        }

        showTimeView.displayShowTimesWithIndex(list);

        int index = showTimeView.inputIndex(list.size());
        ShowTime st = list.get(index);

        System.out.println("👉 Bạn chọn xóa:");
        showTimeView.displayShowTimes(List.of(st));

        System.out.print("Bạn có chắc muốn xóa? (y/n): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            showTimeService.delete(st.getId());
            System.out.println("✅ Xóa thành công");
        } else {
            System.out.println("❌ Đã hủy");
        }
    }

    public void findByTime() {
        String time = showTimeView.inputTime();
        List<ShowTime> result = showTimeService.findByTime(time);

        if (result.isEmpty()) {
            System.out.println("❌ Không tìm thấy suất chiếu");
        } else {
            showTimeView.displayShowTimes(result);
        }
    }
}