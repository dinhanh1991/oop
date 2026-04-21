package repository;

import model.*;
import repository.Impl.ITicketImpl;

import java.io.*;
import java.util.*;

public class TicketRepo implements ITicketImpl {
    private final String PATH = "src/data/tickets.csv";
    private ShowTimeRepo showTimeRepo = new ShowTimeRepo();
    public TicketRepo() {
        ensureFile();
    }

    private void ensureFile() {
        try {
            File file = new File(PATH);
            if (file.getParentFile() != null && !file.getParentFile().exists()) file.getParentFile().mkdirs();
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            System.out.println("❌ Lỗi khởi tạo file vé.");
        }
    }
    public void save(Ticket ticket) {
        ensureFile();
        try (FileWriter fw = new FileWriter(PATH, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            // Lưu: Tên khách, SĐT, ID suất chiếu, Số ghế
            String data = String.format("%s,%s,%s,%s\n",
                    ticket.getCustomer().getName(),
                    ticket.getCustomer().getPhone(),
                    ticket.getShowTime().getId(),
                    ticket.getSeat().getSeatNumber());
            bw.write(data);
        } catch (IOException e) {
            System.out.println("❌ Lỗi: Không thể lưu vé.");
        }
    }

    public List<Ticket> findAll() {
        ensureFile();
        List<Ticket> tickets = new ArrayList<>();
        List<ShowTime> showTimes = showTimeRepo.findAll();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(",");
                if (s.length >= 4) {
                    String name = s[0], phone = s[1], stId = s[2], seatNum = s[3];

                    ShowTime st = showTimes.stream()
                            .filter(item -> item.getId().equals(stId))
                            .findFirst().orElse(null);

                    if (st != null) {
                        Seat seat = st.getSeats().stream()
                                .filter(gs -> gs.getSeatNumber().equals(seatNum))
                                .findFirst().orElse(new Seat(seatNum, true));

                        tickets.add(new Ticket(new Customer(name, phone), st, seat));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi: Không nạp được danh sách vé.");
        }
        return tickets;
    }
    // Trong lớp TicketRepo.java

    public void deleteTicket(String phone, String seatNum) {
        // 1. Đọc tất cả vé hiện có
        List<Ticket> allTickets = findAll();

        // 2. Lọc bỏ vé khớp với số điện thoại và số ghế
        List<Ticket> updatedTickets = allTickets.stream()
                .filter(t -> !(t.getCustomer().getPhone().equals(phone) &&
                        t.getSeat().getSeatNumber().equalsIgnoreCase(seatNum)))
                .collect(java.util.stream.Collectors.toList());

        // 3. Kiểm tra nếu có vé bị xóa thì mới ghi lại file
        if (allTickets.size() != updatedTickets.size()) {
            saveAll(updatedTickets);
            System.out.println("✅ Đã xóa dữ liệu vé khỏi hệ thống.");
        } else {
            System.out.println("❌ Không tìm thấy vé khớp với thông tin cung cấp.");
        }
    }

    // Hàm phụ trợ để ghi đè toàn bộ danh sách vé vào file
    private void saveAll(List<Ticket> tickets) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PATH))) {
            for (Ticket t : tickets) {
                pw.printf("%s,%s,%s,%s\n",
                        t.getCustomer().getName(),
                        t.getCustomer().getPhone(),
                        t.getShowTime().getId(),
                        t.getSeat().getSeatNumber());
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi cập nhật file vé.");
        }
    }
}