package lk.ijse.myap.dto;

import java.time.LocalDate;

public class OrdersDTO {
    
    
    // orders table 
    
    private String id;
    private LocalDate date;
    private double totalPrice;

    
    //default constructor
    public OrdersDTO() {}

    // Constructor 
    public OrdersDTO(String id, LocalDate date) {
        this.id = id;
        this.date = date;
        this.totalPrice = 0.0;
    }
    
    // full arg constroctor
    public OrdersDTO(String id, LocalDate date, double totalPrice) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
    }
    
    // Getters and setters methods
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