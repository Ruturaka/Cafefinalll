package com.confluxsys.demo.model.customer;

import com.confluxsys.demo.bean.OrderItems;

import java.util.List;

public class Customer {
    private Integer customer_id;
    private String name;
    private String email;
    private String mobile;

    public Customer() {
        System.out.println("inside customer default constructor");

    }
    public Customer(String name, String email, String mobile) {
        System.out.println("inside customer parameterized constructor");

        this.name=name;
        this.email=email;
        this.mobile=mobile;
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

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "JsonToJavaObjectBean{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
