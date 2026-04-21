package service;

import model.Seat;
import repository.SeatRepo;
import service.impl.SeatServiceImpl;
import util.ConsoleColor;

import java.util.List;

public class SeatService implements SeatServiceImpl {
    private SeatRepo seatRepo = new SeatRepo();

    // 1. Lấy danh sách ghế của một suất chiếu cụ thể
    public List<Seat> getSeatsByShow(String showId) {
        return seatRepo.findByShowId(showId);
    }

    // 2. Chức năng quan trọng: Vẽ sơ đồ ghế ra màn hình Console
    public void displaySeatGrid(String showId) {
        List<Seat> seats = getSeatsByShow(showId);

        if (seats.isEmpty()) {
            System.out.println("❌ Suất chiếu này chưa được cấu hình sơ đồ ghế!");
            return;
        }

        System.out.println(ConsoleColor.CYAN + "\n      ===== SƠ ĐỒ GHẾ (SUẤT: " + showId + ") =====" + ConsoleColor.RESET);
        System.out.println("           ( MÀN HÌNH CHIẾU )");
        System.out.println("      ___________________________");
        System.out.println();

        // In số cột 1 đến 5
        System.out.print("      ");
        for (int i = 1; i <= 5; i++) System.out.print(i + "    ");
        System.out.println("\n");

        int count = 0;
        char rowLabel = 'A';

        // Giả sử rạp mặc định là 3 hàng A, B, C. Mỗi hàng 5 ghế.
        for (int i = 0; i < 3; i++) {
            System.out.print(rowLabel + "   ");
            for (int j = 0; j < 5; j++) {
                if (count < seats.size()) {
                    Seat s = seats.get(count++);
                    if (s.isBooked()) {
                        // Ghế đã đặt: Màu đỏ [X]
                        System.out.print(ConsoleColor.RED + "[X]  " + ConsoleColor.RESET);
                    } else {
                        // Ghế trống: Màu xanh [O]
                        System.out.print(ConsoleColor.GREEN + "[O]  " + ConsoleColor.RESET);
                    }
                }
            }
            System.out.println("\n");
            rowLabel++;
        }

        System.out.println("Ghi chú: " + ConsoleColor.GREEN + "[O]: Trống" + ConsoleColor.RESET
                + " | " + ConsoleColor.RED + "[X]: Đã đặt" + ConsoleColor.RESET);
        System.out.println("========================================\n");
    }

    // 3. Kiểm tra xem một ghế cụ thể có còn trống không
    public boolean isSeatAvailable(String showId, String seatNumber) {
        List<Seat> seats = getSeatsByShow(showId);
        for (Seat s : seats) {
            if (s.getSeatNumber().equalsIgnoreCase(seatNumber)) {
                return !s.isBooked();
            }
        }
        return false;
    }
    public String getSeatByIndex(String showId, int index) {
        List<Seat> seats = getSeatsByShow(showId);

        if (index < 1 || index > seats.size()) return null;

        return seats.get(index - 1).getSeatNumber();
    }
}
