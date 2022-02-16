package com.gorshkov.shop.service;

import com.gorshkov.shop.dao.ProductsAddDao;

public class ProductsAddService {
    private final ProductsAddDao productAddDao;

    public ProductsAddService(ProductsAddDao productAddDao) {
        this.productAddDao = productAddDao;
    }
}
