package com.example.coyclab.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Product {

    @Id
    private Long id;
    private String name;
    private Double price;
    private int discount;

    public Product() {
    }

    public Product(final Long id, final String name, final Double price, final int discount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }
}
