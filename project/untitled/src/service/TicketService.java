package service;

import model.Customer;
import model.Seat;
import model.ShowTime;
import model.Ticket;
import repository.TicketRepo;
import service.impl.TicketServiceImpl;

import java.util.List;

/**
 * Service xử lý logic liên quan đến Ticket (vé)
 * Là tầng trung gian giữa Controller và Repository
 */
public class TicketService implements TicketServiceImpl {

    // Kết nối tới tầng Repository để thao tác dữ liệu vé
    TicketRepo ticketRepo = new TicketRepo();

    /**
     * Đặt vé mới
     * @param c thông tin khách hàng
     * @param st thông tin suất chiếu
     * @param seat ghế được chọn
     * @return đối tượng Ticket đã tạo
     */
    @Override
    public Ticket bookTicket(Customer c, ShowTime st, Seat seat) {

        // Tạo đối tượng vé từ thông tin khách hàng, suất chiếu và ghế
        Ticket ticket = new Ticket(c, st, seat);

        // Lưu vé vào hệ thống (file / database)
        ticketRepo.addTicket(ticket);

        // Trả về vé vừa tạo
        return ticket;
    }

    /**
     * Lấy toàn bộ danh sách vé
     * @return danh sách Ticket
     */
    @Override
    public List<Ticket> getAll() {
        return ticketRepo.findAll();
    }

    /**
     * Hủy vé theo số điện thoại và số ghế
     * @param phone số điện thoại khách hàng
     * @param seat số ghế
     */
    @Override
    public void cancel(String phone, String seat) {
        ticketRepo.deleteTicket(phone, seat);
    }

    /**
     * Đổi ghế cho vé đã đặt
     * @param phone số điện thoại khách hàng
     * @param oldSeat ghế cũ
     * @param newSeat ghế mới
     */
    @Override
    public void changeSeat(String phone, String oldSeat, String newSeat) {
        ticketRepo.updateTicket(phone, oldSeat, newSeat);
    }
}