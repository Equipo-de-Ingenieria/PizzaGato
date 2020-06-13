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
public class Ingredient implements Serializable{

    private static final long serialVersionUID = 1L;
    @SerializedName("idIngredient")
    private int idIngredient;
    @SerializedName("code")
    private String code;
    @SerializedName("name")
    private String name;

    public Ingredient(int idIngredient, String code, String name) {
        this.idIngredient = idIngredient;
        this.code = code;
        this.name = name;
    }

    public Ingredient() {
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "idIngredient=" + idIngredient + ", code=" + code + ", name=" + name + '}';
    }
    
    

}
