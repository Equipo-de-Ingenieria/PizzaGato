/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Erick
 */
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("idInvoice")
    private int idInvoice;
    @SerializedName("idClient")
    private int idClient;
    @SerializedName("date")
    private Date date;
    @SerializedName("details")
    private ArrayList<Detail> details;

    public Invoice(int idInvoice, int idClient, Date date) {
        this.idInvoice = idInvoice;
        this.idClient = idClient;
        this.date = date;
    }

    public Invoice() {
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Detail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Detail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Invoice{" + "idInvoice=" + idInvoice + ", idClient=" + idClient + ", date=" + date + ", details=" + details + '}';
    }

}
