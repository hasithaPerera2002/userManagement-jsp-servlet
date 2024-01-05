package model;

/********************************************************************
 * --
 * Author: hasit
 * Date: 1/5/2024
 * --
 ********************************************************************/

public class Supplier {

    private String name;
    private String category;
    private int quantity;
    private double price;

    private String email;
    public Supplier( String name, String category, int quantity, double price,String email) {

        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.email=email;
    }

    // Getters and Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
