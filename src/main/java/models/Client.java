/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.json.simple.JSONObject;

/**
 *
 * @author Erick
 */
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Expose
    @SerializedName("idClient")
    private int idClient;
    @Expose
    @SerializedName("idCard")
    private String idCard;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("lastName")
    private String lastName;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public Client(int idClient, String idCard, String name, String lastName, String address, String phone, String email, String password) {
        this.idClient = idClient;
        this.idCard = idCard;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Client(String idCard, String name, String lastName, String address, String phone, String email, String password) {

        this.idCard = idCard;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Client() {
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClient(Client client) {
        this.idClient = client.getIdClient();
        this.idCard = client.getIdCard();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.address = client.getAddress();
        this.phone = client.getPhone();
        this.email = client.getEmail();
        this.password = client.getPassword();
    }

    @Override
    public String toString() {
        return toJSON().toString();
    }

    public JSONObject toJSON() {
        JSONObject r = new JSONObject();
        r.put("idClient", this.idClient);
        r.put("idCard", this.idCard);
        r.put("name", this.name);
        r.put("lastName", this.lastName);
        r.put("phone", this.phone);
        r.put("email", this.email);
        r.put("password", this.password);
        
        return r;
    }
}
