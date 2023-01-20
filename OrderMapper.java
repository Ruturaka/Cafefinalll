package com.confluxsys.demo.model;

import com.confluxsys.demo.bean.CustomerOrder;
import com.confluxsys.demo.bean.OrderItems;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Orders orders = new Orders();

        orders.setDish(rs.getString("dish"));  // get customer name from customers table
        orders.setQuantity(rs.getInt("quantity"));
        orders.setFeedback(rs.getString("feedback"));  // get customer name from customers table// get customer name from customers table
        return orders;
    }
}
