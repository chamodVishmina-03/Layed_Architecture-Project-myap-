package lk.ijse.myap.entity;

import java.time.LocalDate;

public class Orders {
    private String id;
    private LocalDate date;
    private double totalPrice;


    public Orders(String id, LocalDate date) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
