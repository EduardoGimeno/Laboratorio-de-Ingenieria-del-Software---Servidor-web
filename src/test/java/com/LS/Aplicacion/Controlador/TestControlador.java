package com.LS.Aplicacion.Controlador;

import DTO.BusquedaDTO;
import DTO.ReservaDTO;
import ObjetoValor.Dia;
import ObjetoValor.EstadoReserva;
import ObjetoValor.Usuario;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestControlador {

    final Usuario usuario = new Usuario("Nombre", "Apellidos", "email@usuario.com", 123456, 123456789);

    /*--- Test ReservaController ---*/

    ReservaController reservaController = new ReservaController();

    @Test
    public void puedeCrearReserva() throws Exception {

        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setId("idPrueba");
        reservaDTO.setHoraInicio(Timestamp.valueOf("2007-09-23 10:10:10.0"));
        reservaDTO.setHoraFin(Timestamp.valueOf("2007-09-23 10:10:10.0"));
        reservaDTO.setFechaInicio(Timestamp.valueOf("2007-09-23 10:10:10.0"));
        reservaDTO.setFechaFin(Timestamp.valueOf("2007-09-23 10:10:10.0"));
        reservaDTO.setEstado(EstadoReserva.PENDIENTE);
        reservaDTO.setUsuario(usuario);
        reservaDTO.setIdEspacio("idEspacioPrueba");
        reservaDTO.addDia(Dia.LUNES);

        JSONObject jsonRecivido = (JSONObject) reservaController.crear(reservaDTO).getBody();

        JSONObject jsonEsperado = new JSONObject();
        jsonEsperado.append("id", "idPrueba");
        jsonEsperado.append("horaInicio", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("horaFin", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("fechaInicio", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("fechaFin", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("estado", EstadoReserva.PENDIENTE);
        jsonEsperado.append("usuario", usuario);
        jsonEsperado.append("idEspacio", "idEspacioPrueba");
        List<Dia> dias = new ArrayList<>();
        dias.add(Dia.LUNES);
        jsonEsperado.append("dias", dias);

        assertEquals(jsonEsperado, jsonRecivido);
    }

    @Test
    public void puedeCambiarEstadoReserva() throws Exception {

        JSONObject jsonRecivido = (JSONObject) reservaController.cambiarEstado("idPrueba", "Aceptada", "Motivo de prueba").getBody();

        JSONObject jsonEsperado = new JSONObject();
        jsonEsperado.append("id", "idPrueba");
        jsonEsperado.append("horaInicio", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("horaFin", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("fechaInicio", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("fechaFin", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("estado", EstadoReserva.ACEPTADA);
        jsonEsperado.append("usuario", usuario);
        jsonEsperado.append("idEspacio", "idEspacioPrueba");
        List<Dia> dias = new ArrayList<>();
        dias.add(Dia.LUNES);
        jsonEsperado.append("dias", dias);

        assertEquals(jsonEsperado, jsonRecivido);
    }

    @Test
    public void puedeBuscarReservasPorEspacio() throws Exception {

        JSONArray jsonRecivido = (JSONArray) reservaController.getReservasByEspacio("idEspacio").getBody();

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonEsperado = new JSONObject();
        jsonEsperado.append("id", "idPrueba");
        jsonEsperado.append("horaInicio", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("horaFin", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("fechaInicio", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("fechaFin", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("estado", EstadoReserva.ACEPTADA);
        jsonEsperado.append("usuario", usuario);
        jsonEsperado.append("idEspacio", "idEspacioPrueba");
        List<Dia> dias = new ArrayList<>();
        dias.add(Dia.LUNES);
        jsonEsperado.append("dias", dias);
        jsonArray.put(jsonEsperado);

        assertEquals(jsonArray, jsonRecivido);
    }

    @Test
    public void puedeFiltrarReservas() throws Exception {

        BusquedaDTO busquedaDTO = new BusquedaDTO();
        busquedaDTO.setEdificio("edicioPrueba");
        busquedaDTO.setTipoEspacio("espacioPrueba");
        busquedaDTO.setPizarra(true);
        busquedaDTO.setProyector(true);
        busquedaDTO.setCapacidad(10);

        JSONArray jsonRecivido = (JSONArray) reservaController.getFilteredReservas(busquedaDTO).getBody();

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonEsperado = new JSONObject();
        jsonEsperado.append("id", "idPrueba");
        jsonEsperado.append("horaInicio", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("horaFin", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("fechaInicio", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("fechaFin", Timestamp.valueOf("2007-09-23 10:10:10.0"));
        jsonEsperado.append("estado", EstadoReserva.ACEPTADA);
        jsonEsperado.append("usuario", usuario);
        jsonEsperado.append("idEspacio", "idEspacioPrueba");
        List<Dia> dias = new ArrayList<>();
        dias.add(Dia.LUNES);
        jsonEsperado.append("dias", dias);
        jsonArray.put(jsonEsperado);

        assertEquals(jsonArray, jsonRecivido);
    }
}