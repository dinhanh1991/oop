package repository;

import model.Movie;
import repository.impl.MoviesImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class MoviesRepo implements MoviesImpl {
    private final String DATA = "D:\\learning\\oob\\untitled\\src\\data\\movies.csv";

    // READ
    @Override
    public List<Movie> findAll() {

        List<Movie> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(DATA))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] s = line.split(",");
                if (s.length < 3) continue;

                list.add(new Movie(
                        s[0],
                        s[1],
                        Integer.parseInt(s[2])
                ));
            }

        } catch (Exception e) {
            System.out.println("Lỗi đọc movies!");
        }

        return list;
    }

    // CREATE
    @Override
    public void add(Movie m) {
        try {
            File file = new File(DATA);

            // tạo thư mục nếu chưa có
            file.getParentFile().mkdirs();

            // tạo file nếu chưa có
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);

            fw.write(
                    m.getId() + "," +
                            m.getTitle() + "," +
                            m.getDuration() + "\n"
            );

            fw.close();

        } catch (Exception e) {
            e.printStackTrace(); // 👈 in lỗi thật ra để debug
            System.out.println("Lỗi thêm phim!");
        }
    }

    // DELETE
    @Override
    public void delete(String id) {

        List<Movie> list = findAll();

        try (FileWriter fw = new FileWriter(DATA)) {

            for (Movie m : list) {
                if (!m.getId().equals(id)) {
                    fw.write(
                            m.getId() + "," +
                                    m.getTitle() + "," +
                                    m.getDuration() + "\n"
                    );
                }
            }

        } catch (Exception e) {
        }
    }

    // UPDATE
    @Override
    public void update(Movie movie) {

        List<Movie> list = findAll();

        try (FileWriter fw = new FileWriter(DATA)) {

            for (Movie m : list) {

                if (m.getId().equals(movie.getId())) {
                    m = movie;
                }

                fw.write(
                        m.getId() + "," +
                                m.getTitle() + "," +
                                m.getDuration() + "\n"
                );
            }

        } catch (Exception e) {
        }
    }
}

