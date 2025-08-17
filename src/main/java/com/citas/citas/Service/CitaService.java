/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.Service;

import com.citas.citas.Clases.CitaDTO;
import com.citas.citas.Clases.CitaResponseDTO;
import com.citas.citas.Entidades.Barbería;
import com.citas.citas.Entidades.Cita;
import com.citas.citas.Entidades.Cliente;
import com.citas.citas.Repositorys.BarberiaRepository;
import com.citas.citas.Repositorys.CitasRepository;
import com.citas.citas.Repositorys.ClienteRepository;
import com.citas.citas.validaciones.validarCitas;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CitaService {

    @Autowired
    ClienteRepository CR;

    @Autowired
    CitasRepository CitaR;
    
    @Autowired
    BarberiaRepository barberiaRepo;

    public List<CitaResponseDTO> VerCitasPendientes(String nombre) {
        return CitaR.buscarPorEstado("Pendiente", nombre);
    }

    public void VerificarUsuarioYcita(CitaDTO citaDTO) {
        Optional<Cliente> cliente = CR.findByCorreo(citaDTO.getCorreo());
        if (!cliente.isEmpty()) {

            Cita cita = new Cita();
            cita.setFecha(citaDTO.getFecha());
            cita.setEstado(citaDTO.getEstado());
            cita.setCliente(cliente.get());
            cita.setServicio(citaDTO.getServicio());
            CitaR.save(cita);
        } else {
            throw new IllegalArgumentException("El cliente no existe.");
        }
    }

    public List<CitaResponseDTO> CitasDelDia(String nombre) {
        return CitaR.MostrarCitasDelDia(nombre);
    }
    
    public String ValidarDisponibilidadYAceptar(Long idCita, String nombreBarbero) {
            Cita cita= CitaR.findById(idCita).get();
            Barbería barber= barberiaRepo.findByNombre(nombreBarbero).get();
            LocalDateTime horaYfecha= cita.getFecha();
       
        boolean isOcupado= CitaR.isDisponible(nombreBarbero, horaYfecha);
        
        if (isOcupado) {
            return "Esta ocupado";
        }
        else {
            cita.setEstado("Aceptada");
            cita.setBarbero(barber);
            CitaR.save(cita);
            return "Cita aceptada";
        }
    }
}
