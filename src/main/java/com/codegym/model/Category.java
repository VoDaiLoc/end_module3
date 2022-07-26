package com.codegym.model;

public class Category {
    private int id;
    private String action;

    public Category(int id, String acction) {
        this.id = id;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcction() {
        return action;
    }

    public void setAcction(String acction) {
        this.action = acction;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", acction='" + action + '\'' +
                '}';
    }
}
