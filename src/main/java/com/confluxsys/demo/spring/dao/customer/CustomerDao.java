package com.confluxsys.demo.spring.dao.customer;


import com.confluxsys.demo.bean.CustomerOrder;
import com.confluxsys.demo.model.customer.Customer;

public interface CustomerDao {
    Integer addCustomer(Customer customer); //post
}
