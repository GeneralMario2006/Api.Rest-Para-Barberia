/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.token;

import com.citas.citas.Entidades.Cliente;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author mr587
 */
public class ClienteUserDetails implements UserDetails {

    private final Cliente cliente;

    public ClienteUserDetails(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // Sin roles por ahora
    }

    @Override
    public String getPassword() {
        return cliente.getContraseña();// Asegúrate que este sea el nombre del campo
    }

    @Override
    public String getUsername() {
        return cliente.getCorreo(); // O el campo que uses para identificar
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
