package com.gorshkov.shop.service;

import com.gorshkov.shop.dao.ProductsAddDao;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ProductsAddService {
    private final ProductsAddDao productAddDao;

    public ProductsAddService(ProductsAddDao productAddDao) {
        this.productAddDao = productAddDao;
    }

    public void process(HttpServletRequest request, Map<String, Object> pageVariables) {
        productAddDao.process(request, pageVariables);
    }
}
