package repository;

import model.Seat;
import repository.impl.SeatImpl;

import java.io.*;
import java.util.*;

public class SeatRepo implements SeatImpl {

    private final String DATA = "untitled\\src\\data\\seats.csv";
// Kiểm tra xem file có tồn tại không nếu không cos tự tạo file
    private void ensureFile() throws Exception {
        File file = new File(DATA);
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if (!file.exists()) file.createNewFile();
    }

    @Override
    public List<Seat> findByShow(String showId) {
        List<Seat> list = new ArrayList<>();

        try {
            ensureFile();
            BufferedReader br = new BufferedReader(new FileReader(DATA));

            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(",");

                if (s.length >= 3 && s[0].equals(showId)) {
                    list.add(new Seat(s[1], Boolean.parseBoolean(s[2])));
                }
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void updateSeat(String showId, String seatNum, boolean status) {

        List<String[]> list = new ArrayList<>();

        try {
            ensureFile();
            BufferedReader br = new BufferedReader(new FileReader(DATA));

            String line;
            while ((line = br.readLine()) != null) {
                list.add(line.split(","));
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(DATA);

            boolean found = false;

            for (String[] s : list) {
                if (s.length >= 3 && s[0].equals(showId) && s[1].equals(seatNum)) {
                    s[2] = String.valueOf(status);
                    found = true;
                }
                fw.write(String.join(",", s) + "\n");
            }

            // nếu chưa có ghế → thêm mới
            if (!found) {
                fw.write(showId + "," + seatNum + "," + status + "\n");
            }

            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}