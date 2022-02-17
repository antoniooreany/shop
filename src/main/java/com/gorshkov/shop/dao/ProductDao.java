package com.gorshkov.shop.dao;

import com.gorshkov.shop.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ProductDao {

    List<Product> findAll();

    void processAdd(HttpServletRequest request, Map<String, Object> pageVariables);

    void processUpdate(HttpServletRequest request, Map<String, Object> pageVariables);

    void processDelete(HttpServletRequest request, Map<String, Object> pageVariables);
}
