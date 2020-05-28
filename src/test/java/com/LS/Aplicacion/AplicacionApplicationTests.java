package com.LS.Aplicacion;

import com.LS.Aplicacion.Controlador.TestControlador;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AplicacionApplicationTests {

    TestControlador testControlador;

    @Test
    void main() throws Exception {
        System.out.println("Ejecutando test del controlador...");
        System.out.println("Controlador reservas...");
        testControlador.puedeCrearReserva();
        testControlador.puedeCambiarEstadoReserva();
        testControlador.puedeBuscarReservasPorEspacio();
        testControlador.puedeFiltrarReservas();
        System.out.println("Controlador espacios...");
        testControlador.puedeObtenerInformacion();
        testControlador.puedeBuscar();
        testControlador.puedeFiltrarPorEdificioYTipo();
        testControlador.puedeModificarDatos();
        System.out.println("Controlador gerente...");
        testControlador.puedeLoguear();
        System.out.println("Test del controlador pasados");

        System.out.println("Ejecutando test de integridad...");
    }

}
