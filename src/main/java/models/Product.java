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
<<<<<<< HEAD
    @SerializedName("size")
    private Size size;
=======
>>>>>>> eddc1175beb56f20e4cf1a1b5ba9cf1f35b7b75d
    @SerializedName("ingredients")
    private ArrayList<Ingredient> ingredients;
    @SerializedName("Size")
    private String size;
    

<<<<<<< HEAD
    public Product(int idProduct, int idSize, String code, String description) {
=======
    public Product(int idProduct, String code, String description, String size) {
>>>>>>> eddc1175beb56f20e4cf1a1b5ba9cf1f35b7b75d
        this.idProduct = idProduct;
        this.idSize = idSize;
        this.code = code;
        this.description = description;
        this.size = size;
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

<<<<<<< HEAD
=======

>>>>>>> eddc1175beb56f20e4cf1a1b5ba9cf1f35b7b75d
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
<<<<<<< HEAD
        return "Product{" + "idProduct=" + idProduct + ", idSize=" + idSize + ", code=" + code + ", description=" + description + ", ingredients=" + ingredients + '}';
=======
        return "Product{" + "idProduct=" + idProduct + ", code=" + code + ", description=" + description + ", ingredients=" + ingredients + '}';
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
>>>>>>> eddc1175beb56f20e4cf1a1b5ba9cf1f35b7b75d
    }

}
