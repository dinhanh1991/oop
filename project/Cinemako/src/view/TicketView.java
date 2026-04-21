package view;

import model.Ticket;
import java.util.List;
import java.util.Scanner;

public class TicketView {
    public void displayTicketList(List<Ticket> tickets) {
        if (tickets.isEmpty()) {
            System.out.println("📭 Hiện chưa có vé nào được bán ra.");
            return;
        }
        System.out.println("\n--- DANH SÁCH VÉ ĐÃ BÁN ---");
        for (Ticket t : tickets) {
            t.print(); // Sử dụng hàm print() đã viết trong Model Ticket
        }
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
    public boolean confirmCancel() {
        Scanner sc = new Scanner(System.in);
        System.out.print("⚠️ Bạn có chắc muốn hủy vé này không? (Y/N): ");
        String choice = sc.nextLine().trim().toUpperCase();

        return choice.equals("Y");
    }
}