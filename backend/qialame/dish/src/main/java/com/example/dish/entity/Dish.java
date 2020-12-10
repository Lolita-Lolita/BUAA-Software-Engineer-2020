package com.example.dish.entity;


public class Dish
{
    private Integer id;

    private String dishName;

    private Double dishPrice;

    private String taste;

    private String dishType;

    private String location;

    //这里应该还有一个comment的实体，和Dish实体类保持一对一关系

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getDishName()
    {
        return dishName;
    }

    public void setDishName(String dishName)
    {
        this.dishName = dishName;
    }

    public Double getDishPrice()
    {
        return dishPrice;
    }

    public void setDishPrice(Double dishPrice)
    {
        this.dishPrice = dishPrice;
    }

    public String getTaste()
    {
        return taste;
    }

    public void setTaste(String taste)
    {
        this.taste = taste;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public Dish()
    {

    }

    public Dish(Integer id, String dishName, Double dishPrice, String taste, String dishType, String location) {
        this.id = id;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.taste = taste;
        this.dishType = dishType;
        this.location = location;
    }
}
