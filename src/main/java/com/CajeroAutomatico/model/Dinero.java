package com.CajeroAutomatico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DINERO")
public class Dinero {

    @Id
    @Column(name="ID_DINERO")
    private Integer id;

    @Column(name="DENOMINACION")
    private Integer denominacion;

    @Column(name="CANTIDAD")
    private Integer cantidad;

    public Dinero(Integer id, Integer denominacion, Integer cantidad) {
        this.id = id;
        this.denominacion = denominacion;
        this.cantidad = cantidad;
    }

    public Dinero() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(Integer denominacion) {
        this.denominacion = denominacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
