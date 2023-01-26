package com.confluxsys.demo.spring.dao.order;

import com.confluxsys.demo.model.orderedItems.OrderedItems;
import com.confluxsys.demo.model.orders.Order;
import com.confluxsys.demo.spring.dao.order.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;




@Component
public class OrderDaoImpl implements OrderDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Integer createOrder(Order order) {
        String query = "INSERT INTO orders(customer_id) VALUES (?) RETURNING order_id";
        try {
            PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(query);
            ps.setObject(1, order.getCustomer_id(), Types.INTEGER);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("order_id");
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    /*@Override
    public Integer addOrderItems(Integer orderId, List<OrderedItems> orderedItemsList){
            for (OrderedItems orderedItem : orderedItemsList) {
                // Insert the ordered item into the database
                String sql = "INSERT INTO ordered_items (order_id, item_id, quantity) VALUES (?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, orderId);
                    statement.setInt(2, orderedItem.getItemId());
                    statement.setInt(3, orderedItem.getQuantity());

                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return orderId;  // Return the order ID to indicate success
        }
    }*/
}


