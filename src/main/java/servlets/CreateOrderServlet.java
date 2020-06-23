/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Client;
import models.Product;

/**
 *
 * @author Erick
 */
public class CreateOrderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        String productsJSON = request.getParameter("order");
        String userJSON = request.getParameter("user");

        JsonObject json = new JsonObject();

        // put some value pairs into the JSON object .
        json.addProperty("Message", "OK");

        System.out.println(productsJSON);
        System.out.println(userJSON);

        Gson g = new GsonBuilder().setPrettyPrinting().create();

        Type productListType = new TypeToken<ArrayList<Product>>() {
        }.getType();

        ArrayList<Product> products;

        try {
            products = g.fromJson(productsJSON, productListType);
        } catch (JsonSyntaxException e) {
            System.err.println(e.getMessage());
        }

        Client client;
        try {
            client = g.fromJson(userJSON, Client.class);
            System.out.println(client.toString());
        } catch (JsonSyntaxException e) {
            System.err.println(e.getMessage());
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("Ok!");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
