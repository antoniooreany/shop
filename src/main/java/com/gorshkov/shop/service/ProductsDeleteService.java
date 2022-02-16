package com.gorshkov.shop.service;

import com.gorshkov.shop.dao.ProductsDeleteDao;
import com.gorshkov.shop.dao.ProductsUpdateDao;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ProductsDeleteService {
    private final ProductsDeleteDao productsDeleteDao;

    public ProductsDeleteService(ProductsDeleteDao productsDeleteDao) {
        this.productsDeleteDao = productsDeleteDao;
    }

    public void process(HttpServletRequest request, Map<String, Object> pageVariables) {
        productsDeleteDao.process(request, pageVariables);
    }
}
