package view;

import util.InputValidator;

import java.util.Scanner;

/**
 * TicketView: xử lý nhập dữ liệu liên quan đến đặt vé
 */
public class TicketView {

    private Scanner sc = new Scanner(System.in);

    /**
     * Nhập ID phim
     */
    public String inputMovieId() {
        System.out.print("Chọn phim ID: ");
        return sc.nextLine();
    }

    /**
     * Nhập suất chiếu (định dạng HH:mm)
     */
    public String inputTime() {
        while (true) {
            System.out.print("Nhập suất (vd 18:00): ");
            String time = sc.nextLine();

            // kiểm tra định dạng giờ
            if (InputValidator.isValidTime(time)) {
                return time;
            }
            System.out.println("❌ Sai định dạng giờ (HH:mm)");
        }
    }

    /**
     * Nhập số ghế (A1 → C5)
     */
    public String inputSeat() {
        while (true) {
            System.out.print("Chọn ghế: ");
            String seat = sc.nextLine().toUpperCase();

            // kiểm tra ghế hợp lệ
            if (InputValidator.isValidSeat(seat)) {
                return seat;
            }
            System.out.println("❌ Ghế không hợp lệ (A1 → C5)");
        }
    }

    /**
     * Nhập tên khách hàng
     */
    public String inputName() {
        while (true) {
            System.out.print("Tên: ");
            String name = sc.nextLine();

            // kiểm tra tên hợp lệ
            if (InputValidator.isValidName(name)) {
                return name;
            }
            System.out.println("❌ Tên không hợp lệ (không chứa số/ký tự lạ)");
        }
    }

    /**
     * Nhập số điện thoại
     */
    public String inputPhone() {
        while (true) {
            System.out.print("SĐT: ");
            String phone = sc.nextLine();

            // kiểm tra SĐT hợp lệ
            if (InputValidator.isValidPhone(phone)) {
                return phone;
            }
            System.out.println("❌ SĐT không hợp lệ (10 số, bắt đầu bằng 0)");
        }
    }
}