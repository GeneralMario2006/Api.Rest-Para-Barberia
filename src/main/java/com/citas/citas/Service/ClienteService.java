/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.Service;

import com.citas.citas.Clases.CreateClientDto;
import com.citas.citas.Entidades.Cliente;
import com.citas.citas.Repositorys.ClienteRepository;
import com.citas.citas.Security.ServiceConfig;
import com.citas.citas.validaciones.validarCliente;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mr587
 */
@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    
    @Autowired
    ServiceConfig SC;
    
    public void CrearUsuario(CreateClientDto cliente) {
        Cliente crear= new Cliente();
        crear.setNombre(cliente.getNombre());
        crear.setCorreo(cliente.getCorreo());
        crear.setTelefono(cliente.getTelefono());
        crear.setRol("CLIENTE");
        crear.setContraseña(SC.encryptPassword(cliente.getContraseña()));
        
        clienteRepository.save(crear);
    }
    
}
