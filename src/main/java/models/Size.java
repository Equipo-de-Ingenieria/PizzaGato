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
public class Size implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @SerializedName("idSize")
    private int idSize;
    @SerializedName("size")
    private String size;

    public Size(int idSize, String size) {
        this.idSize = idSize;
        this.size = size;
    }

    public Size() {
    }

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Size{" + "idSize=" + idSize + ", size=" + size + '}';
    }

}
