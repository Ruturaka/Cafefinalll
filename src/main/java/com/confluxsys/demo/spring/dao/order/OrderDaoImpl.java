package com.confluxsys.demo.spring.dao.Order;

import com.confluxsys.demo.model.Orders.Order;
import com.confluxsys.demo.model.Orders.OrderMapper;
import com.confluxsys.demo.model.orderedItems.OrderedItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao{

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

    @Override
    public Integer deleteById(Integer id) {
        String query="DELETE FROM orders where order_id=?";
        return jdbcTemplate.update(query,id);
    }

    @Override
    public Integer deleteAll() {
        return jdbcTemplate.update("DELETE from ordered_items");
    }

    /*
    @Override
    public Integer updateOrder(Order order) {
        //return jdbcTemplate.update("update customers set name=?, email=? WHERE mobile=?", new Object[] {customer.getName(), customer.getEmail(), customer.getMobile()});
return null;
       // return jdbcTemplate.queryForObject("update customer set order_id, customer_id WHERE order_id=?", new Object[] {order.or"});
    }

    @Override
    public Integer deleteOrder(Integer customer_id) {
        //        return jdbcTemplate.update("DELETE FROM ordered_items WHERE order_id=?", order_id);
        return jdbcTemplate.update("DELETE FROM orders WHERE customer_id=?", customer_id);
    }
*/


/*
  System.out.println("inside orderdao");
        String query="INSERT INTO orders(customer_id) VALUES (?) RETURNING order_id";
        int customer_id = jdbcTemplate.queryForObject(query, Integer.class, new Object[] {order.getCustomer_id()}, Integer.class);
        return customer_id;
 */
}
