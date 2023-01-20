package com.confluxsys.demo.spring.dao;

import com.confluxsys.demo.bean.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class CustomerDaoImpl implements CustomerDao{
    JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int createCustomer(CustomerOrder customer) {
        String query="INSERT INTO customers(name, email, mobile) VALUES (?, ?, ?)";
        return jdbcTemplate.update(query, customer.getName(), customer.getEmail(), customer.getMobile());
    }
}
