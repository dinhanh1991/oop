package controller;

import model.*;
import service.*;
import util.ConsoleColor;
import view.*;

import java.util.*;

public class MainController {

    private MovieService movieService = new MovieService();
    private SeatService seatService = new SeatService();
    private TicketService ticketService = new TicketService();

    private MovieView movieView = new MovieView();
    private TicketView ticketView = new TicketView();
    private MenuView menuView = new MenuView();
    private SeatView seatView = new SeatView();

    private Scanner sc = new Scanner(System.in); // dùng chung

    public void run() {

        while (true) {

            int choice = menuView.mainMenu();

            switch (choice) {
                case 1 -> showMovies();
                case 2 -> bookTicket();
                case 3 -> cancelTicket();
                case 4 -> changeSeat();
                case 5 -> movieMenu();
                case 6 -> seatMenu();
                case 0 -> System.exit(0);
            }
        }
    }

    private void showMovies() {
        movieView.showMovies(movieService.getAll());
    }

    private void bookTicket() {

        showMovies();

        String movieId = ticketView.inputMovieId();
        String time = ticketView.inputTime();
        String showId = movieId + "_" + time;

        // kiểm tra phim tồn tại
        Movie movie = movieService.getAll()
                .stream()
                .filter(m -> m.getId().equals(movieId))
                .findFirst()
                .orElse(null);

        if (movie == null) {
            System.out.println("Phim không tồn tại!");
            return;
        }

        // hiển thị ghế đúng showId
        Map<String, Boolean> seatMap = seatService.getSeatMap(showId);
        seatView.showSeatGrid(seatMap);

        String seatNum = ticketView.inputSeat();

        if (seatService.isBooked(showId, seatNum)) {
            System.out.println(ConsoleColor.RED + "❌ Ghế này đã có người đặt!" + ConsoleColor.RESET);
            return;
        }

        String name = ticketView.inputName();
        String phone = ticketView.inputPhone();

        ShowTime st = new ShowTime(showId, time, movie, new ArrayList<>());
        Seat seat = new Seat(seatNum, true);
        Customer c = new Customer(name, phone);
        System.out.print("👉 Xác nhận đặt vé (y/n): ");
        String confirm = sc.nextLine();

        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("❌ Đã hủy!");
            return;
        }

        seatService.bookSeat(showId, seatNum);

        Ticket t = ticketService.bookTicket(c, st, seat);
        t.print();
    }

    private void cancelTicket() {

        String phone = ticketView.inputPhone();
        String seat = ticketView.inputSeat();

        ticketService.cancel(phone, seat);
        System.out.println("Đã hủy!");
    }

    private void changeSeat() {

        String phone = ticketView.inputPhone();

        System.out.print("Ghế cũ: ");
        String oldSeat = sc.nextLine();

        System.out.print("Ghế mới: ");
        String newSeat = sc.nextLine();

        // kiểm tra ghế mới
        if (seatService.isBooked("", newSeat)) {
            System.out.println("Ghế mới đã có người đặt!");
            return;
        }

        ticketService.changeSeat(phone, oldSeat, newSeat);

        System.out.println("Đổi ghế thành công!");
    }

    private void movieMenu() {

        int c = menuView.movieMenu();

        switch (c) {
            case 1 -> movieService.add(movieView.inputMovie());
            case 2 -> movieService.delete(movieView.inputId());
            case 3 -> movieService.update(movieView.inputMovie());
        }
    }

    private void seatMenu() {

        String showId = seatView.inputShowId();

        System.out.println("1. Xem ghế");
        System.out.println("2. Reset ghế");
        System.out.println("3. Tạo ghế");
        int c = sc.nextInt();
        sc.nextLine(); // tránh lỗi input

        switch (c) {

            case 1 -> {
                List<Seat> seats = seatService.getSeats(showId);
                if (seats == null || seats.isEmpty()) {
                    System.out.println("Chưa có ghế!");
                    return;
                }
                Map<String, Boolean> seatMap = seatService.getSeatMap(showId);
                seatView.showSeatGrid(seatMap);
               // seatView.showSeats(seats);
            }

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

            case 3 -> {
                seatService.generateSeats(showId);
                System.out.println("Đã tạo ghế!");
            }
        }
    }
}