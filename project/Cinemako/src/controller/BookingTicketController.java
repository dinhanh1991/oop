package controller;

import model.Seat;
import view.BookingView;
import model.Customer;
import model.ShowTime;
import service.SeatService;
import service.ShowTimeService;
import service.TicketService;

import java.util.List;

public class BookingTicketController {
    private ShowTimeService showTimeService = new ShowTimeService();
    private SeatService seatService = new SeatService();
    private TicketService ticketService = new TicketService();
    private BookingView bookingView = new BookingView();

    public void handleBookingProcess() {
        // 1. Hiển thị suất chiếu
        showTimeService.displayShowTimes();
        String stId = bookingView.inputShowIdValidated();
        ShowTime st = showTimeService.findById(stId);

        if (st == null) {
            bookingView.showMessage("❌ Suất chiếu không tồn tại!");
            return;
        }

        // 2. Hiển thị ghế
        List<Seat> seats = seatService.getSeatsByShow(stId);
        bookingView.displaySeatGrid(seats, stId);

        // 3. Chọn ghế (lặp đến khi hợp lệ)
        String seatNum;

        while (true) {
            int index = bookingView.inputSeatIndex();
            seatNum = seatService.getSeatByIndex(stId, index);

            if (seatNum == null) {
                bookingView.showMessage("❌ Ghế không hợp lệ! Vui lòng chọn lại.");
                continue;
            }

            if (!seatService.isSeatAvailable(stId, seatNum)) {
                bookingView.showMessage("❌ Ghế đã được đặt! Vui lòng chọn ghế khác.");
                continue;
            }

            break; // hợp lệ thì thoát
        }

        // 4. Nhập thông tin khách
        String[] info = bookingView.inputCustomerInfo();
        Customer customer = new Customer(info[0], info[1]);

        // 5. Đặt vé
        ticketService.bookTicket(customer, st, seatNum);
    }
}
