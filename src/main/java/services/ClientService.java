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
    private static final String INSERT_CLIENT = "insert into Clients "
            + "(idCard, name, lastname, address, phone, email, password) "
            + "values (?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_CLIENT = "update `eif209_2001_p02`.Clients set idCard= ?, name = ?, lastname = ?, address = ?, phone = ?, email = ?, password = ? "
            + "where idClient = ?";

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
                    client.setIdCard(rs.getString("idCard"));
                    client.setName(rs.getString("name"));
                    client.setLastName(rs.getString("lastname"));
                    client.setAddress(rs.getString("address"));
                    client.setPhone(rs.getString("phone"));
                    client.setEmail(rs.getString("email"));
                    client.setPassword(password);
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

    public boolean insertClient(Client client) {
        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(INSERT_CLIENT);) {
            stm.clearParameters();

            //stm.setInt(1, client.getIdClient());
            stm.setString(1, client.getIdCard());
            stm.setString(2, client.getName());
            stm.setString(3, client.getLastName());
            stm.setString(4, client.getAddress());
            stm.setString(5, client.getPhone());
            stm.setString(6, client.getEmail());
            stm.setString(7, client.getPassword());
            /* Los inserts se hacen con execute vs execute query*/
            if (stm.executeUpdate() != -1) {
                return true;
            }

        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());

        }
        return false;

    }

    public static void main(String[] args) {
        Client client;
        client = getClient("nacho@gmail.com", "qwer");
    }

    public static boolean updateClient(Client client) {
        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(UPDATE_CLIENT)) {
            stm.clearParameters();

            stm.setString(1, client.getIdCard());
            stm.setString(2, client.getName());
            stm.setString(3, client.getLastName());
            stm.setString(4, client.getAddress());
            stm.setString(5, client.getPhone());
            stm.setString(6, client.getEmail());
            stm.setString(7, client.getPassword());
            stm.setInt(8, client.getIdClient());

            if (stm.executeUpdate() == 1) {
                return true;
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());

        }
        return false;

    }
}
