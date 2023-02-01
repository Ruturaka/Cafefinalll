package com.confluxsys.demo.spring.dao.customer;

import com.confluxsys.demo.model.customer.Customer;

import com.confluxsys.demo.model.customer.CustomerBillMapper;
import com.confluxsys.demo.model.customer.CustomerRevenueMapper;
import com.confluxsys.demo.model.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class CustomerDaoImpl implements CustomerDao {
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
    @Override
    public Integer save(Customer customer) {
        String query="INSERT INTO customers (name, email, mobile) VALUES(?,?,?)";
        return jdbcTemplate.update(query, new Object[]{customer.getName(), customer.getEmail(), customer.getMobile()});
    }

    @Override
    public Integer update(Customer customer) {
        String query="UPDATE customers SET name=?, email=?, mobile=? WHERE customer_id=?";
        return jdbcTemplate.update(query, new Object[]{customer.getName(), customer.getEmail(), customer.getMobile(), customer.getCustomer_id()});
    }

    @Override
    public Customer findById(Integer id) {
        try{
            String query="SELECT * FROM customers WHERE customer_id=?";
            Customer customer= jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Customer.class), id);
            return customer;
        }
        catch(IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    @Override
    public Integer deleteById(Integer id) {
        try{
            String query="DELETE FROM customers WHERE customer_id=?";
            return jdbcTemplate.update(query, id);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;

    }

    @Override
    public List<Customer> findAll() {
        String query="SELECT * FROM customers";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public List<Customer> findByMobile(String mobile) {
        String query="SELECT * FROM customers WHERE mobile=?";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Customer.class), mobile);
    }

    @Override
    public List<Customer> findByNameContaining(String name) {
        String query="SELECT * FROM customers WHERE name ILIKE '%" + name + "%'";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public Integer deleteAll() {
        String query="DELETE FROM customers";
        return jdbcTemplate.update(query);
    }




    @Override
    public Customer getCustomerById(Integer id) {
        String query="SELECT customers.customer_id, name, email, mobile FROM customers WHERE customers.customer_id=?";
        return jdbcTemplate.queryForObject(query ,new CustomerBillMapper(),new Object[]{id});
    }

    @Override
    public Integer deleteCustomer(Integer order_id) {
        System.out.println("inside deleteCustomer");

        return jdbcTemplate.update("DELETE FROM customers WHERE order_id=?",order_id);
    }

    @Override
    public Customer getCustomerByMobile(String mobile) {
        return jdbcTemplate.queryForObject("SELECT * FROM customers WHERE mobile=?",new CustomerBillMapper(),new Object[]{mobile});
    }

    @Override
    public List<Customer> getAllCustomerOrders()
    {
        String query="SELECT customers.customer_id, orders.order_id, name, email, mobile, dish, quantity, feedback FROM customers INNER JOIN orders ON customers.customer_id=orders.customer_id INNER JOIN ordered_items ON orders.order_id=ordered_items.order_id";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public List<Customer> getCustomerHistory(String mobile){
        String query="SELECT customers.customer_id,orders.order_id, name, email, mobile, SUM(quantity*price) AS bill FROM customers INNER JOIN orders ON customers.customer_id=orders.customer_id INNER JOIN ordered_items ON orders.order_id=ordered_items.order_id INNER JOIN menu ON ordered_items.dish = menu.dish WHERE mobile=? GROUP BY customers.customer_id,orders.order_id, name, email, mobile";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Customer.class), new Object[]{mobile});
    }

    @Override
    public List<Customer> findCustOrderByNameContaining(String name) {
        String query="SELECT customers.customer_id, orders.order_id, name, email, mobile, dish, quantity, feedback FROM customers INNER JOIN orders ON customers.customer_id=orders.customer_id INNER JOIN ordered_items ON orders.order_id=ordered_items.order_id WHERE name ILIKE '%" + name + "%'";

        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public List<Customer> findCustOrderByMobile(String mobile){
        String query="SELECT customers.customer_id, orders.order_id, name, email, mobile, dish, quantity, feedback FROM customers INNER JOIN orders ON customers.customer_id=orders.customer_id INNER JOIN ordered_items ON orders.order_id=ordered_items.order_id WHERE mobile ILIKE '%" + mobile + "%'";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public Customer getBillByOrder_id(Integer order_id) {
        System.out.println("inside customerdao's getBillByOrder_id");

        try {
            System.out.println("inside try of getBillOrder_id");
            String query = "SELECT customers.customer_id, orders.order_id, name, email, mobile, SUM(quantity*price) AS bill FROM customers INNER JOIN orders ON customers.customer_id=orders.customer_id INNER JOIN ordered_items ON orders.order_id= ordered_items.order_id INNER JOIN menu ON ordered_items.dish = menu.dish WHERE ordered_items.order_id=? GROUP BY orders.order_id, customers.customer_id, name, email, mobile";
            return jdbcTemplate.queryForObject(query, new CustomerBillMapper(), new Object[]{order_id});
        } catch (Exception e) {
            System.out.println("inside catch");
            System.out.println(e);
        }
        return null;
    }
    @Override
    public Customer getCustRevenue(Integer customer_id) {
        System.out.println("inside getCustomerRevenue by customer_id");

        try{
            System.out.println("inside try's of getCustomerRevenue by customer_id");
            String query="SELECT customers.customer_id, name, email, mobile, SUM(quantity*price) AS revenue FROM customers INNER JOIN orders ON customers.customer_id=orders.customer_id INNER JOIN ordered_items ON orders.order_id=ordered_items.order_id INNER JOIN menu ON ordered_items.dish = menu.dish WHERE customers.customer_id=? GROUP BY customers.customer_id, name, email, mobile";
            return jdbcTemplate.queryForObject(query, new CustomerRevenueMapper(), new Object[]{customer_id});
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Customer getCustRevenueByMob(String mobile) {
        System.out.println("inside getCustomerRevenue by mobile");

        try{
            System.out.println("inside try's of getCustomerRevenue by mobile");
            String query="SELECT customers.customer_id, name, email, mobile, SUM(quantity*price) AS revenue FROM customers INNER JOIN orders ON customers.customer_id=orders.customer_id INNER JOIN ordered_items ON orders.order_id=ordered_items.order_id INNER JOIN menu ON ordered_items.dish = menu.dish WHERE mobile=? GROUP BY customers.customer_id, name, email, mobile";
            return jdbcTemplate.queryForObject(query, new CustomerRevenueMapper(), new Object[]{mobile});
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Integer getCafeRevenue() {
        try{
            String query=" SELECT SUM(revenue) AS total_revenue FROM (SELECT customers.customer_id, name, email, mobile, SUM(quantity*price) AS revenue FROM customers INNER JOIN orders ON customers.customer_id=orders.customer_id INNER JOIN ordered_items ON orders.order_id=ordered_items.order_id INNER JOIN menu ON ordered_items.dish = menu.dish GROUP BY customers.customer_id, name, email, mobile) AS t";
            return jdbcTemplate.queryForObject(query,Integer.class);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

}



