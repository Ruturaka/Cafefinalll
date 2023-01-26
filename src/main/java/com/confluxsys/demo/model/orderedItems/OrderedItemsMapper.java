package com.confluxsys.demo.model.orderedItems;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderedItemsMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderedItems orders = new OrderedItems();

        orders.setDish(rs.getString("dish"));  // get customer name from customers table
        orders.setQuantity(rs.getInt("quantity"));
        orders.setFeedback(rs.getString("feedback"));  // get customer name from customers table// get customer name from customers table
        return orders;
    }
}
