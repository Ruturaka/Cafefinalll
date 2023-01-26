package com.confluxsys.demo.controller;

import com.confluxsys.demo.bean.CustomerOrder;
import com.confluxsys.demo.bean.OrderItems;
import com.confluxsys.demo.model.customer.Customer;
import com.confluxsys.demo.model.orderedItems.OrderedItems;
import com.confluxsys.demo.model.orders.Order;
import com.confluxsys.demo.spring.dao.customer.CustomerDao;
import com.confluxsys.demo.spring.dao.order.OrderDao;
import com.confluxsys.demo.spring.dao.orderedItmes.OrderedItemsDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping("/capi")

public class CustomerController {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    OrderedItemsDao orderedItemsDao;

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomerOrder(@RequestBody CustomerOrder body) {

        try{
            Customer cust = new Customer();
            cust.setName(body.getName());
            cust.setEmail(body.getEmail());
            cust.setMobile(body.getMobile());
            Integer customer_id= customerDao.addCustomer(cust);
            System.out.println("customer_id="+customer_id);

            Order o=new Order(customer_id);
            Integer order_id= orderDao.createOrder(o);
            System.out.println("order_id"+order_id);

            OrderedItems oi= new OrderedItems(order_id);
            List<OrderItems> orderItemsList = body.getOrderItemsList();


            Integer item_id;
            //item_id= orderedItemsDao.createOrderedItems(oi);
            for (OrderItems item : orderItemsList) {
                oi.setOrder_id(order_id);
                oi.setDish(item.getDish());
                oi.setQuantity(item.getQuantity());
                oi.setFeedback(item.getFeedback());
                System.out.println("order - " + oi);
                item_id= orderedItemsDao.createOrderedItems(oi);
                System.out.println("item_id: "+item_id);
            }
            System.out.println("Done!!");

            return new ResponseEntity<>("CustomerOrder was created successfully. ", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   /* @Autowired
    CustomerDao customerDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    OrderedItemsDao orderedItemsDao;

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomerOrder(@RequestBody CustomerOrder body) {
        try {

            Customer cust = new Customer();
            cust.setName(body.getName());
            cust.setEmail(body.getEmail());
            cust.setMobile(body.getMobile());
            Integer customer_id= customerDao.createCustomer(cust);
            System.out.println("customer_id="+customer_id);

            Order o=new Order(customer_id);
            Integer order_id= orderDao.createOrder(o);
            System.out.println("order_id"+order_id);


            //orderedItemsDao.addOrderItems(orderId, orderItemsList);


           // Integer order_id= orderDao.createOrder(o);
            //System.out.println("order_id for orders- "+order_id);

           /* OrderedItems oi= new OrderedItems(order_id);
            List<OrderItems> orderItemsList = body.getOrderItemsList();

            for (OrderItems item : orderItemsList) {
                oi.setOrder_id(order_id);
                oi.setDish(item.getDish());
                oi.setQuantity(item.getQuantity());
                oi.setFeedback(item.getFeedback());
                System.out.println("order - "+oi);
                orderedItemsDao.createOrderedItems(oi);
            }


            return new ResponseEntity<>("CustomerOrder was created successfully. ", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/
}
