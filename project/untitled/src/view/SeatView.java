package view;

import model.Seat;
import util.ConsoleColor;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SeatView {

    private Scanner sc = new Scanner(System.in);

    public void showSeats(List<Seat> list) {
        System.out.println("=== DANH SÁCH GHẾ ===");
        for (Seat s : list) {
            System.out.println(s.getSeatNumber() + " - " + (s.isBooked() ? "Đã đặt" : "Trống"));
        }
    }

    public String inputShowId() {
        System.out.print("Nhập showId (vd: 1_18:00): ");
        return sc.nextLine();
    }

    public String inputSeat() {
        System.out.print("Nhập số ghế: ");
        return sc.nextLine();
    }
    public void showSeatGrid(Map<String, Boolean> seats) {

        System.out.println(ConsoleColor.CYAN + "\n========= CINEMAKO CINEMA =========" + ConsoleColor.RESET);
        System.out.println("              SCREEN");
        System.out.println("==================================\n");

        int rows = 3;
        int cols = 5;

        System.out.print("    ");
        for (int i = 1; i <= cols; i++) {
            System.out.printf("%-4d", i);
        }
        System.out.println();

        for (int r = 0; r < rows; r++) {
            char rowChar = (char) ('A' + r);
            System.out.print(rowChar + "   ");

            for (int c = 1; c <= cols; c++) {
                String seat = rowChar + "" + c;
                boolean booked = seats.getOrDefault(seat, false);

                if (booked) {
                    System.out.print(ConsoleColor.RED + "[X]" + ConsoleColor.RESET);
                } else {
                    System.out.print(ConsoleColor.GREEN + "[O]" + ConsoleColor.RESET);
                }
            }
            System.out.println();
        }

        System.out.println("\n" + ConsoleColor.GREEN + "[O] Trống  "
                + ConsoleColor.RED + "[X] Đã đặt" + ConsoleColor.RESET);
    }
}