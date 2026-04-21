package repository.Impl;

import model.ShowTime;

import java.util.List;

public interface IShowTimeImp {
    List<ShowTime> findAll();
    void update(ShowTime updated);
    public void delete(String id);
    public List<ShowTime> findByTime(String time);
}
