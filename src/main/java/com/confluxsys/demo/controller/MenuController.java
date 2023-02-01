package com.confluxsys.demo.controller;

import com.confluxsys.demo.model.menu.Menu;
import com.confluxsys.demo.spring.dao.menu.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:9999")
@RestController
@RequestMapping("/mapi")
public class MenuController {

    @Autowired
    MenuDao menuDao;

    @GetMapping("/menu")
    public ResponseEntity<List<Menu>> getWholeMenu(@RequestParam(required = false) String dish){
        try {
            List<Menu> menu = new ArrayList<Menu>();

            if (dish == null)
                menuDao.findAll().forEach(menu::add);
            else
                menuDao.findByDishContaining(dish).forEach(menu::add);
            if (menu.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/menu/{id}")
    public ResponseEntity<Menu> getDishById(@PathVariable("id") Integer id) {
        Menu menu = menuDao.findById(id);

        if (menu != null) {
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/menu")
    public ResponseEntity<String> createCustomer(@RequestBody Menu menu) {
        try {
            menuDao.save(new Menu(menu.getDish(), menu.getPrice()));
            return new ResponseEntity<>("Dish created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/menu/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") Integer id, @RequestBody Menu menu) {
        Menu _menu = menuDao.findById(id);

        if (_menu != null) {
            _menu.setDish(menu.getDish());
            _menu.setPrice(menu.getPrice());

            menuDao.update(_menu);
            return new ResponseEntity<>("Dish updated successfully.", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Cannot find Order with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<String> deleteDishById(@PathVariable("id") Integer id) {
        try {
            int result = menuDao.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Order with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Dish deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete order.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/menu")
    public ResponseEntity<String> deleteAll() {
        try {
            int numRows = menuDao.deleteAll();
            return new ResponseEntity<>("Deleted" + numRows + "Menu(s) successfully", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Cannot delete dishes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
