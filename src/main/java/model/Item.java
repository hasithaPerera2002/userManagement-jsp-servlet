package model;


public class Item {

    private int id;
    private String name;

    private int quantity;
    private double price;
    private int supplierId;

    public Item(int id, String name, int quantity, double price, int supplierId) {

        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.supplierId = supplierId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

}
