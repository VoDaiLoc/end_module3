package com.codegym.model;

public class Product {
    private int id;
    private String name;
    private String price;
    private String quantity;
    private String color;
    private String des;
    private int idCat;

    public Product(){

    }

    public Product(String name, String price, String quantity, String color, String des) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.des = des;
    }

    public Product(int id, String name, String price, String quantity, String color, String des) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.des = des;
    }

    public Product(int id, String name, String price, String quantity, String color, String des, int idCat) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.des = des;
        this.idCat = idCat;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", color='" + color + '\'' +
                ", des='" + des + '\'' +
                ", idCat=" + idCat +
                '}';
    }
}
