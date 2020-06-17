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
import models.FeedBack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import services.FeedbackService;


/**
 *
 * @author Extreme PC
 */
@WebServlet(name = "CommentarieServlet", urlPatterns = {"/CommentarieServlet"})
public class CommentarieServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FeedbackService fb = new FeedbackService();
        ArrayList<FeedBack> details = fb.getFeedBacks();
      
        try (PrintWriter out = response.getWriter()) {
            JSONObject r = new JSONObject();
            JSONArray JA = new JSONArray();
            details.forEach((p) -> {
                JA.add(p.toJSON());
            });
            r.put("data", JA);
            out.println(r);
        }
    }

    public String getFeedBacks() {
        FeedbackService fb = new FeedbackService();
        ArrayList<FeedBack> details = fb.getFeedBacks();
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
