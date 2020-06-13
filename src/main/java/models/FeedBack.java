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
public class FeedBack implements Serializable {

    private static final long serialVersionUID = 1L;
    @SerializedName("idFeedback")
    private int idFeedback;
    @SerializedName("idClient")
    private int idClient;
    @SerializedName("description")
    private String description;

    public FeedBack(int idFeedback, int idClient, String description) {
        this.idFeedback = idFeedback;
        this.idClient = idClient;
        this.description = description;
    }

    public FeedBack() {
    }

    public int getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(int idFeedback) {
        this.idFeedback = idFeedback;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FeedBack{" + "idFeedback=" + idFeedback + ", idClient=" + idClient + ", description=" + description + '}';
    }
    
    

}
