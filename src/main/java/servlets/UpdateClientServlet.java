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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Client;
import org.json.simple.JSONObject;
import services.ClientService;

/**
 *
 * @author Erick
 */
public class UpdateClientServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        String idClient = request.getParameter("idClient");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String idCard = request.getParameter("idCard");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        try (PrintWriter out = response.getWriter()) {
            if (!idClient.isEmpty() && !name.isEmpty() && !lastName.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !address.isEmpty() && !password.isEmpty() && !idCard.isEmpty()) {
                Client client = new Client();

                client.setIdClient(Integer.parseInt(idClient));
                client.setIdCard(idCard);
                client.setName(name);
                client.setLastName(lastName);
                client.setAddress(address);
                client.setPhone(phone);
                client.setEmail(email);
                client.setPassword(password);

                if (ClientService.updateClient(client)) {

                    request.getSession(true).setAttribute("userData", client);

                    response.setStatus(200);
                    JSONObject json = new JSONObject();
                    json.put("status", "Se actualizo correctamente");
                    out.print(json);

                    RequestDispatcher dispatcher = request.getRequestDispatcher(
                            "index.jsp");
                    dispatcher.forward(request, response);

                } else {
                    JSONObject json = new JSONObject();
                    json.put("status", "No se actualizo");
                    out.print(json);
                }

            } else {

                JSONObject json = new JSONObject();
                json.put("status", "No se actualizo");
                out.print(json);
            }

        }
        
        catch(Exception e){
            System.out.println(e.getMessage());
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
