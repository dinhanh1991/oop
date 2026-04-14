package util;

public class InputValidator {
    public static boolean isValidPhone(String phone) {
        return phone.matches("^0\\d{9}$");
    }

    // Tên: chỉ chữ + khoảng trắng
    public static boolean isValidName(String name) {
        return name.matches("^[a-zA-ZÀ-ỹ\\s]+$");
    }

    // Ghế: A1 -> C5
    public static boolean isValidSeat(String seat) {
        return seat.matches("^[A-C][1-5]$");
    }

    // Giờ: 18:00
    public static boolean isValidTime(String time) {
        return time.matches("^([01]\\d|2[0-3]):[0-5]\\d$");
    }

}
