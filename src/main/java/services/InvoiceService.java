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
import java.util.ArrayList;
import models.Invoice;
import static services.ClientService.getConnection;

/**
 *
 * @author Erick
 */
public class InvoiceService {

    private static final String GET_INVOICES = "select inv.idInvoice, inv.idClient, inv.date "
            + "from `eif209_2001_p02`.invoices inv "
            + "where idClient = ?;";
    private static final String CREATE_INVOICE = "insert into `eif209_2001_p02`.invoices (idclient, date) "
            + "values (?, ?);";

    private static void printAffectedRows(int counter) {
        if (counter > 0) {
            System.out.println("Se insertaron " + counter);
        } else {
            System.err.println("No se insertaron filas");
        }

    }

    public static ArrayList<Invoice> getInvoicesDB() {
        ArrayList<Invoice> invoices = null;
        Invoice invoice = null;

        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(GET_INVOICES);) {
            stm.clearParameters();

            try (ResultSet rs = stm.executeQuery()) {
                invoices = new ArrayList();
                while (rs.next()) {
                    invoice = new Invoice();
                    invoice.setIdInvoice(rs.getInt("idInvoice"));
                    invoice.setIdClient(rs.getInt("idClient"));
                    invoice.setDate(rs.getDate("date"));
                    invoices.add(invoice);
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

        if (invoices == null) {
            System.err.println("\n\nLa factura esta nula\n\n");
        }

        return invoices;
    }

}
