package service;

import model.ShowTime;
import repository.ShowTimeRepo;
import service.impl.ShowTimeServiceImp;

import java.util.List;

public class ShowTimeService implements ShowTimeServiceImp {
    ShowTimeRepo showTimeRepo =new ShowTimeRepo();

    @Override
    public List<ShowTime> getAll() {
        return showTimeRepo.findAll();
    }

    @Override
    public ShowTime findById(String id) {
        return showTimeRepo.findAll().stream()
                .filter(st -> st.getId().equals(id))
                .findFirst().orElse(null);
    }
    public void add(ShowTime st) {
        showTimeRepo.add(st);
    }

    public void update(ShowTime st) {
        showTimeRepo.update(st);
    }

    public void delete(String id) {
        showTimeRepo.delete(id);
    }

    public List<ShowTime> findByTime(String time) {
        return showTimeRepo.findByTime(time);
    }
    // Hiển thị danh sách suất chiếu đẹp mắt ra Console
    @Override
    public void displayShowTimes() {
        List<ShowTime> list = getAll();
        System.out.println("\n----------------- LỊCH CHIẾU PHIM -----------------");
        System.out.printf("%-10s | %-20s | %-10s | %-8s\n", "Mã Suất", "Tên Phim", "Giờ", "Thời lượng");
        System.out.println("---------------------------------------------------");
        for (ShowTime st : list) {
            System.out.printf("%-10s | %-20s | %-10s | %-8d\n",
                    st.getId(),
                    st.getMovie().getTitle(), // Liên kết từ Model Movie
                    st.getTime(),
                    st.getMovie().getDuration());
        }
        System.out.println("---------------------------------------------------");
    }
}
