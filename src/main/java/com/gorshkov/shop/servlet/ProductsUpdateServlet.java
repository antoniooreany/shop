package com.gorshkov.shop.servlet;

import com.gorshkov.shop.service.ProductsUpdateService;
import com.gorshkov.shop.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ProductsUpdateServlet extends HttpServlet {

    private final ProductsUpdateService productsUpdateService;

    public ProductsUpdateServlet(ProductsUpdateService productsUpdateService) {
        this.productsUpdateService = productsUpdateService;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        Map<String, Object> pageVariables = PageVarialbesCreator.createPageVariablesMap(request);
        pageVariables.put("message", "");

        response.getWriter().println(PageGenerator.instance().getPage("productUpdate.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> pageVariables = PageVarialbesCreator.createPageVariablesMap(request);
        response.setContentType("text/html;charset=utf-8");

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("productUpdate.html", pageVariables);
        try {
            response.getWriter()
                    .println(page);
        } catch (IOException e) {
            throw new RuntimeException("Something is wrong with IO", e);
        }
        productsUpdateService.process(request, pageVariables);
    }
}
