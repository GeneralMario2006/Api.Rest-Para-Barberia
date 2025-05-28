package com.citas.citas.Controllers;

import com.citas.citas.Clases.AuthResponse;
import com.citas.citas.Clases.ClienteDTO;
import com.citas.citas.Entidades.Cliente;
import com.citas.citas.Repositorys.ClienteRepository;
import com.citas.citas.Security.ServiceConfig;
import com.citas.citas.Service.ClienteService;
import com.citas.citas.UserDetailsServiceImpl;
import com.citas.citas.token.JwtTokenProvider;
import com.citas.citas.validaciones.validarCliente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mr587
 */
@RestController
@RequestMapping("/usuarios")
public class ControllerCliente {
    @Autowired
    ClienteRepository CR;
    
    @Autowired
private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
private AuthenticationManager authenticationManager;
    
    @Autowired
private UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    validarCliente validacion;
    
    @Autowired 
    ClienteService clienteService;
    
    @PostMapping("/crearUsuario")
    public ResponseEntity<?>AñadirCliente(@RequestBody Cliente c) {
        try {
            validacion.validar(c);
            clienteService.CrearUsuario(c);
            return ResponseEntity.ok("Usuario creado. " + c.getId());
        }catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este correo o telefono ya estan verificados." + e.getMessage());
        }
    }

@PostMapping("/logearUsuario")
public ResponseEntity<?> login(@RequestBody ClienteDTO c) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            c.getCorreo(), c.getContraseña()
        )
    );

    String token = jwtTokenProvider.generateToken(c.getCorreo());

    return ResponseEntity.ok(new AuthResponse(token));
}

}
