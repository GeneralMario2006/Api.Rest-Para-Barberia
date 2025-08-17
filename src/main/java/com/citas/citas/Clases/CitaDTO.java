/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.Clases;

import java.time.LocalDateTime;
import java.util.Date;



/**
 *
 * @author mr587
 */
public class CitaDTO {

    public LocalDateTime fecha;
    public String servicio;
    public String estado;
    public String correo;  // este campo viene del usuario

    public CitaDTO(LocalDateTime fecha, String servicio, String estado, String correo) {
        this.fecha = fecha;
        this.servicio = servicio;
        this.estado = estado;
        this.correo = correo;
    }

    public CitaDTO() {
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    

}
