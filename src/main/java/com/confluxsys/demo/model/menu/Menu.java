package com.confluxsys.demo.model.menu;

public class Menu {
    Integer id;
    String dish;
    Integer price;

    public Menu() {
    }

    public Menu(String dish, Integer price) {
        this.dish = dish;
        this.price=price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
