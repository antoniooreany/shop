package com.gorshkov.shop.servlet;

import com.gorshkov.shop.main.ProductAddService;
import com.gorshkov.shop.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductAddServlet extends HttpServlet {

    private final ProductAddService productAddService;

    public ProductAddServlet(ProductAddService productAddService) {
        this.productAddService = productAddService;
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);

//        String message = request.getParameter("message");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");

        response.setContentType("text/html;charset=utf-8");

//        if (message == null || message.isEmpty()) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        } else {
//            response.setStatus(HttpServletResponse.SC_OK);
//        }
//        pageVariables.put("message", message == null ? "" : message);
        pageVariables.put("id", id == null ? "" : id);
        pageVariables.put("name", name == null ? "" : name);
        pageVariables.put("price", price == null ? "" : price);

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("productAdd.html", pageVariables);
        response.getWriter()
                .println(page);

//        System.out.println(request.getParameterNames());
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }


}
