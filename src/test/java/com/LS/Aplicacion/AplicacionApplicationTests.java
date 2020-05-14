package com.LS.Aplicacion;

import com.LS.Aplicacion.Controlador.TestControlador;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AplicacionApplicationTests {

	TestControlador testControlador;

	@Test
	void main() throws Exception {
		testControlador.puedeCrearReserva();
		testControlador.puedeCambiarEstadoReserva();
		testControlador.puedeBuscarReservasPorEspacio();
		testControlador.puedeFiltrarReservas();
	}

}
