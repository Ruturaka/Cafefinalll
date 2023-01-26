package com.confluxsys.demo.model.orders;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order orders = new Order();

        orders.setOrder_id(rs.getInt("order_id"));
        orders.setCustomer_id(rs.getInt("customer_id"));  // get customer name from customers table
        return orders;
    }
}
