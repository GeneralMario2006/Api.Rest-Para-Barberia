package com.citas.citas;

import com.citas.citas.Entidades.Cliente;
import com.citas.citas.Repositorys.ClienteRepository;
import com.citas.citas.token.ClienteUserDetails;
import java.util.ArrayList;
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

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByCorreo(correo)
                .stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado con correo: " + correo));

        return new ClienteUserDetails(cliente);
    }
}

