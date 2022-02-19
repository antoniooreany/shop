package com.gorshkov.shop.service;

import com.gorshkov.shop.dao.JdbcProductsDao;
import com.gorshkov.shop.dao.ProductsDao;
import com.gorshkov.shop.model.Product;

import java.util.List;

public class ProductsService {
    private final ProductsDao productsDao;

    public ProductsService(JdbcProductsDao productDao) {
        this.productsDao = productDao;
    }

    public List<Product> findAll() {
        return productsDao.findAll();
    }

    public void add(Product product) {
        productsDao.save(product);
    }

    public void update(Product product) {
        productsDao.update(product);
    }

    public void delete(int productId) {
        productsDao.delete(productId);
    }
}
