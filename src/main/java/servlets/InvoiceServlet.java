/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Invoice;
import org.json.simple.JSONObject;
import services.InvoiceService;

/**
 *
 * @author Erick
 */
public class InvoiceServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("json;charset=UTF-8");

        String idInvoice = request.getParameter("idInvoice");

        try (PrintWriter out = response.getWriter()) {

            if (!idInvoice.isEmpty()) {
                Invoice invoice = InvoiceService.getInvoice(Integer.parseInt(idInvoice));

                if (invoice != null) {
                    response.setStatus(200);
                    JSONObject inJSON = new JSONObject();
                    inJSON.put("idInvoice", String.valueOf(invoice.getIdInvoice()));
                    inJSON.put("status", String.valueOf(invoice.getStatus().getDescription()));
                    inJSON.put("date", String.valueOf(invoice.getDate()));

                    out.print(inJSON);
                } else {
                    response.sendError(301);
                }
            }

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
