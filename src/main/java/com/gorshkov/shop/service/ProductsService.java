package com.gorshkov.shop.service;

import com.gorshkov.shop.dao.ProductsDao;
import com.gorshkov.shop.model.Product;

import java.util.List;

public class ProductsService {
    private final ProductsDao productsDao;

    public ProductsService(ProductsDao productsDao) {
        this.productsDao = productsDao;
    }

    public List<Product> findAll() {
        return productsDao.findAll();
    }
}
