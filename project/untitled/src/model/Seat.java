package model;

public class Seat {
    private String seatNumber;// Số thứ tự ghế
    private boolean booked;// Trạng thái nếu false là ghế trống và true là đã có người ngồi
    public Seat(String seatNumber, boolean booked) {
        this.seatNumber = seatNumber;
        this.booked = booked;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
