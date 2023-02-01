package com.confluxsys.demo.spring.dao.orderedItems;

import com.confluxsys.demo.model.orderedItems.OrderedItems;

import java.util.List;

public interface OrderedItemsDao{

    Integer createOrderedItems(OrderedItems orderedItems); //post
    
    Integer deleteById(Integer id);

    List<OrderedItems> findAll();

    List<OrderedItems> findByDish(String mobile);

    Integer deleteAll();

    }
