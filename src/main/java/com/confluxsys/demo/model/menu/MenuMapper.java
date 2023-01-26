package com.confluxsys.demo.model.menu;

import com.confluxsys.demo.model.orderedItems.OrderedItems;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuMapper implements RowMapper<Menu> {

    @Override
    public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
            Menu menu = new Menu();

            menu.setId(rs.getInt("id"));  // get customer name from customers table
            menu.setDish(rs.getString("dish"));
            menu.setPrice(rs.getInt("price"));  // get customer name from customers table// get customer name from customers table
            return menu;
        }
}
