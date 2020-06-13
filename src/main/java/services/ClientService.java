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
import models.Client;
import mysqldb.DataBase;

/**
 *
 * @author Erick
 */
public class ClientService {

    private static final String CLIENT_VERIFICATION = "select exists(select * from `eif209_2001_p02`.clients where email = ? and password = ?);";

    private static final String GET_CLIENT = "select cli.idClient, cli.idCard, cli.name, cli.lastname, cli.address, cli.phone, cli.email, cli.password "
            + "from `eif209_2001_p02`.clients cli "
            + "where cli.email = ? and cli.password = ?;";

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

    static void printAffectedRows(int counter) {
        if (counter > 0) {
            System.out.println("Se insertaron " + counter);
        } else {
            System.err.println("No se insertaron filas");
        }

    }

    public static Client getClient(String email, String password) {
        Client client = null;
        
        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(GET_CLIENT);) {
            stm.clearParameters();

            stm.setString(1, email);
            stm.setString(2, password);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    client = new Client();
                    client.setIdClient(rs.getInt("idClient"));
                    client.setIdCard("idCard");
                    client.setName("name");
                    client.setLastName("lastname");
                    client.setAddress("address");
                    client.setPhone("phone");
                    client.setEmail(rs.getString("email"));
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
        return client;
    }
    
    
    public static void main(String[] args) {
        Client client;
        client = getClient("nacho@gmail.com", "qwer");
    }

}
