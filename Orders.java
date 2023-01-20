package com.confluxsys.demo.model;

public class Orders {
    String dish;
    Integer quantity;
    String feedback;

    public Orders() {
        System.out.println("inside orders default constructor");
    }

    public Orders(String dish, Integer quantity, String feedback) {
        System.out.println("inside order's parameterized constructor");
        this.dish = dish;
        this.quantity=quantity;
        this.feedback=feedback;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "dish='" + dish + '\'' +
                ", quantity=" + quantity +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
