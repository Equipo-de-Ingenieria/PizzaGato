/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Admin;
import models.Client;
import services.AdminService;
import services.ClientService;

/**
 *
 * @author Extreme PC
 */
@WebServlet(
        name = "LoginServlet",
        urlPatterns = {"/LoginServlet"}
)
public class LoginServlet extends HttpServlet {

    private void processRequest(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Admin admin = null;
        Client client = null;
        String email = request.getParameter("userInput");
        String pass = request.getParameter("passInput");

        //Check if the inputs have something
        if (email != null && pass != null) {
            admin = AdminService.getAdmin(email, pass);
            client = ClientService.getClient(email, pass);
        }
// userService.getUser(id, pass)
//                    .ifPresent(user -> request.getSession(true).setAttribute("userData", user));
        //Check if its admin or client
        if (client != null) { //Client
            request.getSession(true).setAttribute("userData", null);
            request.getSession(true).setAttribute("userData", client);
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "clientMenu.jsp");
            dispatcher.forward(request, response);
        } else if (admin != null) { //Admin
            request.getSession(true).setAttribute("userData", null);
            request.getSession(true).setAttribute("userData", admin);
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "adminMenu.jsp");
            dispatcher.forward(request, response);
        } else { //If there is no admin or client in the DB, go back to login
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "index.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
