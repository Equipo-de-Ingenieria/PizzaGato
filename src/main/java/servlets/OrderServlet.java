/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Product;
import services.ProductService;

/**
 *
 * @author Erick
 */
public class OrderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        ArrayList <Product> products = null;
        products = ProductService.getProducts();
        
        try (PrintWriter out = response.getWriter()) {
            if(products != null && !products.isEmpty()){
                Gson g = new GsonBuilder().setPrettyPrinting().create();
                out.print(g.toJson(products));
                
                System.out.println(g.toJson(products));
                
            }
            else{
                response.sendError(1, "No se pudo recuperar los datos");
                System.out.println("No existen productos");
            }
            
        }
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
