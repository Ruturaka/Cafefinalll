package com.confluxsys.demo.model.Orders;

public class Order {
    Integer order_id; //auto
    Integer customer_id; // we will take that from customer's table

    public Order() {
    }

    public Order(Integer customer_id) {
        this.customer_id=customer_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }
}
