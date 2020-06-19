/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Product;
import org.json.simple.JSONArray;
import services.ProductService;

/**
 *
 * @author Extreme PC
 */
@WebServlet(name = "PizzaMenuServlet", urlPatterns = {"/PizzaMenuServlet"})
public class PizzaMenuServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
     
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("method").equals("delete")) {
                delete(request.getParameter("row"));

            } else if (request.getParameter("method").equals("add")) {//String code, String size, String description, double price
                Product product = new Product(request.getParameter("code"), request.getParameter("size"), request.getParameter("desc"), Double.parseDouble(request.getParameter("price")));
                add(product);
            } else if (request.getParameter("method").equals("update")) {
                Product product = new Product(request.getParameter("code"), request.getParameter("size"), request.getParameter("desc"), Double.parseDouble(request.getParameter("price")));
                update(product);
            }
            out.println("Ok!");
        }

    }

    public void delete(String code) {
        ProductService.deleteProduct(Integer.parseInt(code));
    }

    public void add(Product pro) {
        ProductService.createProduct(pro);
    }

    public void update(Product pro) {
        ProductService.updateProduct(pro);
    }

    public String getProducts() {
        ArrayList<Product> details = ProductService.getProductsDB();
        JSONArray JA = new JSONArray();
        details.forEach((p) -> {
            JA.add(p.toJSON());
        });
        return JA.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
