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
import models.Detail;
import models.Invoice;
import models.Status;
import static services.ClientService.getConnection;

/**
 *
 * @author Erick
 */
public class InvoiceService {

    private static final String GET_INVOICES = "select inv.idInvoice, inv.idClient, inv.idStatus, inv.date, stat.description "
            + "from `eif209_2001_p02`.invoices inv, `eif209_2001_p02`.status stat "
            + "where idClient = ? and inv.idStatus = stat.idStatus;";
    private static final String CREATE_INVOICE = "insert into `eif209_2001_p02`.invoices (idclient, idStatus, date) "
            + "values (?, 1, CURRENT_TIMESTAMP());";
    private static final String GET_ALL_INVOICES = "select inv.idInvoice, inv.idClient, inv.idStatus, inv.date, stat.description from `eif209_2001_p02`.invoices inv, `eif209_2001_p02`.status stat where inv.idStatus = stat.idStatus;";
    
    private static void printAffectedRows(int counter) {
        if (counter > 0) {
            System.out.println("Se insertaron " + counter);
        } else {
            System.err.println("No se insertaron filas");
        }

    }

    public static ArrayList<Invoice> getInvoicesDB(int idClient) {
        ArrayList<Invoice> invoices = null;
        Invoice invoice = null;

        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(GET_INVOICES);) {
            stm.clearParameters();
            stm.setInt(1, idClient);

            try (ResultSet rs = stm.executeQuery()) {
                invoices = new ArrayList();
                while (rs.next()) {
                    invoice = new Invoice();
                    invoice.setIdInvoice(rs.getInt("idInvoice"));
                    invoice.setIdClient(rs.getInt("idClient"));
                    invoice.setStatus(new Status(rs.getInt("idStatus"), rs.getString("description")));
                    invoice.setDate(rs.getTimestamp("date"));
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

    public static ArrayList<Invoice> getInvoices(int idClient) {
        ArrayList<Invoice> invoices = getInvoicesDB(idClient);

        if (invoices != null && !invoices.isEmpty()) {
            invoices.forEach((invoice) -> {
                ArrayList<Detail> details = DetailService.getDetails(invoice.getIdInvoice());
                if (details != null && !details.isEmpty()) {
                    invoice.setDetails(details);
                }
            });
        }

        return invoices;
    }

    public static boolean createInvoice(int idClient) {
        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(CREATE_INVOICE);) {
            stm.clearParameters();
            stm.setInt(1, idClient);

            int counter = 0;

            counter = stm.executeUpdate();

            stm.close();
            connection.close();

            if (counter != -1) {
                printAffectedRows(counter);

                if (counter == 1) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;

        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage() + " createInvoice()");

        }
        return false;
    }
    
        public static ArrayList<Invoice> getAllInvoices(){
         ArrayList<Invoice> invoices = null;
        Invoice invoice = null;

        try (Connection connection = getConnection();
                PreparedStatement stm = connection.prepareStatement(GET_ALL_INVOICES);) {
            stm.clearParameters();

            try (ResultSet rs = stm.executeQuery()) {
                invoices = new ArrayList();
                while (rs.next()) {
                    invoice = new Invoice();
                    invoice.setIdInvoice(rs.getInt("idInvoice"));
                    invoice.setIdClient(rs.getInt("idClient"));
                    invoice.setStatus(new Status(rs.getInt("idStatus"), rs.getString("description")));
                    invoice.setDate(rs.getTimestamp("date"));
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
        invoices = setDetails(invoices);
        return invoices;
    }
    private static ArrayList<Invoice> setDetails(ArrayList<Invoice> inv){
        for(int i = 0; i < inv.size(); i++){
            inv.get(i).setDetails(DetailService.getDetails(inv.get(i).getIdInvoice()));
        }
        return inv;
    }
    public static void main(String[] args) {

        ArrayList<Invoice> inv = getAllInvoices();
        System.out.println(inv.toString());
        createInvoice(1);
    }

}
