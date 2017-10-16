package com.example.coyclab.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Date;

@Entity
public class Product {

    @Id
    private Long id;
    private String name;
    private Double price;
    private int discount;
    private Date fromDate;
    private Date toDate;

    public Product() {
    }

    public Product(Long id, String name, Double price, int discount, Date fromDate, Date toDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.fromDate = fromDate;
        this.toDate = toDate;
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

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }
}
