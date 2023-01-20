package com.confluxsys.demo.spring.dao;

import com.confluxsys.demo.bean.CustomerOrder;

public interface CustomerDao {
    int createCustomer(CustomerOrder customer); //post

}
