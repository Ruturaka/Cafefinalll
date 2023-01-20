package com.confluxsys.demo.model;

import com.confluxsys.demo.bean.OrderItems;

import java.util.List;

public class Customer {
    private String name;
    private String email;
    private String mobile;
    private Integer order_id;

    public Customer() {
    }
    public Customer(String name, String email, String mobile) {
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


    @Override
    public String toString() {
        return "JsonToJavaObjectBean{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
