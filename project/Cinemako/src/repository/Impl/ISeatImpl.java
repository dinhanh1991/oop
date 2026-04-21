package repository.Impl;

import model.Seat;

import java.util.List;

public interface ISeatImpl {
    List<Seat> findByShowId(String showId);
    void updateSeatStatus(String showId, String seatNum, boolean status);
}
