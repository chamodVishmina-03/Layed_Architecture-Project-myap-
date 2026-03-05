package lk.ijse.myap.dto;

public class ItemDTO {

    private String id; 
    private String name; 
    private int qty; 
    private double unitPrice; 
    private int categoryId; 

    
            // default donstructor
    public ItemDTO() {
    }

    
    
    // full arg Constructor 
    public ItemDTO(String id, String name, int qty, double unitPrice, int categoryId) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.categoryId = categoryId;
    }

    
    
    
    // Getters and Setters (Important for TableView PropertyValueFactory)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}