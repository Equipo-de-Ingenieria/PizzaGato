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
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @SerializedName("idProduct")
    private int idProduct;
    @SerializedName("idIngredient")
    private int idIngredient;

    public Recipe(int idProduct, int idIngredient) {
        this.idProduct = idProduct;
        this.idIngredient = idIngredient;
    }

    public Recipe() {
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    @Override
    public String toString() {
        return "Recipe{" + "idProduct=" + idProduct + ", idIngredient=" + idIngredient + '}';
    }

}
