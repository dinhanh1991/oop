package repository;

import model.Seat;
import repository.Impl.ISeatImpl;

import java.io.*;
import java.util.*;

public class SeatRepo implements ISeatImpl {
    private final String PATH = "src/data/seats.csv";
    public SeatRepo() {
        ensureFile();
    }

    private void ensureFile() {
        try {
            File file = new File(PATH);
            if (file.getParentFile() != null && !file.getParentFile().exists()) file.getParentFile().mkdirs();
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            System.out.println("❌ Lỗi khởi tạo file ghế.");
        }
    }

    public List<Seat> findByShowId(String showId) {
        ensureFile();
        List<Seat> seats = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(",");
                if (s.length >= 3 && s[0].equals(showId)) {
                    seats.add(new Seat(s[1], Boolean.parseBoolean(s[2])));
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc ghế.");
        }

        // 👉 Nếu chưa có ghế → tạo mới
        if (seats.isEmpty()) {
            createDefaultSeats(showId);
            return findByShowId(showId); // đọc lại
        }

        return seats;
    }
    public void updateSeatStatus(String showId, String seatNum, boolean status) {
        List<String> lines = new ArrayList<>();
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(",");
                if (s[0].equals(showId) && s[1].equals(seatNum)) {
                    line = showId + "," + seatNum + "," + status;
                    found = true;
                }
                lines.add(line);
            }
            if (!found) lines.add(showId + "," + seatNum + "," + status);

            try (PrintWriter pw = new PrintWriter(new FileWriter(PATH))) {
                for (String l : lines) pw.println(l);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
    public void createDefaultSeats(String showId) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH, true))) {

            char row = 'A';
            for (int i = 0; i < 3; i++) { // 3 hàng A, B, C
                for (int j = 1; j <= 5; j++) { // 5 ghế mỗi hàng
                    String seatNum = row + String.valueOf(j);
                    bw.write(showId + "," + seatNum + ",false\n");
                }
                row++;
            }

        } catch (IOException e) {
            System.out.println("❌ Lỗi tạo ghế mặc định.");
        }
    }
}