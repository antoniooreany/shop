package com.gorshkov.shop.dao;

import com.gorshkov.shop.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    void save(Product product);

    void update(Product product);

    void delete(int productId);
}
