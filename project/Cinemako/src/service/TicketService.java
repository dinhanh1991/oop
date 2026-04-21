package service;

import model.Customer;
import model.Seat;
import model.ShowTime;
import model.Ticket;
import repository.SeatRepo;
import repository.TicketRepo;
import service.impl.TicketServiceImpl;

import java.util.List;

public class TicketService implements TicketServiceImpl {
    private TicketRepo ticketRepo = new TicketRepo();
    private SeatRepo seatRepo = new SeatRepo();
    @Override
    // ===================Đặt Vé Mới===================
    public void bookTicket(Customer customer, ShowTime showTime, String seatNumber) {
        // Kiểm tra lại lần nữa xem ghế còn trống không (đề phòng đa luồng)
        Seat selectedSeat = showTime.getSeats().stream()
                .filter(s -> s.getSeatNumber().equalsIgnoreCase(seatNumber) && !s.isBooked())
                .findFirst().orElse(null);
        if (selectedSeat != null) {
            Ticket newTicket = new Ticket(customer, showTime, selectedSeat);

            // Bước 1: Lưu vé vào file tickets.csv
            ticketRepo.save(newTicket);

            // Bước 2: Cập nhật trạng thái ghế thành true (đã đặt) trong file seats.csv
            seatRepo.updateSeatStatus(showTime.getId(), seatNumber, true);

            System.out.println("🎉 Đặt vé thành công!");
            newTicket.print(); // In vé ra màn hình ngay lập tại chỗ
        } else {
            System.out.println("❌ Lỗi: Ghế đã có người đặt hoặc không tồn tại.");

        }
    }

    @Override
    public void cancelTicket(String phone, String showId, String seatNum) {
        // Bước 1: Xóa vé trong file tickets.csv
        ticketRepo.deleteTicket(phone, seatNum);

        // Bước 2: Giải phóng ghế trong file seats.csv (chuyển về false)
        seatRepo.updateSeatStatus(showId, seatNum, false);

        System.out.println("✅ Quy trình hủy vé hoàn tất: Ghế " + seatNum + " hiện đã trống.");
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }
}
