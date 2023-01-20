package com.confluxsys.demo.model;

import com.confluxsys.demo.bean.CustomerOrder;
import com.confluxsys.demo.bean.OrderItems;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();

        customer.setName(rs.getString("name"));  // get customer name from customers table
        customer.setEmail(rs.getString("email")); // get customer name from customers table

        customer.setMobile(rs.getString("mobile")); // get order id from orders table

        return customer;
    }
}
