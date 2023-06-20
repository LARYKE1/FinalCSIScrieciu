package com.example.finalcsiscrieciu;

import java.io.Serializable;
import java.lang.reflect.Type;

public class CoffeType implements Serializable {
    private int imageResource;
    private String name;
    private String description;
    public String type;
    private double price;
    private String extra;
    private int quantity;

    public CoffeType(int imageResource, String name, String description, String type, double price, String extra, int quantity) {
        this.imageResource = imageResource;
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.extra = extra;
        this.quantity = quantity;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
