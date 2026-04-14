// Customer
package model;

public class Customer {
    private String name; // Tên
    private String phone; // Số Điện Thoại
// Constructor
    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
// setter and getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}