package com.gorshkov.shop;

import com.gorshkov.shop.dao.ProductsDao;
import com.gorshkov.shop.service.ProductsService;
import com.gorshkov.shop.servlet.ProductsAddServlet;
import com.gorshkov.shop.servlet.ProductsDeleteServlet;
import com.gorshkov.shop.servlet.ProductsServlet;
import com.gorshkov.shop.servlet.ProductsUpdateServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        ProductsDao productsDao = new ProductsDao();
        ProductsService productsService = new ProductsService(productsDao);

        ProductsServlet productsServlet = new ProductsServlet(productsService);
        ProductsAddServlet productsAddServlet = new ProductsAddServlet(productsService);
        ProductsUpdateServlet productsUpdateServlet = new ProductsUpdateServlet(productsService);
        ProductsDeleteServlet productsDeleteServlet = new ProductsDeleteServlet(productsService);

        contextHandler.addServlet(new ServletHolder(productsServlet), "/products");
        contextHandler.addServlet(new ServletHolder(productsAddServlet), "/products/add");
        contextHandler.addServlet(new ServletHolder(productsUpdateServlet), "/products/update");
        contextHandler.addServlet(new ServletHolder(productsDeleteServlet), "/products/delete");

        Server server = new Server(3000);
        server.setHandler(contextHandler);

        server.start();
    }
}
