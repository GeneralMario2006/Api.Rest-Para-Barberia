package com.citas.citas;

import com.citas.citas.Entidades.Barbería;
import com.citas.citas.Entidades.Cliente;
import com.citas.citas.Repositorys.BarberiaRepository;
import com.citas.citas.Repositorys.ClienteRepository;
import com.citas.citas.token.BarberDetailsImpl;
import com.citas.citas.token.UserDetailsImpl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author mr587
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BarberiaRepository barberRepository;
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Optional<Cliente> client= clienteRepository.findByCorreo(correo);
        if (client.isPresent()) {
            return new UserDetailsImpl(client.get());
        }
        
        Optional<Barbería> barbershop= barberRepository.findByNombre(correo);
        
        if (barbershop.isPresent()) {
            return new BarberDetailsImpl(barbershop.get());
        }
        
        throw new UsernameNotFoundException("Usuario "+correo + " no encontrado.");
    }
}

