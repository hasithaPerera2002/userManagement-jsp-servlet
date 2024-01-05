package model;



public class Item {

    private String name;

    private int quantity;
    private double price;
    private int supplierId;
    public Item( String name,int quantity, double price, int supplierId) {

        this.name = name;

        this.quantity = quantity;
        this.price = price;
        this.supplierId = supplierId;
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
