/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.Service;

import com.citas.citas.Clases.CitaDTO;
import com.citas.citas.Entidades.Cita;
import com.citas.citas.Entidades.Cliente;
import com.citas.citas.Repositorys.CitasRepository;
import com.citas.citas.Repositorys.ClienteRepository;
import com.citas.citas.validaciones.validarCitas;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author mr587
 */
@Service
public class CitaService {
    
    @Autowired
    ClienteRepository CR;
    
    @Autowired
    CitasRepository CitaR;
    
    public void VerificarUsuarioYcita(CitaDTO citaDTO) {
        Optional<Cliente> cliente= CR.findByCorreo(citaDTO.getCorreo());
        if (!cliente.isEmpty()){
            
            Cita cita= new Cita();
            cita.setFecha(citaDTO.getFecha());
            cita.setEstado(citaDTO.getEstado());
            cita.setCliente(cliente.get());
            cita.setServicio(citaDTO.getServicio());
            CitaR.save(cita);
            }
        else {
            throw new IllegalArgumentException("El cliente no existe.");
        }
    }
}
