package view;

import model.Seat;
import util.ConsoleColor;
import util.DataValidator;

import java.util.List;
import java.util.Scanner;

public class BookingView {
    private Scanner sc = new Scanner(System.in);

    // Hiển thị sơ đồ ghế [O] [X]
    public void displaySeatGrid(List<Seat> seats, String showId) {
        System.out.println(ConsoleColor.CYAN + "\n      ===== SƠ ĐỒ GHẾ (SUẤT: " + showId + ") =====" + ConsoleColor.RESET);
        System.out.println("           ( MÀN HÌNH CHIẾU )");
        System.out.println("      ___________________________");
        System.out.println();

        System.out.print("      ");
        for (int i = 1; i <= 5; i++) System.out.print(i + "    ");
        System.out.println("\n");

        int count = 0;
        char rowLabel = 'A';
        for (int i = 0; i < 3; i++) {
            System.out.print(rowLabel + "   ");
            for (int j = 0; j < 5; j++) {
                if (count < seats.size()) {
                    Seat s = seats.get(count++);
                    String icon = s.isBooked() ? ConsoleColor.RED + "[X]" : ConsoleColor.GREEN + "[O]";
                    System.out.print(icon + "  " + ConsoleColor.RESET);
                }
            }
            System.out.println("\n");
            rowLabel++;
        }
    }

    public String inputShowId() {
        System.out.print("👉 Nhập mã Suất chiếu: ");
        return sc.nextLine();
    }

    public String inputSeatNumber() {
        System.out.print("👉 Chọn vị trí ghế (ví dụ A1): ");
        return sc.nextLine().toUpperCase();
    }

    public String[] inputCustomerInfo() {
        System.out.print("👉 Tên khách hàng: ");
        String name = sc.nextLine();
        String phone = inputPhoneValidated();
        return new String[]{name, phone};
    }
    public int inputSeatIndex() {
        System.out.print("👉 Chọn số ghế: ");
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
    public String inputPhoneValidated() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("👉 Nhập số điện thoại: ");
            String phone = sc.nextLine();

            if (!DataValidator.isNotEmpty(phone)) {
                System.out.println("❌ Không được để trống!");
            } else if (!DataValidator.isValidPhone(phone)) {
                System.out.println("❌ SĐT phải 10 số và bắt đầu bằng 0!");
            } else {
                return phone;
            }
        }
    }
    public String inputShowIdValidated() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("👉 Nhập mã suất chiếu: ");
            String id = sc.nextLine();

            if (!DataValidator.isNotEmpty(id)) {
                System.out.println("❌ Không được để trống!");
            } else if (!DataValidator.isValidId(id)) {
                System.out.println("❌ ID không hợp lệ!");
            } else {
                return id;
            }
        }
    }
    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
