package com.vecks.timo.models;

/**
 * Created by carlos on 10/12/17.
 */

public class Materia {
    private int id;
    private String nombre;

    public Materia() {
    }

    public Materia(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
