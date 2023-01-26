package com.confluxsys.demo.spring.dao.customer;

import com.confluxsys.demo.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class CustomerDaoImpl implements CustomerDao{
    JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Integer addCustomer(Customer customer) {

        String sql = "SELECT customer_id FROM customers WHERE name = ? AND email=? AND mobile=?";
        String query = "INSERT INTO customers(name, email, mobile) VALUES (?, ?, ?) ON CONFLICT DO NOTHING RETURNING customer_id";
        try{
            System.out.println("inside try:");
            return jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{customer.getName(), customer.getEmail(), customer.getMobile()});
        }
        catch(Exception e){
            System.out.println(e);
        }
        return jdbcTemplate.queryForObject(query,Integer.class, customer.getName(), customer.getEmail(), customer.getMobile());

    }
}