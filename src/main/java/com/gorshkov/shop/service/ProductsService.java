package com.gorshkov.shop.service;

import com.gorshkov.shop.dao.ProductsDao;
import com.gorshkov.shop.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class ProductsService {
    private final ProductsDao productsDao;

    public ProductsService(ProductsDao productsDao) {
        this.productsDao = productsDao;
    }

    public List<Product> findAll() {
        return productsDao.findAll();
    }

    public void processAdd(HttpServletRequest request, Map<String, Object> pageVariables) {
        productsDao.processAdd(request, pageVariables);
    }

    public void processUpdate(HttpServletRequest request, Map<String, Object> pageVariables) {
        productsDao.processUpdate(request, pageVariables);
    }

    public void processDelete(HttpServletRequest request, Map<String, Object> pageVariables) {
        productsDao.processDelete(request, pageVariables);
    }
}
