package model;

import java.util.List;

public class ShowTime {
    private String id;
    private String time; // Thời Gian phim
    private Movie movie; // Phim ứng với thời gian
    private List<Seat> seats; // danh sách ghế ngồi

    public ShowTime(String id, String time, Movie movie, List<Seat> seats) {
        this.time = time;
        this.id = id;
        this.movie = movie;
        this.seats = seats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
