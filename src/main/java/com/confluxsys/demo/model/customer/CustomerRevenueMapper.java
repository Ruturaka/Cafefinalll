package com.confluxsys.demo.model.customer;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRevenueMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();

        customer.setCustomer_id(rs.getInt("customer_id"));
        customer.setName(rs.getString("name"));  // get customer name from customers table
        customer.setEmail(rs.getString("email")); // get customer name from customers table
        customer.setMobile(rs.getString("mobile")); // get order id from orders table
        customer.setRevenue(rs.getInt("revenue"));

        return customer;
    }
}
