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
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("idAdmin")
    private int idAdmin;
    @SerializedName("idCard")
    private String idCard;
    @SerializedName("name")
    private String name;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("password")
    private String password;

    public Admin(int id, String idCard, String name, String lastName, String password) {
        this.idAdmin = id;
        this.idCard = idCard;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
    }

    public Admin() {
    }

    public int getId() {
        return idAdmin;
    }

    public void setId(int id) {
        this.idAdmin = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" + "idAdmin=" + idAdmin + ", idCard=" + idCard + ", name=" + name + ", lastName=" + lastName + ", password=" + password + '}';
    }

    public void setAdmin(Admin aux) {
        this.idAdmin = aux.getId();
        this.idCard = aux.getIdCard();
        this.name = aux.getName();
        this.lastName = aux.getLastName();
        this.password = aux.getPassword();
    }

}
