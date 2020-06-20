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
import models.Detail;
import models.Invoice;
import org.json.simple.JSONArray;
import services.DetailService;
import services.InvoiceService;

/**
 *
 * @author Extreme PC
 */
@WebServlet(name = "SellsMenuServlet", urlPatterns = {"/SellsMenuServlet"})
public class SellsMenuServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            int id = Integer.parseInt(request.getParameter("id"));
            out.println(getDetails(id));
        }
    }

    public String getInvoice() {
        ArrayList<Invoice> details = InvoiceService.getAllInvoices();
        JSONArray JA = new JSONArray();
        details.forEach((p) -> {
            JA.add(p.toJSON());
        });
        return JA.toString();
    }

    public String getDetails(int id) {
        ArrayList<Detail> details = DetailService.getDetails(id);
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
