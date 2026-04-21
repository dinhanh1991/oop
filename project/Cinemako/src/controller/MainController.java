package controller;

import util.ConsoleColor;

import java.util.Scanner;

public class MainController {
    private final Scanner sc = new Scanner(System.in);

    // Khởi tạo các Controller con
    private final MovieController movieController = new MovieController();
    private final ShowTimeController showTimeController = new ShowTimeController();
    private final BookingTicketController bookingController = new BookingTicketController();
    private final TicketController ticketController = new TicketController();

    public void run() {
        int choice;
        do {
            printMainMenu();
            choice = inputChoice();

            switch (choice) {
                case 1 -> movieController.run(); // Chuyển sang module quản lý phim
                case 2 -> showTimeController.run(); // chuyển sang module quản lý suất chiếu
                case 3 -> bookingController.handleBookingProcess(); // Đặt vé & xem sơ đồ ghế
                case 4 -> ticketController.handleCancel(); // Hủy vé
                case 5 -> ticketController.displayAllTickets(); // Xem lịch sử đặt vé
                case 0 -> System.out.println(ConsoleColor.YELLOW + "👋 Tạm biệt bạn! Chúc bạn một ngày tốt lành." + ConsoleColor.RESET);
                default -> System.out.println(ConsoleColor.RED + "❌ Lựa chọn không hợp lệ, vui lòng nhập lại!" + ConsoleColor.RESET);
            }
        } while (choice != 0);
    }

    private void printMainMenu() {
        System.out.println(ConsoleColor.BLUE + "\n==========================================" + ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLUE + "   HỆ THỐNG QUẢN LÝ RẠP CHIẾU PHIM 🎬" + ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLUE + "==========================================" + ConsoleColor.RESET);
        System.out.println("1. 🎞️ Quản lý Phim (Thêm, Sửa, Xóa, Tìm kiếm)");
        System.out.println("2. 📅 Quản lý Xuất chiếu (ShowTimes)");
        System.out.println("3. 🎟️ Đặt Vé & Xem Sơ Đồ Ghế");
        System.out.println("4. 🛠️ Hủy Vé");
        System.out.println("5. 📜 Lịch Sử Giao Dịch (Vé đã bán)");
        System.out.println("0. 🚪 Thoát chương trình");
        System.out.println("------------------------------------------");
    }

    private int inputChoice() {
        try {
            System.out.print("👉 Lựa chọn của bạn: ");
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Trả về giá trị lỗi nếu người dùng nhập chữ
        }
    }
}
