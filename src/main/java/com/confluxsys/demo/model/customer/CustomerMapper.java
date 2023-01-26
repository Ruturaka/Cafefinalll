package com.confluxsys.demo.model.customer;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


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
