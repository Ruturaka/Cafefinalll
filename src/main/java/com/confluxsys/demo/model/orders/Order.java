package com.confluxsys.demo.model.orders;

public class Order {

    Integer order_id;
    Integer customer_id;

    public Order() {
    }

    public Order(Integer customer_id) {
        this.customer_id = customer_id;
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
