package service.impl;

import model.Seat;
import java.util.List;
import java.util.Map;

public interface SeatServiceImpl {

    List<Seat> getSeats(String showId);

    boolean isBooked(String showId, String seatNum);

    void bookSeat(String showId, String seatNum);

    void unBookSeat(String showId, String seatNum);
    void generateSeats(String showId);
    Map<String, Boolean> getSeatMap(String showId);
}