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
import org.json.simple.JSONObject;
import services.FeedbackService;

/**
 *
 * @author Erick
 */
public class SendFeedbackServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String idClient = request.getParameter("idClient");
        String message = request.getParameter("message");
        
          try (PrintWriter out = response.getWriter()) {
            if (!idClient.isEmpty() && !message.isEmpty()) {

                if (FeedbackService.insertFeedback(Integer.parseInt(idClient), message)) {
                    response.setStatus(200);
                    JSONObject json = new JSONObject();
                    json.put("status", "Se envio correctamente");
                    out.print(json);

                } else {
                    JSONObject json = new JSONObject();
                    json.put("status", "No se envio");
                    out.print(json);
                }

            } else {

                JSONObject json = new JSONObject();
                json.put("status", "No se envio");
                out.print(json);
            }

        }
        
        catch(Exception e){
            System.out.println(e.getMessage());
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
