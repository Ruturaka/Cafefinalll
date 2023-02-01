package com.confluxsys.demo.controller;

import com.confluxsys.demo.model.customer.Customer;
import com.confluxsys.demo.model.orderedItems.OrderedItems;
import com.confluxsys.demo.spring.dao.Order.OrderDao;
import com.confluxsys.demo.spring.dao.customer.CustomerDao;
import com.confluxsys.demo.spring.dao.orderedItems.OrderedItemsDao;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:9999")
@RestController
@RequestMapping("/oapi")
public class OrdersController {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    OrderedItemsDao orderedItemsDao;

    @Autowired
    OrderDao orderDao;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderedItems>> getAllCustomers(@RequestParam(required = false) String dish){
        try {
            System.out.println("inside OrderedItems");
            List<OrderedItems> orderedItemsList = new ArrayList<OrderedItems>();

            if (dish == null) {
                System.out.println("iside its if");
                orderedItemsDao.findAll().forEach(orderedItemsList::add);
            }
            else {
                System.out.println("inside its else");
                orderedItemsDao.findByDish(dish).forEach(orderedItemsList::add);
            }
            if (orderedItemsList.isEmpty()) {
                System.out.println("iniside is empty if()");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orderedItemsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable("id") Integer id) {
        try {
            int result = orderDao.deleteById(id);
            int res = customerDao.deleteById(id);

            if (result == 0 && res==0) {
                return new ResponseEntity<>("Cannot find Order with order_id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Order deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Cannot delete order.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/orders")
    public ResponseEntity<String> deleteAll() {
        try {
            int numRows = orderDao.deleteAll();
            return new ResponseEntity<>("Deleted" + numRows + "order(s) successfully", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Cannot delete orders", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
