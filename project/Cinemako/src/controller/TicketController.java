package controller;

import model.Ticket;
import service.TicketService;
import view.TicketView;
import java.util.List;
import java.util.Scanner;

public class TicketController {
    private final TicketService ticketService = new TicketService();
    private final TicketView ticketView = new TicketView();
    private final Scanner sc = new Scanner(System.in);

    // Hiển thị tất cả vé đã bán
    public void displayAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        ticketView.displayTicketList(tickets);
    }

    // Xử lý logic hủy vé
    public void handleCancel() {
        ticketView.showMessage("\n--- HỦY VÉ XEM PHIM ---");

        // 1. Lấy tất cả vé
        List<Ticket> allTickets = ticketService.getAllTickets();

        if (allTickets.isEmpty()) {
            ticketView.showMessage("❌ Không có vé nào để hủy.");
            return;
        }

        // 2. Lấy danh sách SĐT (không trùng)
        List<String> phones = allTickets.stream()
                .map(t -> t.getCustomer().getPhone())
                .distinct()
                .toList();

        // 3. Hiển thị danh sách khách
        System.out.println("📞 Danh sách khách hàng:");
        for (int i = 0; i < phones.size(); i++) {
            System.out.println((i + 1) + ". " + phones.get(i));
        }

        // 4. Chọn khách
        System.out.print("👉 Chọn khách: ");
        int choice = Integer.parseInt(sc.nextLine());

        if (choice < 1 || choice > phones.size()) {
            ticketView.showMessage("❌ Lựa chọn không hợp lệ!");
            return;
        }

        String selectedPhone = phones.get(choice - 1);

        // 5. Lấy vé của khách đó
        List<Ticket> userTickets = allTickets.stream()
                .filter(t -> t.getCustomer().getPhone().equals(selectedPhone))
                .toList();

        // 6. Hiển thị vé
        System.out.println("\n🎟️ Vé của khách:");
        for (int i = 0; i < userTickets.size(); i++) {
            System.out.print((i + 1) + ". ");
            userTickets.get(i).print();
        }

        // 7. Chọn vé cần hủy
        System.out.print("👉 Chọn vé cần hủy: ");
        int ticketChoice = Integer.parseInt(sc.nextLine());

        if (ticketChoice < 1 || ticketChoice > userTickets.size()) {
            ticketView.showMessage("❌ Không hợp lệ!");
            return;
        }

        Ticket selected = userTickets.get(ticketChoice - 1);
        // 👉 confirm trước khi hủy
        boolean isConfirmed = ticketView.confirmCancel();

        if (!isConfirmed) {
            ticketView.showMessage("❌ Đã hủy thao tác.");
            return;
        }

// 👉 nếu OK thì mới hủy
        ticketService.cancelTicket(
                selected.getCustomer().getPhone(),
                selected.getShowTime().getId(),
                selected.getSeat().getSeatNumber()
        );
    }
}