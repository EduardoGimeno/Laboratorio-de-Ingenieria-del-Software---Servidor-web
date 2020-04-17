package com.LS.Aplicacion;

import com.LS.Aplicacion.Mensajeria.Emisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AplicacionApplication {

	@Autowired
	Emisor emisor;

	@Bean
	public void conexionBroker() throws Exception {
		//emisor.enviarMensaje("startConnection");
		//System.out.println(emisor.recibirMensaje());
	}

	public static void main(String[] args) {
		SpringApplication.run(AplicacionApplication.class, args);
	}

}
