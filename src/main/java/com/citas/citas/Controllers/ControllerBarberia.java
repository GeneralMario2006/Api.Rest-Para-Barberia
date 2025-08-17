/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.Controllers;

import com.citas.citas.Clases.BarberoLoginRequest;
import com.citas.citas.Clases.CitaResponseDTO;
import com.citas.citas.Clases.CreateBarberDTO;
import com.citas.citas.Entidades.Cita;
import com.citas.citas.Service.BarberService;
import com.citas.citas.Service.CitaService;
import com.citas.citas.token.JwtTokenProvider;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Barberia")
public class ControllerBarberia {

    @Autowired
    BarberService serviceBarberShop;

    @Autowired
    CitaService citaService;

    @Autowired
    JwtTokenProvider jwt;

    @Autowired
    AuthenticationManager authManager;


    @PostMapping("/crearBarbero")
    public ResponseEntity<?> Login(@RequestBody CreateBarberDTO request) {
        try {
            serviceBarberShop.CrearUsuario(request);
            return ResponseEntity.ok("CREADO");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/logearBarbero")
    public ResponseEntity<?> Login(@RequestBody BarberoLoginRequest request) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getNombre(),
                            request.getContraseña())
            );

            UserDetails barberDetails = (UserDetails) auth.getPrincipal();

            boolean isAuthenticate = barberDetails.getAuthorities().stream().anyMatch(rol -> rol.getAuthority().equals("ROLE_BARBERO"));

            if (!isAuthenticate) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            String token = jwt.generateToken(barberDetails);

            return ResponseEntity.ok("token: " + token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Credenciales incorrectas. ");
        }
    }

    
    @GetMapping("/verCitasPendientes/{nombre}")
    public ResponseEntity<?> verCitasPendientes(@PathVariable("nombre") String nombre) {
        List<CitaResponseDTO> buscar = citaService.VerCitasPendientes(nombre);
        if (buscar.isEmpty()) {
            ResponseEntity.ok("No tienes citas pendientes.");
        }
        return ResponseEntity.ok(buscar);
    }

    @GetMapping("/VerCitasDelDia/{nombre}")
    public ResponseEntity<?> VerCitasDelDia(@PathVariable("nombre") String nombre) {
        List<CitaResponseDTO> citasDelDia = citaService.CitasDelDia(nombre);
        if (citasDelDia.isEmpty()) {
            ResponseEntity.ok("No tienes citas para este día");
        }
        return ResponseEntity.ok(citasDelDia);
    }
}
