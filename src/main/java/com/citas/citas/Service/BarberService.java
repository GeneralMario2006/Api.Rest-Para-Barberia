/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.Service;

import com.citas.citas.Clases.CreateBarberDTO;
import com.citas.citas.Entidades.Barbería;
import com.citas.citas.Entidades.Cita;
import com.citas.citas.Repositorys.BarberiaRepository;
import com.citas.citas.Security.ServiceConfig;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarberService {

    @Autowired
    BarberiaRepository barberRepository;

    @Autowired
    ServiceConfig SC;

    public void CrearUsuario(CreateBarberDTO barber) {
        Barbería crear = new Barbería();
        crear.setNombre(barber.getNombre());
        crear.setContraseña(SC.encryptPassword(barber.getContraseña()));
        crear.setRol("BARBERO");

        barberRepository.save(crear);
    }

}
