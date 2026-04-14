package repository;

import model.*;
import repository.impl.TicketImpl;

import java.io.*;
import java.util.*;

public class TicketRepo implements TicketImpl {

    private final String DATA = "D:\\learning\\oob\\untitled\\src\\data\\tickets.csv";

    // ================= READ =================
    @Override
    public List<Ticket> findAll() {

        List<Ticket> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(DATA))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] s = line.split(",");

                // tạo object từ CSV
                Customer c = new Customer(s[0], s[1]);
                Movie m = new Movie("M", s[2], 0);
                ShowTime st = new ShowTime("S", s[3], m, new ArrayList<>());
                Seat seat = new Seat(s[4], true);

                list.add(new Ticket(c, st, seat));
            }

        } catch (Exception e) {
            System.out.println("Lỗi đọc file!");
        }

        return list;
    }

    // ================= CREATE =================
    @Override
    public void addTicket(Ticket t) {

        try (FileWriter fw = new FileWriter(DATA, true)) {

            String line =
                    t.getCustomer().getName() + "," +
                            t.getCustomer().getPhone() + "," +
                            t.getShowTime().getMovie().getTitle() + "," +
                            t.getShowTime().getTime() + "," +
                            t.getSeat().getSeatNumber();

            fw.write(line + "\n");

        } catch (Exception e) {
            System.out.println("Lỗi ghi file!");
        }
    }

    // ================= DELETE =================
    @Override
    public void deleteTicket(String phone, String seat) {

        List<String[]> list = readRaw();

        try (FileWriter fw = new FileWriter(DATA)) {

            for (String[] r : list) {

                if (!(r[1].equals(phone) && r[4].equals(seat))) {
                    fw.write(String.join(",", r) + "\n");
                }
            }

        } catch (Exception e) {
            System.out.println("Lỗi xóa!");
        }
    }

    // ================= UPDATE =================
    @Override
    public void updateTicket(String phone, String oldSeat, String newSeat) {

        List<String[]> list = readRaw();

        try (FileWriter fw = new FileWriter(DATA)) {

            for (String[] r : list) {

                if (r[1].equals(phone) && r[4].equals(oldSeat)) {
                    r[4] = newSeat;
                }

                fw.write(String.join(",", r) + "\n");
            }

        } catch (Exception e) {
            System.out.println("Lỗi update!");
        }
    }

    // ================= HELPER =================
    private List<String[]> readRaw() {

        List<String[]> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(DATA))) {

            String line;
            while ((line = br.readLine()) != null) {
                list.add(line.split(","));
            }

        } catch (Exception e) {}

        return list;
    }
}