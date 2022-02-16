package com.gorshkov.shop;

import com.gorshkov.shop.dao.ProductsAddDao;
import com.gorshkov.shop.dao.ProductsDao;
import com.gorshkov.shop.service.ProductsAddService;
import com.gorshkov.shop.service.ProductsService;
import com.gorshkov.shop.servlet.ProductsAddServlet;
import com.gorshkov.shop.servlet.ProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        ProductsDao productsDao = new ProductsDao();
        ProductsService productsService = new ProductsService(productsDao);
        ProductsServlet productsServlet = new ProductsServlet(productsService);

        ProductsAddDao productAddDao = new ProductsAddDao();
        ProductsAddService productsAddService = new ProductsAddService(productAddDao);
        ProductsAddServlet productsAddServlet = new ProductsAddServlet(productsAddService);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(productsServlet), "/products");
        contextHandler.addServlet(new ServletHolder(productsAddServlet), "/products/add"); //todo is it correct to add more than 1 servlet to the handler?

        Server server = new Server(3000);
        server.setHandler(contextHandler);

        server.start();
    }
}
