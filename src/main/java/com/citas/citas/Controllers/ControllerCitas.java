/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.Controllers;

import com.citas.citas.Clases.CitaDTO;
import com.citas.citas.Clases.EmailService;
import com.citas.citas.Entidades.Cita;
import com.citas.citas.Entidades.Cliente;
import com.citas.citas.Repositorys.CitasRepository;
import com.citas.citas.Repositorys.ClienteRepository;
import com.citas.citas.Service.CitaService;
import com.citas.citas.validaciones.validarCitas;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mr587
 */
@RestController
@RequestMapping("/Citas")
public class ControllerCitas {
    
    @Autowired
    public CitaService CS;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
     validarCitas validar;
    
   
@PostMapping("/añadirCita")
public ResponseEntity<?> crearCita(@RequestBody CitaDTO data) {
    try {
        validar.ValidarCita(data.getFecha());
    CS.VerificarUsuarioYcita(data);
    emailService.enviarCorreo(data.getCorreo(), "Notificación de cita", "Querido usuario, hemos recibido su cita correctamente. \n"
            + "Le invitamos a estar pendiente, ya que nuestro personal se contactará con usted.");
    return ResponseEntity.status(HttpStatus.CREATED).body("Cita agendada correctamente");
}catch(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR: "+ e);
        }
}


}
