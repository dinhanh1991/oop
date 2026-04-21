package service.impl;

import model.ShowTime;

import java.util.List;

public interface ShowTimeServiceImp {
    List<ShowTime> getAll();
    ShowTime findById(String id);
    void displayShowTimes();
}
