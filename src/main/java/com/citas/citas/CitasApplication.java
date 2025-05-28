package com.citas.citas;
import com.citas.citas.Controllers.ControllerCliente;
import com.citas.citas.Clases.CitaDTO;
import com.citas.citas.Entidades.Cliente;
import com.citas.citas.Entidades.Cita;
import com.citas.citas.Entidades.Barbería;
import com.citas.citas.Repositorys.ClienteRepository;
import com.citas.citas.Repositorys.CitasRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitasApplication.class, args);
	}

}
