/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.simple.JSONObject;


public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("idProduct")
    private int idProduct;
    @SerializedName("code")
    private String code;
    @SerializedName("size")
    private String size;
    @SerializedName("description")
    private String description;
    @SerializedName("price")
    private double price;
    @SerializedName("ingredients")
    private ArrayList<Ingredient> ingredients;

    public Product(int idProduct, String code, String size, String description, double price) {
        this.idProduct = idProduct;
        this.code = code;
        this.size = size;
        this.description = description;
        this.price = price;
    }

    public Product(String code, String size, String description, double price) {
        this.code = code;
        this.size = size;
        this.description = description;
        this.price = price;
    }
    
    

    public Product() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

  @Override
    public String toString() {
        return toJSON().toString();
    }
   // int idProduct, String code, String size, String description, double price
     public JSONObject toJSON() {
        JSONObject r = new JSONObject();
        r.put("idProduct", getIdProduct());
        r.put("code", getCode());
        r.put("size", getSize());
        r.put("description", getDescription());
        r.put("price", getPrice());
        return r;
    }
}
