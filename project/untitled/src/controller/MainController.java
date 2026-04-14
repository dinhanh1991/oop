package controller;

import model.*;
import service.*;
import util.ConsoleColor;
import view.*;

import java.util.*;

/**
 * Controller chính của chương trình
 * Điều phối luồng chạy giữa View (giao diện) và Service (xử lý logic)
 */
public class MainController {

    // ================= SERVICE =================
    private MovieService movieService = new MovieService();
    private SeatService seatService = new SeatService();
    private TicketService ticketService = new TicketService();

    // ================= VIEW =================
    private MovieView movieView = new MovieView();
    private TicketView ticketView = new TicketView();
    private MenuView menuView = new MenuView();
    private SeatView seatView = new SeatView();

    // Scanner dùng chung cho toàn bộ chương trình
    private Scanner sc = new Scanner(System.in);

    /**
     * Hàm chạy chính của chương trình (menu chính)
     */
    public void run() {

        while (true) {

            // Hiển thị menu và lấy lựa chọn
            int choice = menuView.mainMenu();

            // Điều hướng chức năng theo lựa chọn
            switch (choice) {
                case 1 -> showMovies();     // xem danh sách phim
                case 2 -> bookTicket();    // đặt vé
                case 3 -> cancelTicket();  // hủy vé
                case 4 -> changeSeat();    // đổi ghế
                case 5 -> movieMenu();     // quản lý phim
                case 6 -> seatMenu();      // quản lý ghế
                case 0 -> System.exit(0);  // thoát chương trình
            }
        }
    }

    /**
     * Hiển thị danh sách phim
     */
    private void showMovies() {
        movieView.showMovies(movieService.getAll());
    }

    /**
     * Chức năng đặt vé
     */
    private void bookTicket() {

        // Hiển thị danh sách phim trước khi chọn
        showMovies();

        // Nhập thông tin phim và suất chiếu
        String movieId = ticketView.inputMovieId();
        String time = ticketView.inputTime();
        String showId = movieId + "_" + time; // tạo mã suất chiếu

        // Kiểm tra phim có tồn tại không
        Movie movie = movieService.getAll()
                .stream()
                .filter(m -> m.getId().equals(movieId))
                .findFirst()
                .orElse(null);

        if (movie == null) {
            System.out.println("Phim không tồn tại!");
            return;
        }

        // Hiển thị sơ đồ ghế theo suất chiếu
        Map<String, Boolean> seatMap = seatService.getSeatMap(showId);
        seatView.showSeatGrid(seatMap);

        // Nhập ghế muốn đặt
        String seatNum = ticketView.inputSeat();

        // Kiểm tra ghế đã bị đặt chưa
        if (seatService.isBooked(showId, seatNum)) {
            System.out.println(ConsoleColor.RED + "❌ Ghế này đã có người đặt!" + ConsoleColor.RESET);
            return;
        }

        // Nhập thông tin khách hàng
        String name = ticketView.inputName();
        String phone = ticketView.inputPhone();

        // Tạo đối tượng liên quan
        ShowTime st = new ShowTime(showId, time, movie, new ArrayList<>());
        Seat seat = new Seat(seatNum, true); // true = đã đặt
        Customer c = new Customer(name, phone);

        // Xác nhận trước khi đặt
        System.out.print("👉 Xác nhận đặt vé (y/n): ");
        String confirm = sc.nextLine();

        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("❌ Đã hủy!");
            return;
        }

        // Cập nhật trạng thái ghế
        seatService.bookSeat(showId, seatNum);

        // Lưu vé
        Ticket t = ticketService.bookTicket(c, st, seat);

        // In vé
        t.print();
    }

    /**
     * Chức năng hủy vé
     */
    private void cancelTicket() {

        // Nhập thông tin để xác định vé
        String phone = ticketView.inputPhone();
        String seat = ticketView.inputSeat();

        // Xóa vé
        ticketService.cancel(phone, seat);

        System.out.println("Đã hủy!");
    }

    /**
     * Chức năng đổi ghế
     */
    private void changeSeat() {

        String phone = ticketView.inputPhone();

        System.out.print("Ghế cũ: ");
        String oldSeat = sc.nextLine();

        System.out.print("Ghế mới: ");
        String newSeat = sc.nextLine();

        // ⚠️ Lưu ý: đang thiếu showId → check chưa chính xác
        if (seatService.isBooked("", newSeat)) {
            System.out.println("Ghế mới đã có người đặt!");
            return;
        }

        // Cập nhật vé
        ticketService.changeSeat(phone, oldSeat, newSeat);

        System.out.println("Đổi ghế thành công!");
    }

    /**
     * Menu quản lý phim (CRUD)
     */
    private void movieMenu() {

        int c = menuView.movieMenu();

        switch (c) {
            case 1 -> movieService.add(movieView.inputMovie());   // thêm phim
            case 2 -> movieService.delete(movieView.inputId());   // xóa phim
            case 3 -> movieService.update(movieView.inputMovie()); // sửa phim
        }
    }

    /**
     * Menu quản lý ghế
     */
    private void seatMenu() {

        // Nhập mã suất chiếu
        String showId = seatView.inputShowId();

        System.out.println("1. Xem ghế");
        System.out.println("2. Reset ghế");
        System.out.println("3. Tạo ghế");

        int c = sc.nextInt();
        sc.nextLine(); // tránh lỗi input

        switch (c) {

            // Xem sơ đồ ghế
            case 1 -> {
                List<Seat> seats = seatService.getSeats(showId);

                if (seats == null || seats.isEmpty()) {
                    System.out.println("Chưa có ghế!");
                    return;
                }

                Map<String, Boolean> seatMap = seatService.getSeatMap(showId);
                seatView.showSeatGrid(seatMap);
            }

            // Reset toàn bộ ghế về chưa đặt
            case 2 -> {
                List<Seat> seats = seatService.getSeats(showId);

                if (seats == null || seats.isEmpty()) {
                    System.out.println("Chưa có ghế để reset!");
                    return;
                }

                for (Seat s : seats) {
                    seatService.unBookSeat(showId, s.getSeatNumber());
                }

                System.out.println("Đã reset ghế!");
            }

            // Tạo ghế mới (A1 → C5)
            case 3 -> {
                seatService.generateSeats(showId);
                System.out.println("Đã tạo ghế!");
            }
        }
    }
}