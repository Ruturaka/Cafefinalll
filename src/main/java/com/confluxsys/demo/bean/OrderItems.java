package com.confluxsys.demo.bean;

public class OrderItems {
    Integer order_id;

    String dish;
    Integer quantity;
    String feedback;

    public OrderItems() {
        System.out.println("inside orderitems parameterized constructor");

    }

    public OrderItems(String dish, Integer quantity, String feedback) {
        System.out.println("inside orderitems parameterized constructor");
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

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
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
