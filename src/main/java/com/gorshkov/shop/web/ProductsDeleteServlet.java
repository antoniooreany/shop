package com.gorshkov.shop.web;

import com.gorshkov.shop.service.ProductsService;
import com.gorshkov.shop.templater.PageVarialbesCreator;
import com.gorshkov.shop.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ProductsDeleteServlet extends HttpServlet {

    private final ProductsService productsService;

    public ProductsDeleteServlet(ProductsService productsDeleteService) {
        this.productsService = productsDeleteService;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        Map<String, Object> pageVariables = PageVarialbesCreator.createPageVariablesMap(request);
        pageVariables.put("message", "");

        response.getWriter().println(PageGenerator.instance().getPage("productsDelete.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> pageVariables = PageVarialbesCreator.createPageVariablesMap(request);
        response.setContentType("text/html;charset=utf-8");

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("productsDelete.html", pageVariables);
        try {
            response.getWriter()
                    .println(page);
        } catch (IOException e) {
            throw new RuntimeException("Something is wrong with IO", e);
        }
        productsService.processDelete(request, pageVariables);
    }
}