package com.confluxsys.demo.bean;

public class OrderItems {
    String dish;
    Integer quantity;
    String feedback;

    public OrderItems() {
    }

    public OrderItems(String dish, Integer quantity, String feedback) {
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
