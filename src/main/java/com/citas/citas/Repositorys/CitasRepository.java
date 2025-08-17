/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.Repositorys;

import com.citas.citas.Clases.CitaResponseDTO;
import com.citas.citas.Entidades.Cita;
import com.citas.citas.Entidades.Cliente;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mr587
 */
@Repository
public interface CitasRepository extends JpaRepository<Cita, Long> {

    @Query("SELECT new com.citas.citas.Clases.CitaResponseDTO(c.cliente.correo, c.servicio, c.estado, c.fecha) FROM Cita c WHERE c.estado LIKE CONCAT ('%', :estado, '%') AND c.barbero.nombre LIKE CONCAT('%', :nombre, '%')")
    List<CitaResponseDTO> buscarPorEstado(@Param("estado") String estado, @Param("nombre") String nombre);

    @Query("SELECT new com.citas.citas.Clases.CitaResponseDTO(c.cliente.correo, c.servicio, c.estado, c.fecha) FROM Cita AS c WHERE c.fecha= CURRENT_DATE AND c.barbero.nombre LIKE CONCAT('%', :nombre, '%')")
    List<CitaResponseDTO> MostrarCitasDelDia(@Param("nombre") String nombre);
    
    @Query("SELECT COUNT(c) > 0 FROM Cita c WHERE c.barbero.nombre = :barberoName AND c.fecha = :fecha")
    boolean isDisponible(@Param("barberoName") String nombreBarbero,
            @Param("fecha") LocalDateTime fecha);
}
