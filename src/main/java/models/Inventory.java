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
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("idProduct")
    private int idProduct;
    @SerializedName("idSize")
    private int idSize;
    @SerializedName("price")
    private double price;
    @SerializedName("sizes")
    private ArrayList<Size> sizes;

    public Inventory(int idProduct, int idSize, double price) {
        this.idProduct = idProduct;
        this.idSize = idSize;
        this.price = price;
    }

    public Inventory() {
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Size> getSizes() {
        return sizes;
    }

    public void setSizes(ArrayList<Size> sizes) {
        this.sizes = sizes;
    }

    @Override
    public String toString() {
        return "Inventory{" + "idProduct=" + idProduct + ", idSize=" + idSize + ", price=" + price + ", sizes=" + sizes + '}';
    }

}
