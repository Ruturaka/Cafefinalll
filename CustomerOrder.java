package com.confluxsys.demo.bean;

import java.util.List;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CustomerOrder {
    private Integer orderid;
    private String name;
    private String email;
    private String mobile;
    private List<OrderItems> orderItemsList;

    public CustomerOrder() {
    }
    public CustomerOrder(String name, String email, String mobile) {
        this.name=name;
        this.email=email;
        this.mobile=mobile;
    }
    public CustomerOrder(String name, String email, String mobile, List<OrderItems> orderItemsList) {
        this.name=name;
        this.email=email;
        this.mobile=mobile;
        this.orderItemsList = orderItemsList;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    @Override
    public String toString() {
        return "JsonToJavaObjectBean{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", orderItemsList=" + orderItemsList +
                '}';
    }
}
