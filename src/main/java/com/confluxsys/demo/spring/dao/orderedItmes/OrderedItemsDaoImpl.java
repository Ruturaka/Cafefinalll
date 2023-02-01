package com.confluxsys.demo.spring.dao.orderedItems;

import com.confluxsys.demo.model.orderedItems.OrderedItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@PropertySource("classpath:database.properties")
@Component
public class OrderedItemsDaoImpl implements OrderedItemsDao{
    JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderedItemsDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Integer createOrderedItems(OrderedItems orderedItems) {
        System.out.println("Inside createOrderredItems()");

        String query="INSERT INTO ordered_items(order_id, dish, quantity, feedback) VALUES (?, ?, ?, ?)  RETURNING item_id";
        System.out.println("hii from ordered_items");
        try {
            PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(query);
            ps.setObject(1, orderedItems.getOrder_id(), Types.INTEGER);
            ps.setObject(2, orderedItems.getDish(), Types.VARCHAR);
            ps.setObject(3, orderedItems.getQuantity(), Types.INTEGER);
            ps.setObject(4, orderedItems.getFeedback(), Types.VARCHAR);

            ResultSet rs = ps.executeQuery();

            if (rs != null && rs.next()) {
                return rs.getInt("item_id");
            }
        } catch (Exception e){
            System.out.println(e);
        }

        System.out.println("outside try catch");
        return 0;
    }

    @Override
    public Integer deleteById(Integer id) {
        String query="DELETE FROM ordered_items where order_id=?";
        return jdbcTemplate.update(query,id);
    }

    @Override
    public List<OrderedItems> findAll() {
        try{
            String query="SELECT * FROM ordered_items";
            return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(OrderedItems.class));
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<OrderedItems> findByDish(String dish) {
        try{
            String query= "SELECT * FROM ordered_items WHERE dish=?";
            return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(OrderedItems.class), dish);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;

    }

    @Override
    public Integer deleteAll() {
        return jdbcTemplate.update("DELETE from ordered_items");
    }

}
