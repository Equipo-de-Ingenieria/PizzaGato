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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import models.Detail;
import models.Product;
import mysqldb.DataBase;

/**
 *
 * @author Erick
 */
public class DetailService {

    private static final String CREATE_DETAILS = "insert into `eif209_2001_p02`.details (idInvoice, idProduct, stock) "
            + "values (? ,? ,?)";

    private static final String GET_DETAILS = "select det.idInvoice, det.idProduct, det.stock  "
            + "from `eif209_2001_p02`.details det "
            + "where det.idInvoice = ?;";

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

    public static void checkUpdateCounts(int[] updateCounts) {
        for (int i = 0; i < updateCounts.length; i++) {
            if (updateCounts[i] >= 0) {
                System.out.println("OK; updateCount=" + updateCounts[i]);
            } else if (updateCounts[i] == Statement.SUCCESS_NO_INFO) {
                System.out.println("OK; updateCount=Statement.SUCCESS_NO_INFO");
            } else if (updateCounts[i] == Statement.EXECUTE_FAILED) {
                System.out.println("Failure; updateCount=Statement.EXECUTE_FAILED");
            }
        }

    }


    public static boolean createDetails(ArrayList<Detail> details) {
        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(CREATE_DETAILS);) {
            stm.clearParameters();

            for (Detail detail : details) {
                stm.setInt(1, detail.getIdInvoice());
                stm.setInt(2, detail.getIdProduct());
                stm.setInt(3, detail.getStock());
                stm.addBatch();
            }

            int[] updateCounts = stm.executeBatch();
            checkUpdateCounts(updateCounts);

            if (updateCounts.length > 0) {
                return true;
            }

            stm.close();
            connection.close();

        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage() + " createDetails()");

        }
        return false;

    }

    public static ArrayList<Detail> getDetailsDB(int idInvoice) {
        ArrayList<Detail> details = null;
        Detail detail = null;
        Product product = null;

        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(GET_DETAILS);) {
            stm.clearParameters();
            stm.setInt(1, idInvoice);

            try (ResultSet rs = stm.executeQuery()) {
                details = new ArrayList();
                while (rs.next()) {
                    detail = new Detail();
                    detail.setIdInvoice(rs.getInt(1));
                    detail.setIdProduct(rs.getInt(2));
                    detail.setStock(rs.getInt(3));

                    details.add(detail);
                }

            }

            stm.close();
            connection.close();

        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage() + " ()");
        }

        if (details == null) {
            System.err.println("\n\nLa factura esta nula\n\n");
        }

        return details;
    }

    public static ArrayList<Detail> getDetails(int idInvoice) {
        ArrayList<Detail> details = getDetailsDB(idInvoice);

        if (details != null && !details.isEmpty()) {
            details.forEach((detail) -> {
                detail.setProduct(ProductService.getProduct(detail.getIdProduct()));
            });
        }

        return details;

    }

    public static void main(String[] args) {
        ArrayList<Detail> details = getDetails(1);
    }

}
