package service;

import model.Customer;
import model.Seat;
import model.ShowTime;
import model.Ticket;
import repository.TicketRepo;
import service.impl.TicketServiceImpl;

import java.util.List;

public class TicketService implements TicketServiceImpl {
    TicketRepo ticketRepo = new TicketRepo();

    @Override
    public Ticket bookTicket(Customer c, ShowTime st, Seat seat) {
        Ticket ticket = new Ticket(c,st,seat);
         ticketRepo.addTicket(ticket);
        return  ticket;
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepo.findAll();
    }

    @Override
    public void cancel(String phone, String seat) {
        ticketRepo.deleteTicket(phone,seat);
    }

    @Override
    public void changeSeat(String phone, String oldSeat, String newSeat) {
        ticketRepo.updateTicket(phone,oldSeat,newSeat);
    }
}
