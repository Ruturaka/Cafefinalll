package com.confluxsys.demo.spring.dao.customer;

import com.confluxsys.demo.bean.CustomerOrder;
import com.confluxsys.demo.model.Orders.Order;
import com.confluxsys.demo.model.customer.Customer;
import com.confluxsys.demo.model.menu.Menu;

import java.util.List;

public interface CustomerDao {

   Integer addCustomer(Customer customer);

   Integer save(Customer customer);

   Integer update(Customer customer);

   Customer findById(Integer id);

   Integer deleteById(Integer id);

   List<Customer> findAll();

   List<Customer> findByMobile(String mobile);

   List<Customer> findByNameContaining(String name);

   Integer deleteAll();




   Customer getCustomerById(Integer id);

   Integer deleteCustomer(Integer order_id); //
   Customer getCustomerByMobile(String mobile); //get

   List<Customer> getAllCustomerOrders(); //get

   List<Customer> findCustOrderByNameContaining(String name); //get

   List<Customer> findCustOrderByMobile(String mobile); //get

   Customer getBillByOrder_id(Integer order_id); //get

   Customer getCustRevenue(Integer customer_id); //get

   Customer getCustRevenueByMob(String mobile); //get revenue by mob

   Integer getCafeRevenue();
   List<Customer> getCustomerHistory(String mobile); //get history

}

