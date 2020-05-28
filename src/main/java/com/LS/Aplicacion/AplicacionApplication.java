package com.LS.Aplicacion;

import DTO.EspacioDTO;
import DTO.ReservaDTO;
import ObjetoValor.*;
import com.LS.Aplicacion.Controlador.EspacioController;
import com.LS.Aplicacion.Controlador.ReservaController;
import com.LS.Aplicacion.Mensajeria.Emisor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import DTO.GerenteDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class AplicacionApplication {

	@Autowired
	Emisor emisor;

	public static void main(String[] args) {
		SpringApplication.run(AplicacionApplication.class, args);
	}

}
