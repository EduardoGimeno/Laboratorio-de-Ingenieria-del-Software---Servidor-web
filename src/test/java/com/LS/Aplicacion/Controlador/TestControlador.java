package com.LS.Aplicacion.Controlador;

import DTO.BusquedaDTO;
import DTO.DatosDTO;
import DTO.ReservaDTO;
import ObjetoValor.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestControlador {

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

        JSONObject jsonRecibido = (JSONObject) reservaController.cambiarEstado("idPrueba", "Aceptada", "Motivo de prueba").getBody();

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

        assertEquals(jsonEsperado, jsonRecibido);
    }

    @Test
    public void puedeBuscarReservasPorEspacio() throws Exception {

        JSONArray jsonRecibido = (JSONArray) reservaController.getReservasByEspacio("idEspacio").getBody();

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

        assertEquals(jsonArray, jsonRecibido);
    }

    @Test
    public void puedeFiltrarReservas() throws Exception {

        BusquedaDTO busquedaDTO = new BusquedaDTO();
        busquedaDTO.setEdificio("edicioPrueba");
        busquedaDTO.setTipoEspacio("espacioPrueba");
        busquedaDTO.setPizarra(true);
        busquedaDTO.setProyector(true);
        busquedaDTO.setCapacidad(99);

        JSONArray jsonRecibido = (JSONArray) reservaController.getFilteredReservas(busquedaDTO).getBody();

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

        assertEquals(jsonArray, jsonRecibido);
    }

    /*--- Test EspacioController ---*/

    EspacioController espacioController = new EspacioController();

    @Test
    public void puedeObtenerInformacion() throws Exception {

        JSONObject jsonEsperado = new JSONObject();
        jsonEsperado.append("id", "idPrueba");
        jsonEsperado.append("tipo", "Laboratorio");
        jsonEsperado.append("capacidad", 30);
        List<Equipamiento> equipamiento = new ArrayList<>();
        equipamiento.add(new Equipamiento(TipoEquipamiento.PIZARRA, 1, 1));
        jsonEsperado.append("equipamiento", equipamiento);
        jsonEsperado.append("ubicacion", new Ubicacion("Ada Byron", 1));
        jsonEsperado.append("notas", "Nota de prueba");

        JSONObject jsonRecibido = (JSONObject) espacioController.obtenerInformacion("idPrueba").getBody();

        assertEquals(jsonEsperado, jsonRecibido);
    }

    @Test
    public void puedeBuscar() throws Exception {

        BusquedaDTO busquedaDTO = new BusquedaDTO();
        busquedaDTO.setEdificio("Ada Byron");
        busquedaDTO.setTipoEspacio("Laboratorio");
        busquedaDTO.setPizarra(true);
        busquedaDTO.setProyector(false);
        busquedaDTO.setCapacidad(30);

        JSONArray jsonRecibido = (JSONArray) espacioController.buscar(busquedaDTO).getBody();

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonEsperado = new JSONObject();
        jsonEsperado.append("id", "idPrueba");
        jsonEsperado.append("tipo", "Laboratorio");
        jsonEsperado.append("capacidad", 30);
        List<Equipamiento> equipamiento = new ArrayList<>();
        equipamiento.add(new Equipamiento(TipoEquipamiento.PIZARRA, 1, 1));
        jsonEsperado.append("equipamiento", equipamiento);
        jsonEsperado.append("ubicacion", new Ubicacion("Ada Byron", 1));
        jsonEsperado.append("notas", "Nota de prueba");
        jsonArray.put(jsonEsperado);

        assertEquals(jsonArray, jsonRecibido);
    }

    @Test
    public void puedeFiltrarPorEdificioYTipo() throws Exception {

        JSONArray jsonRecibido = (JSONArray) espacioController.obtenerPorEdificioYTipo("Ada Byron", "Laboratorio").getBody();

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonEsperado = new JSONObject();
        jsonEsperado.append("id", "idPrueba");
        jsonEsperado.append("tipo", "Laboratorio");
        jsonEsperado.append("capacidad", 30);
        List<Equipamiento> equipamiento = new ArrayList<>();
        equipamiento.add(new Equipamiento(TipoEquipamiento.PIZARRA, 1, 1));
        jsonEsperado.append("equipamiento", equipamiento);
        jsonEsperado.append("ubicacion", new Ubicacion("Ada Byron", 1));
        jsonEsperado.append("notas", "Nota de prueba");
        jsonArray.put(jsonEsperado);

        assertEquals(jsonArray, jsonRecibido);
    }

    @Test
    public void puedeModificarDatos() throws Exception {

        DatosDTO datosDTO = new DatosDTO();
        datosDTO.setId("idPrueba");
        datosDTO.setPizarra(true);
        datosDTO.setProyector(true);
        datosDTO.setCapacidad(30);

        JSONObject jsonRecibido = (JSONObject) espacioController.modificarDatos(datosDTO).getBody();

        JSONObject jsonEsperado = new JSONObject();
        jsonEsperado.append("id", "idPrueba");
        jsonEsperado.append("tipo", "Laboratorio");
        jsonEsperado.append("capacidad", 30);
        List<Equipamiento> equipamiento = new ArrayList<>();
        equipamiento.add(new Equipamiento(TipoEquipamiento.PIZARRA, 1, 1));
        equipamiento.add(new Equipamiento(TipoEquipamiento.CANON, 1, 1));
        jsonEsperado.append("equipamiento", equipamiento);
        jsonEsperado.append("ubicacion", new Ubicacion("Ada Byron", 1));
        jsonEsperado.append("notas", "Nota de prueba");

        assertEquals(jsonEsperado, jsonRecibido);
    }

    @Test
    public void puedeObtenerHorarioEntreFechas() throws Exception {

        JSONObject jsonRecibido = (JSONObject) espacioController.obtenerHorarioEntreFechas("idEspacio", Timestamp.valueOf("2007-09-23 10:10:10.0"), Timestamp.valueOf("2007-09-23 10:10:10.0")).getBody();

        JSONObject jsonEsperado = new JSONObject();
        jsonEsperado.append("idEspacio", "idPrueba");
        jsonEsperado.append("dia", "Laboratorio");
        jsonEsperado.append("horaInicio", 9);
        jsonEsperado.append("horaFin", 21);
        List<Integer> horasOcupadas = new ArrayList<>();
        horasOcupadas.add(9);
        horasOcupadas.add(10);
        jsonEsperado.append("horasOcupadas", horasOcupadas);

        assertEquals(jsonEsperado, jsonRecibido);
    }

    /*--- Test GerenteController ---*/

    GerenteController gerenteController = new GerenteController();

    @Test
    public void puedeLoguear() throws Exception {

        JSONObject jsonRecibido = (JSONObject) gerenteController.logIn("nomUsuarioPrueba", "passUsuarioPrueba").getBody();

        System.out.println(jsonRecibido);

        assert jsonRecibido != null;
        assertEquals("true", jsonRecibido.toString());

    }
}