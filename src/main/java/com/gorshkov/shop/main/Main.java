package com.gorshkov.shop.main;

import com.gorshkov.shop.servlet.ProductAddServlet;
import com.gorshkov.shop.servlet.ProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        ProductDao productDao = new ProductDao();
        ProductService productService = new ProductService(productDao);
        ProductsServlet productsServlet = new ProductsServlet(productService);

        ProductAddDao productAddDao = new ProductAddDao();
        ProductAddService productAddService = new ProductAddService(productAddDao);
        ProductAddServlet productAddServlet = new ProductAddServlet(productAddService);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(productsServlet), "/products");
        contextHandler.addServlet(new ServletHolder(productAddServlet), "/product/add"); //todo is it correct to add more than 1 servlet to the handler?

        Server server = new Server(3000);
        server.setHandler(contextHandler);

        server.start();
    }
}
