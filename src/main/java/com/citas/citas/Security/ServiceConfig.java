/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citas.citas.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author mr587
 */
@Service
public class ServiceConfig {
    public PasswordEncoder PE;
    
    public ServiceConfig(PasswordEncoder passwordEncoder) {
        this.PE = passwordEncoder;
    }
    public String encryptPassword(String rawPassword) {
        return PE.encode(rawPassword);
    }
    
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
    return PE.matches(rawPassword, encodedPassword);
}

}
