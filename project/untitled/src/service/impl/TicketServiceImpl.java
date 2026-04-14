package service.impl;

import model.Customer;
import model.Seat;
import model.ShowTime;
import model.Ticket;

import java.util.List;

public interface TicketServiceImpl {
    Ticket bookTicket(Customer c, ShowTime st, Seat seat);
    List<Ticket> getAll();
    void cancel(String phone, String seat);
    void changeSeat(String phone, String oldSeat, String newSeat);
}
