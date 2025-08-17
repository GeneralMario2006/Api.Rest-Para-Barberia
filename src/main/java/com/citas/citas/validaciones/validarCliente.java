/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.validaciones;

import com.citas.citas.Clases.CreateClientDto;
import com.citas.citas.Entidades.Cliente;
import com.citas.citas.Repositorys.ClienteRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author mr587
 */
@Component
public class validarCliente {
    @Autowired
    ClienteRepository clienteRepository;
    
    public void validar(CreateClientDto cliente) {
        Optional<Cliente> correo= clienteRepository.findByCorreo(cliente.getCorreo());
        Optional<Cliente> telefono= clienteRepository.findByTelefono(cliente.getTelefono());
        
        if (correo.isEmpty() && telefono.isEmpty()) {
            //el correo no existe, por lo q sigue su curso.
        }
        else {
            throw new IllegalArgumentException("Este correo o teléfono ya están registrados.");
        }
    }
}
