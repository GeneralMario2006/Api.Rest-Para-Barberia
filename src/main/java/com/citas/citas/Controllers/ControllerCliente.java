package com.citas.citas.Controllers;

import com.citas.citas.Clases.AuthResponse;
import com.citas.citas.Clases.ClienteDTO;
import com.citas.citas.Clases.CreateClientDto;
import com.citas.citas.Entidades.Cliente;
import com.citas.citas.Repositorys.ClienteRepository;
import com.citas.citas.Security.ServiceConfig;
import com.citas.citas.Service.ClienteService;
import com.citas.citas.UserDetailsServiceImpl;
import com.citas.citas.token.JwtTokenProvider;
import com.citas.citas.validaciones.validarCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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
    validarCliente validacion;

    @Autowired
    ClienteService clienteService;

    @PostMapping("/crearUsuario")
    public ResponseEntity<?> AñadirCliente(@RequestBody CreateClientDto c) {
        try {
            validacion.validar(c);
            clienteService.CrearUsuario(c);
            return ResponseEntity.ok("Usuario creado. ");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este correo o telefono ya estan verificados." + e.getMessage());
        }
    }

    @PostMapping("/logearUsuario")
    public ResponseEntity<?> login(@RequestBody ClienteDTO c) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(c.getCorreo(), c.getContraseña())
            );

            UserDetails clientDetails = (UserDetails) auth.getPrincipal();

            boolean isClient = clientDetails.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE"));

            if (!isClient) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            String token = jwtTokenProvider.generateToken(clientDetails);
            return ResponseEntity.ok(new AuthResponse(token));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Credenciales incorrectas: " + e.getMessage());
        }
    }

}
