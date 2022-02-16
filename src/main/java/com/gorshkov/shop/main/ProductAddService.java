package com.gorshkov.shop.main;

public class ProductAddService {
    private final ProductAddDao productAddDao;

    public ProductAddService(ProductAddDao productAddDao) {
        this.productAddDao = productAddDao;
    }
}
