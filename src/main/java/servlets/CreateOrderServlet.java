/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import models.Detail;
import models.Product;
import org.json.simple.JSONObject;
import services.DetailService;
import services.InvoiceService;

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

        // put some value pairs into the JSON object .
        System.out.println(productsJSON);
        System.out.println(userJSON);

        Gson g = new GsonBuilder().setPrettyPrinting().create();

        Type productListType = new TypeToken<ArrayList<Product>>() {
        }.getType();

        ArrayList<Product> products = new ArrayList();

        try {
            products = g.fromJson(productsJSON, productListType);
        } catch (JsonSyntaxException e) {
            System.err.println(e.getMessage());
        }

        Client client = new Client();
        try {
            client = g.fromJson(userJSON, Client.class);
            System.out.println(client.toString());
        } catch (JsonSyntaxException e) {
            System.err.println(e.getMessage());
        }

        try {
            InvoiceService.createInvoice(client.getIdClient());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        ArrayList<Detail> details = new ArrayList();

        int invoiceID = InvoiceService.getLastID();

        products.forEach((product) -> {
            details.add(new Detail(invoiceID, product.getIdProduct(), product.getQuantity()));
        });


        try (PrintWriter out = response.getWriter()) {

            if (DetailService.createDetails(details)) {
                response.setStatus(200);
                
                JSONObject status = new JSONObject();
                status.put("status", "**Compra realizada**  \n\n\nSu numero de orden es: " + String.valueOf(invoiceID));
                out.print(status);
            }
            else{
                response.sendError(404);
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
