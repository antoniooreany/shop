package com.gorshkov.shop.web;

import com.gorshkov.shop.model.Product;
import com.gorshkov.shop.service.ProductsService;
import com.gorshkov.shop.templater.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsServlet extends HttpServlet {

    private final ProductsService productsService;
    private final PageGenerator pageGenerator = PageGenerator.instance();
    private final List<String> tokens;

    public ProductsServlet(ProductsService productsService, List<String> tokens) {
        this.productsService = productsService;
        this.tokens = tokens;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        boolean isValid = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user-token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    if (tokens.contains(token)) {
                        isValid = true;
                    }
                    break;
                }
            }
        }
        if (!isValid) {
            response.sendRedirect("/login");
        }

        List<Product> productsList = productsService.findAll();

        response.setStatus(HttpServletResponse.SC_OK);

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("products", productsList);


        String page = pageGenerator.getPage("products.html", pageVariables);
        response.getWriter().println(page);
    }
}
