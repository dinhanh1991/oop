package repository.impl;

import model.Seat;
import java.util.List;

public interface SeatImpl {
    List<Seat> findByShow(String showId);
    void updateSeat(String showId, String seatNum, boolean status);
}