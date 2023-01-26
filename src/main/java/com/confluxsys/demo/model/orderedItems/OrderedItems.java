package com.confluxsys.demo.model.orderedItems;

public class OrderedItems {
    Integer order_id; //we get that from order's table

    Integer item_id; //auto generated
    String dish;
    Integer quantity;
    String feedback;

    public OrderedItems() {
        System.out.println("inside orders default constructor");
    }

    public OrderedItems(Integer order_id) {
        this.order_id = order_id;
    }

    public OrderedItems(Integer order_id, String dish, Integer quantity, String feedback) {
        System.out.println("inside order's parameterized constructor");
        this.order_id=order_id;
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

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
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
