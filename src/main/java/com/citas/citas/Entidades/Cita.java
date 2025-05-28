/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;

/**
 *
 * @author mr587
 */
@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    Date fecha;
    
    String servicio;
    
    String estado;
    
    @ManyToOne
    @JoinColumn(name= "id_cliente", nullable=true)
    Cliente cliente;
    
@ManyToOne
    @JoinColumn(name = "id_barbero", nullable= true)
    private Barbería barbero;

    public Cita() {
    }

    public Cita(Date fecha, String servicio, String estado, Cliente cliente) {
        this.fecha = fecha;
        this.servicio = servicio;
        this.estado = estado;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Barbería getBarbero() {
        return barbero;
    }

    public void setBarbero(Barbería barbero) {
        this.barbero = barbero;
    }


}
