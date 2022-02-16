package com.gorshkov.shop;

import com.gorshkov.shop.dao.ProductsAddDao;
import com.gorshkov.shop.dao.ProductsDao;
import com.gorshkov.shop.dao.ProductsUpdateDao;
import com.gorshkov.shop.service.ProductsAddService;
import com.gorshkov.shop.service.ProductsService;
import com.gorshkov.shop.service.ProductsUpdateService;
import com.gorshkov.shop.servlet.ProductsAddServlet;
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
        contextHandler.addServlet(new ServletHolder(productsServlet), "/products");

        ProductsAddDao productAddDao = new ProductsAddDao();
        ProductsAddService productsAddService = new ProductsAddService(productAddDao);
        ProductsAddServlet productsAddServlet = new ProductsAddServlet(productsAddService);
        contextHandler.addServlet(new ServletHolder(productsAddServlet), "/products/add");

        ProductsUpdateDao productsUpdateDao = new ProductsUpdateDao();
        ProductsUpdateService productsUpdateService = new ProductsUpdateService(productsUpdateDao);
        ProductsUpdateServlet productsUpdateServlet = new ProductsUpdateServlet(productsUpdateService);
        contextHandler.addServlet(new ServletHolder(productsUpdateServlet), "/products/update");

        Server server = new Server(3000);
        server.setHandler(contextHandler);

        server.start();
    }
}
