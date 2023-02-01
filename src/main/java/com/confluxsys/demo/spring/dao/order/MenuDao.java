package com.confluxsys.demo.spring.dao.menu;


import com.confluxsys.demo.model.menu.Menu;

import java.util.List;

public interface MenuDao {
    int save(Menu dish);

    int update(Menu dish);

    Menu findById(Integer id);

    int deleteById(Integer id);

    List<Menu> findAll();

    List<Menu> findByDish(String dish);

    List<Menu> findByDishContaining(String dish);

    int deleteAll();
}
