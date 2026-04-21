package util;

import java.util.regex.Pattern;

public class DataValidator {
    // Regex cho Số điện thoại Việt Nam (10 số, bắt đầu bằng 0)
    private static final String PHONE_REGEX = "^0[0-9]{9}$";

    // Regex cho ID (Ví dụ: M-001 hoặc ST-01, không khoảng trắng)
    private static final String ID_REGEX = "^[a-zA-Z0-9_-]+$";
    // 1. Kiểm tra không được để trống
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // 2. Kiểm tra định dạng ID
    public static boolean isValidId(String id) {
        return Pattern.matches(ID_REGEX, id);
    }

    // 3. Kiểm tra định dạng Số điện thoại
    public static boolean isValidPhone(String phone) {
        return Pattern.matches(PHONE_REGEX, phone);
    }

    // 4. Kiểm tra thời lượng phim (phải > 0)
    public static boolean isValidDuration(int duration) {
        return duration > 0 && duration < 500;
    }
}
