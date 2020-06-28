/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Erick
 */
@WebServlet(
        name = "ClientRedirectServlet",
        urlPatterns = {"/ClientRedirectServlet"}
)
public class ClientRedirectServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String website = request.getParameter("menu");
        String url = null;

        try (PrintWriter out = response.getWriter()) {
            if (website != null && !website.isEmpty()) {
                switch (website) {
                    case "start":
                        url = "WEB-INF/clientMenu.jsp";
                        break;

                    case "order":
                        url = "WEB-INF/orderMenu.jsp";
                        break;

                    case "account":
                        url = "WEB-INF/clientAccount.jsp";
                        break;

                    case "orders":
                        url = "WEB-INF/clientOrders.jsp";
                        break;

                    case "feedback":
                        url = "WEB-INF/clientFeedback.jsp";

                }

                if (url != null) {
                    response.setStatus(200);
                    out.print("OK");

                    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                    dispatcher.forward(request, response);
                } else {
                    response.sendError(404);
                    out.print("Cannot find page");
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
