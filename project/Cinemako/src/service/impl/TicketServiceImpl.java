package service.impl;

import model.Customer;
import model.ShowTime;
import model.Ticket;

import java.util.List;

public interface TicketServiceImpl {
    void bookTicket(Customer customer, ShowTime showTime, String seatNumber);
    void cancelTicket(String phone, String showId, String seatNum);
    List<Ticket> getAllTickets();
}
