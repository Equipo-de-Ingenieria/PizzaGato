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
    @SerializedName("description")
    private String description;

    public Size(int idSize, String size, String description) {
        this.idSize = idSize;
        this.size = size;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Size{" + "idSize=" + idSize + ", size=" + size + ", description=" + description + '}';
    }
   

}
