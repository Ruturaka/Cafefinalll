package com.confluxsys.demo.spring.dao.order;

import com.confluxsys.demo.model.orderedItems.OrderedItems;
import com.confluxsys.demo.model.orders.Order;

public interface OrderDao {
    Integer createOrder(Order order); //post
    //orderedItemsDao.addOrderItems(orderId, orderItemsList);
    //Integer addOrderItems(Integer orderId, OrderedItems orderedItemsList);

}
