/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Erick
 */
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("idProduct")
    private int idProduct;
    @SerializedName("idSize")
    private int idSize;
    @SerializedName("code")
    private String code;
    @SerializedName("description")
    private String description;
    @SerializedName("size")
    private Size size;
    @SerializedName("ingredients")
    private ArrayList<Ingredient> ingredients;

    public Product(int idProduct, int idSize, String code, String description) {
        this.idProduct = idProduct;
        this.idSize = idSize;
        this.code = code;
        this.description = description;
    }

    public Product() {
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
    
    

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Product{" + "idProduct=" + idProduct + ", idSize=" + idSize + ", code=" + code + ", description=" + description + ", ingredients=" + ingredients + '}';
    }

}
