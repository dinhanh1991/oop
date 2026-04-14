package model;

import util.ConsoleColor;

public class Ticket {
    private Customer customer;
    private ShowTime showTime;
    private Seat seat;

    public Ticket(Customer customer, ShowTime showTime, Seat seat) {
        this.customer = customer;
        this.showTime = showTime;
        this.seat = seat;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    public void print() {

        System.out.println(ConsoleColor.YELLOW + "\n========= VÉ XEM PHIM =========" + ConsoleColor.RESET);
        System.out.println("🎬 Phim: " + showTime.getMovie().getTitle());
        System.out.println("⏰ Suất: " + showTime.getTime());
        System.out.println("💺 Ghế: " + seat.getSeatNumber());
        System.out.println("--------------------------------");
        System.out.println("👤 Tên: " + customer.getName());
        System.out.println("📞 SĐT: " + customer.getPhone());
        System.out.println(ConsoleColor.YELLOW + "================================" + ConsoleColor.RESET);
    }
}
