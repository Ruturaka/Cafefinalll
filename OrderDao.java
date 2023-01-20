package com.confluxsys.demo.spring.dao;

import com.confluxsys.demo.bean.CustomerOrder;
import com.confluxsys.demo.model.Orders;

public interface OrderDao {
    int createOrder(Orders customer); //post

}
