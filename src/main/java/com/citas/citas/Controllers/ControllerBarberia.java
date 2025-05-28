/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.Controllers;


import com.citas.citas.Entidades.Barbería;
import com.citas.citas.Entidades.Cita;
import com.citas.citas.Repositorys.BarberiaRepository;
import com.citas.citas.Repositorys.CitasRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mr587
 */
@RestController
@RequestMapping("/Barberia")
public class ControllerBarberia {
    @Autowired
    BarberiaRepository BR;
    
    @Autowired
    CitasRepository CR;

    
@GetMapping("/ver")
public ResponseEntity<?>verCitasPendientes(){
    List<Cita> buscar= CR.buscarPorEstado("Pendiente");
    return ResponseEntity.ok(buscar);
}

}
