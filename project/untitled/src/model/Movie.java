package model;

public class Movie {
    private String id; // id phim
    private String title;// Tên phim
    private int duration;// thời lượng
// Constructor
    public Movie(String id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }
// Getter và setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
