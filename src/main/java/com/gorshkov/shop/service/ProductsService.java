package com.gorshkov.shop.service;

import com.gorshkov.shop.dao.JdbcProductsDao;
import com.gorshkov.shop.model.Product;

import java.util.List;

public class ProductsService {
    private final JdbcProductsDao jdbcProductsDao;

    public ProductsService(JdbcProductsDao jdbcProductsDao) {
        this.jdbcProductsDao = jdbcProductsDao;
    }

    public List<Product> findAll() {
        return jdbcProductsDao.findAll();
    }

    public void add(Product product) {
        jdbcProductsDao.save(product);
    }

    public void update(Product product) {
        jdbcProductsDao.update(product);
    }

    public void delete(int productId) {
        jdbcProductsDao.delete(productId);
    }
}
