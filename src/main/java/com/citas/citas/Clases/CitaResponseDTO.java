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
public record CitaResponseDTO(String correo, String servicio, String estado, LocalDateTime fecha) {
    
}
