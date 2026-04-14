package view;

import util.ConsoleColor;

import java.util.Scanner;

public class MenuView {

    private Scanner sc = new Scanner(System.in);

    public int mainMenu() {

        System.out.println(ConsoleColor.CYAN + "\n========= CINEMAKO =========" + ConsoleColor.RESET);
        System.out.println("1. 🎬 Xem phim");
        System.out.println("2. 🎟️ Đặt vé");
        System.out.println("3. ❌ Hủy vé");
        System.out.println("4. 🔄 Đổi ghế");
        System.out.println("5. ⚙️ Quản lý phim");
        System.out.println("6. 💺 Quản lý ghế");
        System.out.println("0. 🚪 Thoát");

        System.out.print("👉 Chọn: ");
        return Integer.parseInt(sc.nextLine());
    }

    public int movieMenu() {
        System.out.println("1. Thêm");
        System.out.println("2. Xóa");
        System.out.println("3. Sửa");

        return Integer.parseInt(sc.nextLine());
    }
}