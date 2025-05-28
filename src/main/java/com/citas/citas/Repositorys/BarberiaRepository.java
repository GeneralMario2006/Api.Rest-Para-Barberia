/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.Repositorys;

import com.citas.citas.Entidades.Barbería;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mr587
 */
public interface BarberiaRepository extends JpaRepository<Barbería, Long>{
    List<Barbería> findByNombre(String nombre);
}
