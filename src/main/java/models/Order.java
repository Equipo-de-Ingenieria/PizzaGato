/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author Erick
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("idStatus")
    private int idStatus;
    @SerializedName("idInvoice")
    private int idInvoice;

    public Order(int idStatus, int idInvoice) {
        this.idStatus = idStatus;
        this.idInvoice = idInvoice;
    }

    public Order() {
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    @Override
    public String toString() {
        return "Order{" + "idStatus=" + idStatus + ", idInvoice=" + idInvoice + '}';
    }

}
