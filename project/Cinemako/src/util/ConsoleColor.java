package util;

public class ConsoleColor {
    // Reset về màu mặc định
    public static final String RESET = "\033[0m";

    // Các màu cơ bản
    public static final String RED = "\033[31m";    // Thường dùng cho thông báo LỖI
    public static final String GREEN = "\033[32m";  // Thường dùng cho THÀNH CÔNG
    public static final String YELLOW = "\033[33m"; // Thường dùng cho CẢNH BÁO
    public static final String BLUE = "\033[34m";   // Thường dùng cho MENU/TIÊU ĐỀ
    public static final String PURPLE = "\033[35m"; // Màu tím (nếu muốn nổi bật)
    public static final String CYAN = "\033[36m";   // Thường dùng cho THÔNG TIN/SƠ ĐỒ GHẾ
    public static final String WHITE = "\033[37m";  // Màu trắng

    // Nếu muốn chữ đậm hơn (Bold)
    public static final String BLUE_BOLD = "\033[1;34m";
}
