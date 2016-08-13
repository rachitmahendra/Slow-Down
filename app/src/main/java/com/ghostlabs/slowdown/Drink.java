package com.ghostlabs.slowdown;

import java.io.Serializable;

/**
 * Created by Hp on 13-08-2016.
 */
public class Drink implements Serializable {
    String name;
    double alcohol;
    int standardDrinks;


    public Drink(){}

public Drink(String name ,double alcohol){
    this.alcohol = alcohol;
    this.name = name;
}
    public String getName(){
        return name;
    }
    public double getAlcohol()
    {
        return alcohol;
    }
    public int getStandardDrinks(){
        return standardDrinks;
    }
    public void setAlcohol(double alcohol){
        this.alcohol = alcohol;
    }
    public void setStandardDrinks(int standardDrinks){
        this.standardDrinks = standardDrinks;
    }
    public void setName(String name){
        this.name = name;
    }
}
