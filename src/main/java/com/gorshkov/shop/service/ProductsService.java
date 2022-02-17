package com.gorshkov.shop.service;

import com.gorshkov.shop.dao.JdbcProductsDao;
import com.gorshkov.shop.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class ProductsService {
    private final JdbcProductsDao jdbcProductsDao;

    public ProductsService(JdbcProductsDao jdbcProductsDao) {
        this.jdbcProductsDao = jdbcProductsDao;
    }

    public List<Product> findAll() {
        return jdbcProductsDao.findAll();
    }

    public void processAdd(HttpServletRequest request, Map<String, Object> pageVariables) {
        jdbcProductsDao.processAdd(request, pageVariables);
    }

    public void processUpdate(HttpServletRequest request, Map<String, Object> pageVariables) {
        jdbcProductsDao.processUpdate(request, pageVariables);
    }

    public void processDelete(HttpServletRequest request, Map<String, Object> pageVariables) {
        jdbcProductsDao.processDelete(request, pageVariables);
    }
}
