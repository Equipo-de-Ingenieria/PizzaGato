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
import models.Client;
import services.ClientService;

/**
 *
 * @author Erick
 */
@WebServlet(
        name = "ClientSigninServlet",
        urlPatterns = {"/ClientSigninServlet"}
)
public class ClientSigninServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idcard = request.getParameter("userInput");
        String pass = request.getParameter("passInput");
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        Client client = null;
        client = new Client(idcard, name, lastname, address, phone, email, pass);
        ClientService cs = new ClientService();
        if(cs.insertClient(client)){
            System.out.print("Yes");
        }else{
            System.out.print("No");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "index.jsp");
        dispatcher.forward(request, response);
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
