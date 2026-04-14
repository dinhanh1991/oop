package view;

import util.InputValidator;

import java.util.Scanner;

public class TicketView {

    private Scanner sc = new Scanner(System.in);

    public String inputMovieId() {
        System.out.print("Chọn phim ID: ");
        return sc.nextLine();
    }

    public String inputTime() {
        while (true) {
            System.out.print("Nhập suất (vd 18:00): ");
            String time = sc.nextLine();

            if (InputValidator.isValidTime(time)) {
                return time;
            }
            System.out.println("❌ Sai định dạng giờ (HH:mm)");
        }
    }

    public String inputSeat() {
        while (true) {
            System.out.print("Chọn ghế: ");
            String seat = sc.nextLine().toUpperCase();

            if (InputValidator.isValidSeat(seat)) {
                return seat;
            }
            System.out.println("❌ Ghế không hợp lệ (A1 → C5)");
        }
    }

    public String inputName() {
        while (true) {
            System.out.print("Tên: ");
            String name = sc.nextLine();

            if (InputValidator.isValidName(name)) {
                return name;
            }
            System.out.println("❌ Tên không hợp lệ (không chứa số/ký tự lạ)");
        }
    }

    public String inputPhone() {
        while (true) {
            System.out.print("SĐT: ");
            String phone = sc.nextLine();

            if (InputValidator.isValidPhone(phone)) {
                return phone;
            }
            System.out.println("❌ SĐT không hợp lệ (10 số, bắt đầu bằng 0)");
        }
    }
}