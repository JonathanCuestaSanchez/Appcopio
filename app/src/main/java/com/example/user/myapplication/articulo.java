package com.example.user.myapplication;

/**
 * Created by User on 06/11/2018.
 */

public class articulo {
    String claseid,producto,lugar;


    public articulo(String claseid, String producto, String lugar) {
        this.claseid = claseid;
        this.producto = producto;
        this.lugar = lugar;
    }

    public String getLugar() {
        return lugar;
    }

    public String getClaseid() {
        return claseid;
    }

    public String getProducto() {
        return producto;
    }
}
