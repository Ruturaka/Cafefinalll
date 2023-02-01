package com.confluxsys.demo.controller;

import com.confluxsys.demo.model.customer.Customer;
import com.confluxsys.demo.spring.dao.customer.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:9999")
@RestController
@RequestMapping("/capi")
public class CustomerController {

    @Autowired
    CustomerDao customerDao;

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(required = false) String name){
        try {
            List<Customer> customers = new ArrayList<Customer>();

            if (name == null)
                customerDao.findAll().forEach(customers::add);
            else
                customerDao.findByNameContaining(name).forEach(customers::add);
            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getDishById(@PathVariable("id") Integer id) {
        Customer customer = customerDao.findById(id);

        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customer")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        try {
            customerDao.save(new Customer(customer.getName(), customer.getEmail(), customer.getMobile()));
            return new ResponseEntity<>("Customer created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") Integer id, @RequestBody Customer customer) {
        Customer _customer = customerDao.findById(id);

        if (_customer != null) {
            _customer.setName(customer.getName());
            _customer.setEmail(customer.getEmail());
            _customer.setMobile(customer.getMobile());

            customerDao.update(_customer);
            return new ResponseEntity<>("Customer updated successfully.", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Cannot find Customer with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customer/{customer_id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable("customer_id") Integer customer_id) {
        try {
            int result = customerDao.deleteById(customer_id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Customer with id=" + customer_id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Customer deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Cannot delete customer.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customer")
    public ResponseEntity<String> deleteAll() {
        try {
            int numRows = customerDao.deleteAll();
            return new ResponseEntity<>("Deleted" + numRows + "Customer(s) successfully", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Cannot delete customers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
