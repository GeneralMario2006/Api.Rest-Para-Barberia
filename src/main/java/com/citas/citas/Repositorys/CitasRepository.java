/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.Repositorys;

import com.citas.citas.Entidades.Cita;
import com.citas.citas.Entidades.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mr587
 */
@Repository
public interface CitasRepository extends JpaRepository<Cita, Long>{
    @Query("SELECT c FROM Cita c WHERE c.estado LIKE %:estado%")
    List<Cita> buscarPorEstado(@Param("estado") String estado); 
}
