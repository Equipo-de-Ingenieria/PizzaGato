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
public class Detail implements Serializable{

    private static final long serialVersionUID = 1L;
    @SerializedName("idInvoice")
    private int idInvoice;
    @SerializedName("idProduct")
    private int idProduct;
    @SerializedName("stock")
    private int stock;
    @SerializedName("products")
    private ArrayList<Product> products;

    public Detail(int idInvoice, int idProduct, int stock) {
        this.idInvoice = idInvoice;
        this.idProduct = idProduct;
        this.stock = stock;
    }

    public Detail() {
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Detail{" + "idInvoice=" + idInvoice + ", idProduct=" + idProduct + ", stock=" + stock + ", products=" + products + '}';
    }
    
    

  

}
