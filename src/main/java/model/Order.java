package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class Order {
    private int id;
    private Date orderDate;
    private int total;
    private String status;

    private int quantity;

    private int itemId;






    public Order(int id, Date orderDate, int totalAmount, String status,int quantity,int itemId) {
        this.id = id;
        this.orderDate = orderDate;
        this.total = totalAmount;
        this.status = status;
        this.quantity=quantity;
        this.itemId=itemId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotalAmount() {
        return total;
    }

    public void setTotalAmount(int totalAmount) {
        this.total = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    public int getItemId(){
        return itemId;
    }

    public void setItemId(int itemId){
        this.itemId=itemId;
    }


}
