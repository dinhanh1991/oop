package service.impl;

import model.Seat;

import java.util.List;

public interface SeatServiceImpl {
    List<Seat> getSeatsByShow(String showId);
    void displaySeatGrid(String showId);
    boolean isSeatAvailable(String showId, String seatNumber);
}
