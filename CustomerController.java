package com.confluxsys.demo.controller;

import com.confluxsys.demo.bean.CustomerOrder;
import com.confluxsys.demo.bean.OrderItems;
import com.confluxsys.demo.model.Orders;
import com.confluxsys.demo.spring.dao.CustomerDao;
import com.confluxsys.demo.spring.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerOrder body) {
        try {

            CustomerOrder cust = new CustomerOrder();
            cust.setName(body.getName());
            cust.setEmail(body.getEmail());
            cust.setMobile(body.getMobile());
            cust.setOrderItemsList(body.getOrderItemsList());
            List<OrderItems> orderItemsList = body.getOrderItemsList();

            customerDao.createCustomer(cust);
            Orders o=new Orders();
            for (OrderItems item : orderItemsList) {

                o.setDish(item.getDish());
                o.setQuantity(item.getQuantity());
                o.setFeedback(item.getFeedback());
                System.out.println("order - "+o);
                orderDao.createOrder(o);
            }


                return new ResponseEntity<>("CustomerOrder was created successfully. ", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
