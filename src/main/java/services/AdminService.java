/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import models.Admin;
import mysqldb.DataBase;

/**
 *
 * @author Erick
 */
public class AdminService {

    private static final String GET_ADMIN = "select idAdmin, idCard, name, lastname, password from `eif209_2001_p02`.admins adm  where adm.email = ? and adm.password = ?;";

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

    public static Admin getAdmin(String email, String password) {
        Admin admin = null;

        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(GET_ADMIN);) {
            stm.clearParameters();

            stm.setString(1, email);
            stm.setString(2, password);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    admin = new Admin();
                    admin.setId(rs.getInt("idAdmin"));
                    admin.setIdCard(rs.getString("idCard"));
                    admin.setName(rs.getString("name"));
                    admin.setLastName(rs.getString("lastname"));
                    admin.setPassword(rs.getString("password"));
                }
            }
            stm.close();
            connection.close();
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return admin;
    }
}
