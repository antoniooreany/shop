package com.gorshkov.shop.service;

import com.gorshkov.shop.dao.ProductsAddDao;
import com.gorshkov.shop.dao.ProductsUpdateDao;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ProductsUpdateService {
    private final ProductsUpdateDao productsUpdateDao;

    public ProductsUpdateService(ProductsUpdateDao productsUpdateDao) {
        this.productsUpdateDao = productsUpdateDao;
    }

    public void process(HttpServletRequest request, Map<String, Object> pageVariables) {
        productsUpdateDao.process(request, pageVariables);
    }
}
