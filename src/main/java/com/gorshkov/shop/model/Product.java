package com.gorshkov.shop.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private final int id;
    private final String name;
    private final double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
