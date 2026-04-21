package repository.Impl;

import model.Ticket;

import java.util.List;

public interface ITicketImpl {
    void save(Ticket ticket);
    List<Ticket> findAll();
    void deleteTicket(String phone, String seatNum);
}
