package com.vecks.timo.models;

import java.util.Date;

/**
 * Created by carlos on 10/12/17.
 */

public class Tarea {
    private int id;
    private String nombreTarea;
    private Date fechaEntrega;
    private int idAsignatura;
    private int idCategoria;
    private int estado; // 1: Hecho, 0: No hecho
    private int importancia; // 1,2,3 1 la menor, 3 la mayor
    private String detalle; // Como eran antes los tweets de 140 caracteres maximo

    public Tarea() {
    }

    public Tarea(int id, String nombreTarea, Date fechaEntrega, int idAsignatura, int idCategoria, int estado, int importancia, String detalle) {
        this.id = id;
        this.nombreTarea = nombreTarea;
        this.fechaEntrega = fechaEntrega;
        this.idAsignatura = idAsignatura;
        this.idCategoria = idCategoria;
        this.estado = estado;
        this.importancia = importancia;
        this.detalle = detalle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getImportancia() {
        return importancia;
    }

    public void setImportancia(int importancia) {
        this.importancia = importancia;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }


}
