package com.LS.Aplicacion;

import com.LS.Aplicacion.Mensajeria.Emisor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AplicacionApplication {

	@Bean
	public void conexionBroker() throws Exception {
		new Emisor().enviarMensaje("startConection");
	}

	public static void main(String[] args) {
		SpringApplication.run(AplicacionApplication.class, args);
	}

}
