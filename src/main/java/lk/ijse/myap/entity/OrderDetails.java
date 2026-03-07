package lk.ijse.myap.entity;

public class OrderDetails {
    private String orderId;
    private String itemId;
    private int orderQty;
    private double unitPrice;
    private double lineTotal;

    public OrderDetails(String orderId, String itemId, int orderQty, double unitPrice, double lineTotal) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.orderQty = orderQty;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
    }
    public OrderDetails() {}

    public OrderDetails(String orderId, String itemId, int orderQty, double unitPrice) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.orderQty = orderQty;
        this.unitPrice = unitPrice;
    }

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
          this.orderId = orderId;
    }
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public int getOrderQty() {
        return orderQty;
    }
    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public double getLineTotal() {
        return lineTotal;
    }
    public void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }

}
