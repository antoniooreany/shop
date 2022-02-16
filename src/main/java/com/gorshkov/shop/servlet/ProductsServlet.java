package com.gorshkov.shop.servlet;

import com.gorshkov.shop.model.Connector;
import com.gorshkov.shop.model.Product;
import com.gorshkov.shop.service.ProductsService;
import com.gorshkov.shop.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsServlet extends HttpServlet {

    private final ProductsService productsService;

    public ProductsServlet(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productsList = productsService.findAll();

        response.setStatus(HttpServletResponse.SC_OK);

        PageGenerator pageGenerator = PageGenerator.instance();
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("products", productsList);
        String page = pageGenerator.getPage("products.html", pageVariables);
        response.getWriter().println(page);
    }

}
