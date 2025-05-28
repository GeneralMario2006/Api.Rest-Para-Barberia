/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.validaciones;

import com.citas.citas.Service.CitaService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author mr587
 */
@Component
public class validarCitas {
    
    
    public void ValidarCita(Date fecha) {
        LocalDate localDate = fecha.toInstant()
                                  .atZone(ZoneId.systemDefault())
                                  .toLocalDate();
        
        if (localDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha inválida.");
            }
    }
}
