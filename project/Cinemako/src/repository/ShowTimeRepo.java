package repository;

import model.Movie;
import model.Seat;
import model.ShowTime;
import repository.Impl.IShowTimeImp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShowTimeRepo implements IShowTimeImp {
    private final String PATH = "src/data/showtimes.csv";
    private final MovieRepo movieRepo = new MovieRepo();
    private final SeatRepo seatRepo = new SeatRepo();

    public ShowTimeRepo() {
        ensureFile();
    }

    private void ensureFile() {
        try {
            File file = new File(PATH);
            if (file.getParentFile() != null && !file.getParentFile().exists()) file.getParentFile().mkdirs();
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            System.out.println("❌ Lỗi khởi tạo file suất chiếu.");
        }
    }

    public List<ShowTime> findAll() {
        ensureFile();
        List<ShowTime> showTimes = new ArrayList<>();
        List<Movie> movies = movieRepo.getAllMovies(); // Nạp phim làm nguyên liệu

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(","); // showId, time, movieId
                if (s.length >= 3) {
                    String stId = s[0];
                    String time = s[1];
                    String mId = s[2];

                    // Liên kết đối tượng Movie
                    Movie movie = movies.stream()
                            .filter(m -> m.getId().equals(mId))
                            .findFirst().orElse(null);

                    if (movie != null) {
                        // Liên kết danh sách Ghế
                        List<Seat> seats = seatRepo.findByShowId(stId);
                        showTimes.add(new ShowTime(stId, time, movie, seats));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi: Không tìm thấy file showtimes.csv");
        }
        return showTimes;
    }
    public void add(ShowTime st) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH, true))) {
            String data = String.format("%s,%s,%s\n",
                    st.getId(),
                    st.getTime(),
                    st.getMovie().getId());
            bw.write(data);
        } catch (IOException e) {
            System.out.println("❌ Lỗi thêm suất chiếu");
        }
    }
    //==============Update suất chiếu===========
    public void update(ShowTime updated) {
        List<ShowTime> list = findAll();

        for (ShowTime st : list) {
            if (st.getId().equals(updated.getId())) {
                st.setTime(updated.getTime());
                st.setMovie(updated.getMovie());
            }
        }

        save(list);
    }
    //================Delete ============
    public void delete(String id) {
        List<ShowTime> list = findAll();

        list.removeIf(st -> st.getId().equals(id));

        save(list);
    }
    public List<ShowTime> findByTime(String time) {
        return findAll().stream()
                .filter(st -> st.getTime().equalsIgnoreCase(time))
                .toList();
    }
    //==================lưu file===========================
    private void save(List<ShowTime> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH))) {
            for (ShowTime st : list) {
                String data = String.format("%s,%s,%s\n",
                        st.getId(),
                        st.getTime(),
                        st.getMovie().getId());
                bw.write(data);
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file showtime");
        }
    }
}