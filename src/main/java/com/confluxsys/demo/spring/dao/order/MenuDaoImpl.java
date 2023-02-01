package com.confluxsys.demo.spring.dao.menu;

import com.confluxsys.demo.bean.CustomerOrder;
import com.confluxsys.demo.model.menu.Menu;
import com.confluxsys.demo.model.menu.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class MenuDaoImpl implements MenuDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Menu menu){
        String query="INSERT INTO menu(dish, price) VALUES (?,?)";
        return jdbcTemplate.update(query,new Object[]{menu.getDish(), menu.getPrice()});

    }

    @Override
    public int update(Menu menu){
        String query="UPDATE menu SET dish=?, price=? WHERE id=?";
        return jdbcTemplate.update(query, new Object[]{menu.getDish(), menu.getPrice(), menu.getId()});
    }

    @Override
    public Menu findById(Integer id){
        try{
            String query="SELECT * FROM menu WHERE id=?";
            Menu menu= jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Menu.class), id);
            return menu;
        }
        catch (IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    @Override
    public int deleteById(Integer id){
        return jdbcTemplate.update("DELETE FROM menu WHERE id=?", id);
    }

    @Override
    public List<Menu> findAll(){
        return jdbcTemplate.query("SELECT * FROM menu", BeanPropertyRowMapper.newInstance(Menu.class));
    }

    @Override
    public List<Menu> findByDish(String dish){
        String query="SELECT * FROM menu WHERE dish=?";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Menu.class), dish);
    }

    @Override
    public List<Menu> findByDishContaining(String dish){
        String query="SELECT * FROM menu WHERE dish ILIKE '%" + dish + "%'";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Menu.class));
    }

    @Override
    public int deleteAll(){
        return jdbcTemplate.update("DELETE from menu");
    }

}
