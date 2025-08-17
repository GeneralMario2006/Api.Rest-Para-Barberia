/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.token;

import com.citas.citas.Entidades.Barbería;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author mr587
 */
public class BarberDetailsImpl implements UserDetails{
    private Barbería barber;
    
    public BarberDetailsImpl(Barbería barber) {
        this.barber= barber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
          return List.of(new SimpleGrantedAuthority("ROLE_"+ barber.getRol()));
    }

    @Override
    public String getPassword() {
        return barber.getContraseña();
    }

    @Override
    public String getUsername() {
        return barber.getNombre();
    }

    @Override
    public boolean isAccountNonExpired() {
return true;
    }

    @Override
    public boolean isAccountNonLocked() {
return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
return true;
    }

    @Override
    public boolean isEnabled() {
return true;
    }
    
}
