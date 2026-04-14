package repository.impl;

import model.Ticket;
import java.util.List;

public interface TicketImpl {
// các phương thức khi làm việc với ticket
    List<Ticket> findAll();

    void addTicket(Ticket ticket);

    void deleteTicket(String phone, String seat);

    void updateTicket(String phone, String oldSeat, String newSeat);
}