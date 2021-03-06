package com.gorshkov.shop.web.servlet;

import com.gorshkov.shop.model.Product;
import com.gorshkov.shop.service.ProductsService;
import com.gorshkov.shop.templater.PageGenerator;
import com.gorshkov.shop.templater.PageVarialbesCreator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductsAddServlet extends HttpServlet {

    private final ProductsService productsService;

    public ProductsAddServlet(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println(PageGenerator.instance().getPage("productsAdd.html", new HashMap<>()));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("productsAdd.html", new HashMap<>());
        try {
            response.getWriter().println(page);
        } catch (IOException e) {
            throw new RuntimeException("Something is wrong with IO", e);
        }

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        Product product = new Product(id, name, price);
        productsService.add(product);
//        response.sendRedirect("/products");
    }
}
