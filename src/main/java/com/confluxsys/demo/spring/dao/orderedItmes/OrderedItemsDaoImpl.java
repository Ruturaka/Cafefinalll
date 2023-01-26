package com.confluxsys.demo.spring.dao.orderedItmes;

import com.confluxsys.demo.model.orderedItems.OrderedItems;
import com.confluxsys.demo.spring.dao.order.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

@Component
public class OrderedItemsDaoImpl implements OrderedItemsDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderedItemsDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int createOrderedItems(OrderedItems orderedItems) {
        System.out.println("Inside createOrderredItems()");

        String query = "INSERT INTO ordered_items(order_id, dish, quantity, feedback) VALUES (?, ?, ?, ?)  RETURNING item_id";
        System.out.println("hii from ordered_items");
        try {
           // String query = "INSERT INTO ordered_items(order_id, dish, quantity, feedback) VALUES (?, ?, ?, ?)  RETURNING item_id";

            PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(query);
            ps.setObject(1, orderedItems.getOrder_id(), Types.INTEGER);
            ps.setObject(2, orderedItems.getDish(), Types.VARCHAR);
            ps.setObject(3, orderedItems.getQuantity(), Types.INTEGER);
            ps.setObject(4, orderedItems.getFeedback(), Types.VARCHAR);

            ResultSet rs = ps.executeQuery();

            if (rs != null && rs.next()) {
                return rs.getInt("item_id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("outside try catch");
        return 0;

    }
}
