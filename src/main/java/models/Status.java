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
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;
    @SerializedName("idStatus")
    private int idStatus;
    @SerializedName("description")
    private String description;

    public Status(int idStatus, String description) {
        this.idStatus = idStatus;
        this.description = description;
    }

    public Status() {
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Status{" + "idStatus=" + idStatus + ", description=" + description + '}';
    }

}
