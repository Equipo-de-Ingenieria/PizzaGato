/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.json.simple.JSONObject;

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
    @SerializedName("status")
    private Status status;
    @SerializedName("date")
    private Timestamp date;
    @SerializedName("details")
    private ArrayList<Detail> details;

    public Invoice(int idInvoice, int idClient, Timestamp date) {
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public ArrayList<Detail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Detail> details) {
        this.details = details;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return toJSON().toString();
    }

    public JSONObject toJSON() {
        JSONObject r = new JSONObject();
        r.put("idInvoice", getIdInvoice());
        r.put("idClient", getIdClient());
        r.put("status", getStatus().getDescription());
        Timestamp date = getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        r.put("date", strDate);
        return r;
    }

}
