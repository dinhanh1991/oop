package view;

import model.Seat;
import util.ConsoleColor;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * SeatView: hiển thị và nhập dữ liệu liên quan đến ghế
 */
public class SeatView {

    private Scanner sc = new Scanner(System.in);

    /**
     * Hiển thị danh sách ghế dạng list
     * @param list danh sách Seat
     */
    public void showSeats(List<Seat> list) {
        System.out.println("=== DANH SÁCH GHẾ ===");
        for (Seat s : list) {
            System.out.println(s.getSeatNumber() + " - " + (s.isBooked() ? "Đã đặt" : "Trống"));
        }
    }

    /**
     * Nhập mã suất chiếu (showId)
     * @return chuỗi showId
     */
    public String inputShowId() {
        System.out.print("Nhập showId (vd: 1_18:00): ");
        return sc.nextLine();
    }

    /**
     * Nhập số ghế (A1, B2,...)
     * @return chuỗi số ghế
     */
    public String inputSeat() {
        System.out.print("Nhập số ghế: ");
        return sc.nextLine();
    }

    /**
     * Hiển thị sơ đồ ghế dạng ma trận (rạp chiếu phim)
     * [O] = ghế trống, [X] = ghế đã đặt
     * @param seats Map<seatNumber, trạng thái>
     */
    public void showSeatGrid(Map<String, Boolean> seats) {

        System.out.println(ConsoleColor.CYAN + "\n========= CINEMAKO CINEMA =========" + ConsoleColor.RESET);
        System.out.println("              SCREEN");
        System.out.println("==================================\n");

        int rows = 3; // số hàng ghế (A, B, C)
        int cols = 5; // số cột ghế (1 → 5)

        // In tiêu đề cột
        System.out.print("    ");
        for (int i = 1; i <= cols; i++) {
            System.out.printf("%-4d", i);
        }
        System.out.println();

        // In từng hàng ghế
        for (int r = 0; r < rows; r++) {
            char rowChar = (char) ('A' + r);
            System.out.print(rowChar + "   ");

            for (int c = 1; c <= cols; c++) {
                String seat = rowChar + "" + c;
                boolean booked = seats.getOrDefault(seat, false); // mặc định chưa đặt

                // Hiển thị trạng thái ghế
                if (booked) {
                    System.out.print(ConsoleColor.RED + "[X]" + ConsoleColor.RESET);
                } else {
                    System.out.print(ConsoleColor.GREEN + "[O]" + ConsoleColor.RESET);
                }
            }
            System.out.println();
        }

        // Chú thích
        System.out.println("\n" + ConsoleColor.GREEN + "[O] Trống  "
                + ConsoleColor.RED + "[X] Đã đặt" + ConsoleColor.RESET);
    }
}