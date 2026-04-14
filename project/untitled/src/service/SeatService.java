package service;

import model.Seat;
import repository.SeatRepo;
import service.impl.SeatServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatService implements SeatServiceImpl {

    private SeatRepo seatRepo = new SeatRepo();

    @Override
    public List<Seat> getSeats(String showId) {
        return seatRepo.findByShow(showId);
    }

    @Override
    public boolean isBooked(String showId, String seatNum) {
        for (Seat s : getSeats(showId)) {
            if (s.getSeatNumber().equals(seatNum)) {
                return s.isBooked();
            }
        }
        return false;
    }

    @Override
    public void bookSeat(String showId, String seatNum) {
        seatRepo.updateSeat(showId, seatNum, true);
    }

    @Override
    public void unBookSeat(String showId, String seatNum) {
        seatRepo.updateSeat(showId, seatNum, false);
    }
    public void generateSeats(String showId) {
        for (char row = 'A'; row <= 'C'; row++) {
            for (int i = 1; i <= 5; i++) {
                seatRepo.updateSeat(showId, row + "" + i, false);
            }
        }
    }
    @Override
    public Map<String, Boolean> getSeatMap(String showId) {
        Map<String, Boolean> map = new HashMap<>();

        for (Seat s : getSeats(showId)) {
            map.put(s.getSeatNumber(), s.isBooked());
        }

        return map;
    }
}