package com.confluxsys.demo.spring.dao;

import com.confluxsys.demo.bean.CustomerOrder;
import com.confluxsys.demo.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class OrderDaoImpl implements OrderDao{
    JdbcTemplate jdbcTemplate;




    @Autowired
    public OrderDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int createOrder(Orders orders) {
        String query="INSERT INTO orders(dish, quantity, feedback) VALUES (?, ?, ?)";
        return jdbcTemplate.update(query, orders.getDish(), orders.getQuantity(), orders.getFeedback());
    }
}
