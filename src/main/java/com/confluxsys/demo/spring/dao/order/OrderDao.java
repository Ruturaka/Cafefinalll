package com.confluxsys.demo.spring.dao.Order;

import com.confluxsys.demo.model.Orders.Order;
import com.confluxsys.demo.model.orderedItems.OrderedItems;

import java.util.List;

public interface OrderDao {
    Integer createOrder(Order order); //post

    Integer deleteById(Integer id);

    Integer deleteAll();


}
