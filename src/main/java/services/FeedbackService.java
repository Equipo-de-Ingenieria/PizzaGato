/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import models.Detail;
import models.FeedBack;
import models.Product;
import mysqldb.DataBase;
import org.json.simple.JSONArray;
import static services.DetailService.getConnection;
import static services.DetailService.getDetails;

/**
 *
 * @author Extreme PC
 */
public class FeedbackService {

    private static final String GET_FEEDBACKS = "select c.name, f.description, f.idFeedback, f.idClient from Feedbacks f, Clients c where f.idClient = c.idClient group by c.name, f.description;";

    public static Connection getConnection() throws
            ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            IOException,
            SQLException {
        DataBase db = DataBase.getInstance();
        Properties cfg = db.getConfig();
        Connection connection = db.getConnection(
                cfg.getProperty("database"),
                cfg.getProperty("user"),
                cfg.getProperty("password")
        );
        return connection;
    }

    public ArrayList<FeedBack> getFeedBacks() {
        ArrayList<FeedBack> fbs = null;
        FeedBack fb = null;
        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(GET_FEEDBACKS);) {
            stm.clearParameters();
            try (ResultSet rs = stm.executeQuery()) {
                fbs = new ArrayList();
                while (rs.next()) {
                    fb = new FeedBack(rs.getInt(3), rs.getInt(4), rs.getString(2), rs.getString(1));
                    fbs.add(fb);
                }
            }catch(Exception e){
                 System.err.printf("Excepción: '%s'%n", e.getMessage() + " ()");
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage() + " ()");
        }
        return fbs;
    } 


    public static void main(String[] args) {
        FeedbackService fb = new FeedbackService();
        ArrayList<FeedBack> details = fb.getFeedBacks();
        JSONArray JA = new JSONArray();
        details.forEach((p) -> {
            JA.add(p.toJSON());
        });
        System.out.print(JA.toJSONString());
        int i = 0;
    }

}
