package service;

import model.Seat;
import repository.SeatRepo;
import service.impl.SeatServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service xử lý logic liên quan đến ghế (Seat)
 * Là tầng trung gian giữa Controller và Repository
 */
public class SeatService implements SeatServiceImpl {

    // Kết nối tới tầng Repository để thao tác dữ liệu
    private SeatRepo seatRepo = new SeatRepo();

    /**
     * Lấy danh sách ghế theo mã suất chiếu
     * @param showId mã suất chiếu
     * @return danh sách ghế
     */
    @Override
    public List<Seat> getSeats(String showId) {
        return seatRepo.findByShow(showId);
    }

    /**
     * Kiểm tra một ghế đã được đặt chưa
     * @param showId mã suất chiếu
     * @param seatNum số ghế (ví dụ: A1, B2)
     * @return true nếu đã đặt, false nếu chưa
     */
    @Override
    public boolean isBooked(String showId, String seatNum) {
        for (Seat s : getSeats(showId)) {
            if (s.getSeatNumber().equals(seatNum)) {
                return s.isBooked(); // trả về trạng thái ghế
            }
        }
        return false; // nếu không tìm thấy ghế
    }

    /**
     * Đặt ghế (chuyển trạng thái thành đã đặt)
     * @param showId mã suất chiếu
     * @param seatNum số ghế
     */
    @Override
    public void bookSeat(String showId, String seatNum) {
        seatRepo.updateSeat(showId, seatNum, true);
    }

    /**
     * Hủy đặt ghế (chuyển trạng thái về chưa đặt)
     * @param showId mã suất chiếu
     * @param seatNum số ghế
     */
    @Override
    public void unBookSeat(String showId, String seatNum) {
        seatRepo.updateSeat(showId, seatNum, false);
    }

    /**
     * Tạo danh sách ghế mặc định cho một suất chiếu
     * Ví dụ: A1 → A5, B1 → B5, C1 → C5
     * Tổng: 15 ghế
     * @param showId mã suất chiếu
     */
    public void generateSeats(String showId) {
        for (char row = 'A'; row <= 'C'; row++) {
            for (int i = 1; i <= 5; i++) {
                // false = ghế chưa được đặt
                seatRepo.updateSeat(showId, row + "" + i, false);
            }
        }
    }

    /**
     * Trả về Map dạng:
     * key = số ghế (A1, A2,...)
     * value = trạng thái (true = đã đặt, false = chưa)
     * @param showId mã suất chiếu
     * @return map ghế
     */
    @Override
    public Map<String, Boolean> getSeatMap(String showId) {
        Map<String, Boolean> map = new HashMap<>();

        for (Seat s : getSeats(showId)) {
            map.put(s.getSeatNumber(), s.isBooked());
        }

        return map;
    }
}