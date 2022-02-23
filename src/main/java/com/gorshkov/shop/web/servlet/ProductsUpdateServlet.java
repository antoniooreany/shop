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

public class ProductsUpdateServlet extends HttpServlet {

    private final ProductsService productsService;
    private int id;

    public ProductsUpdateServlet(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pathInfo = request.getPathInfo();
        String[] parts = pathInfo.split("/");
        id = Integer.parseInt(parts[1]); //todo why 1st, not 2nd?
        Product productById = productsService.findById(id);

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("product", productById);
        response.getWriter().println(PageGenerator.instance().getPage("productsUpdate.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        PageGenerator pageGenerator = PageGenerator.instance();
        Map<String, Object> pageVariables = new HashMap<>();
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        Product product = new Product(id, name, price);
        pageVariables.put("product", product);
        String page = pageGenerator.getPage("productsUpdate.html", pageVariables);
        try {
            response.getWriter().println(page);
        } catch (IOException e) {
            throw new RuntimeException("Something is wrong with IO", e);
        }
        productsService.update(product);
//        response.sendRedirect("/products");

    }
}
