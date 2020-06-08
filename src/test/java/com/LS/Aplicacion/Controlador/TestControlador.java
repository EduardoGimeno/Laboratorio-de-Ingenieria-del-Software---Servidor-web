package com.LS.Aplicacion.Controlador;

import DTO.*;
import Enum.Dia;
import Enum.EstadoReserva;
import Enum.TipoEquipamiento;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(OrderAnnotation.class)
public class TestControlador {

    final UsuarioDTO usuario = new UsuarioDTO();

    String idPrueba = "";

    public TestControlador() throws Exception {
        usuario.setNombre("Nombre");
        usuario.setApellidos("Apellidos");
        usuario.setEmail("email@usuario.com");
        usuario.setNIA(123456);
        usuario.setTelefono(123456789);
    }

    /*--- Test ReservaController ---*/

    ReservaController reservaController = new ReservaController();

    @Test
    @Order(1)
    public void puedeCrearReserva() throws Exception {

        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setId("idPrueba");
        reservaDTO.setHoraInicio(10);
        reservaDTO.setHoraFin(11);
        reservaDTO.setFechaInicio(Timestamp.valueOf("2007-09-23 0:0:0.0"));
        reservaDTO.setFechaFin(Timestamp.valueOf("2007-09-23 0:0:0.0"));
        reservaDTO.setEstado(EstadoReserva.PENDIENTE);
        reservaDTO.setUsuario(usuario);
        reservaDTO.setIdEspacio("CRE.1200.03.060");
        reservaDTO.setDias(new ArrayList<>());
        reservaDTO.addDia(Dia.LUNES);

        JSONObject jsonRecibido = new JSONObject((String) reservaController.crear(reservaDTO).getBody());

        idPrueba = (String) jsonRecibido.get("id");

        assertEquals(10, jsonRecibido.get("horaInicio"));
        assertEquals(11, jsonRecibido.get("horaFin"));
        assertEquals(Timestamp.valueOf("2007-09-23 0:0:0.0").getTime(), jsonRecibido.get("fechaInicio"));
        assertEquals(Timestamp.valueOf("2007-09-23 0:0:0.0").getTime(), jsonRecibido.get("fechaFin"));
        assertEquals(EstadoReserva.PENDIENTE.getEstado().toUpperCase(), jsonRecibido.get("estado"));
        JSONObject objectUsuario = (JSONObject) jsonRecibido.get("usuario");
        assertEquals(new JSONObject(usuario).get("nombre"), objectUsuario.get("nombre"));
        assertEquals(new JSONObject(usuario).get("apellidos"), objectUsuario.get("apellidos"));
        assertEquals(new JSONObject(usuario).get("email"), objectUsuario.get("email"));
        assertEquals(new JSONObject(usuario).get("NIA"), objectUsuario.get("nia"));
        assertEquals(new JSONObject(usuario).get("telefono"), objectUsuario.get("telefono"));
    }

    @Test
    @Order(2)
    public void puedeBuscarReservasPorId() throws Exception {

        JSONObject jsonObject = new JSONObject((String) reservaController.getReservaById(idPrueba).getBody());

        assertEquals(10, jsonObject.get("horaInicio"));
        assertEquals(11, jsonObject.get("horaFin"));
        assertEquals(Timestamp.valueOf("2007-09-23 0:0:0.0").getTime(), jsonObject.get("fechaInicio"));
        assertEquals(Timestamp.valueOf("2007-09-23 0:0:0.0").getTime(), jsonObject.get("fechaFin"));
        assertEquals(EstadoReserva.PENDIENTE.getEstado().toUpperCase(), jsonObject.get("estado"));
        JSONObject objectUsuario = (JSONObject) jsonObject.get("usuario");
        assertEquals(new JSONObject(usuario).get("nombre"), objectUsuario.get("nombre"));
        assertEquals(new JSONObject(usuario).get("apellidos"), objectUsuario.get("apellidos"));
        assertEquals(new JSONObject(usuario).get("email"), objectUsuario.get("email"));
        assertEquals(new JSONObject(usuario).get("NIA"), objectUsuario.get("nia"));
        assertEquals(new JSONObject(usuario).get("telefono"), objectUsuario.get("telefono"));
    }

    @Test
    @Order(3)
    public void puedeCambiarEstadoReserva() throws Exception {

        JSONObject jsonRecibido = new JSONObject((String) reservaController.cambiarEstado(idPrueba, "ACEPTADA", "Motivo de prueba").getBody());

        assertEquals(10, jsonRecibido.get("horaInicio"));
        assertEquals(11, jsonRecibido.get("horaFin"));
        assertEquals(Timestamp.valueOf("2007-09-23 0:0:0.0").getTime(), jsonRecibido.get("fechaInicio"));
        assertEquals(Timestamp.valueOf("2007-09-23 0:0:0.0").getTime(), jsonRecibido.get("fechaFin"));
        assertEquals(EstadoReserva.ACEPTADA.getEstado().toUpperCase(), jsonRecibido.get("estado"));
        JSONObject objectUsuario = (JSONObject) jsonRecibido.get("usuario");
        assertEquals(new JSONObject(usuario).get("nombre"), objectUsuario.get("nombre"));
        assertEquals(new JSONObject(usuario).get("apellidos"), objectUsuario.get("apellidos"));
        assertEquals(new JSONObject(usuario).get("email"), objectUsuario.get("email"));
        assertEquals(new JSONObject(usuario).get("NIA"), objectUsuario.get("nia"));
        assertEquals(new JSONObject(usuario).get("telefono"), objectUsuario.get("telefono"));
    }

    @Test
    @Order(4)
    public void puedeObtenerHorarioDeEspacio() throws Exception {

        JSONArray jsonRecibido = new JSONArray((String) reservaController.getHorarios("CRE.1200.03.060", Timestamp.valueOf("2007-09-23 0:0:0.0").getTime(), Timestamp.valueOf("2007-09-23 0:0:0.0").getTime()).getBody());

        JSONArray jsonArray = new JSONArray();
        // JSON para HorarioDTO
        JSONObject jsonEsperado = new JSONObject();
        jsonEsperado.put("idEspacio", "CRE.1200.03.060");
        jsonEsperado.put("dia", Timestamp.valueOf("2007-09-23 0:0:0.0").getTime());
        jsonEsperado.put("horaInicio", 8);
        jsonEsperado.put("horaFin", 22);
        List<Integer> horasOcupadas = new ArrayList<>();
        jsonEsperado.put("horasOcupadas", horasOcupadas);
        jsonArray.put(jsonEsperado);

        assertEquals(jsonArray.toString(), jsonRecibido.toString());
    }

    @Test
    @Order(5)
    public void puedeFiltrarReservas() throws Exception {

        String edificio = "Ada Byron";
        String tipo = "Laboratorio";
        Timestamp fechaIni = Timestamp.valueOf("2007-09-23 0:0:0.0");
        Timestamp fechaFin = Timestamp.valueOf("2007-09-23 0:0:0.0");
        int horaIni = 10;
        int horaFin = 11;
        EstadoReserva estadoReserva = EstadoReserva.ACEPTADA;

        JSONArray jsonRecibido = new JSONArray((String) reservaController.getFilteredReservas(edificio, tipo, fechaIni.getTime(), fechaFin.getTime(), horaIni, horaFin, estadoReserva).getBody());

        JSONObject jsonObject = (JSONObject) jsonRecibido.get(0);

        assertEquals(10, jsonObject.get("horaInicio"));
        assertEquals(11, jsonObject.get("horaFin"));
        assertEquals(Timestamp.valueOf("2007-09-23 0:0:0.0").getTime(), jsonObject.get("fechaInicio"));
        assertEquals(Timestamp.valueOf("2007-09-23 0:0:0.0").getTime(), jsonObject.get("fechaFin"));
        assertEquals(EstadoReserva.ACEPTADA.getEstado().toUpperCase(), jsonObject.get("estado"));
        JSONObject objectUsuario = (JSONObject) jsonObject.get("usuario");
        assertEquals(new JSONObject(usuario).get("nombre"), objectUsuario.get("nombre"));
        assertEquals(new JSONObject(usuario).get("apellidos"), objectUsuario.get("apellidos"));
        assertEquals(new JSONObject(usuario).get("email"), objectUsuario.get("email"));
        assertEquals(new JSONObject(usuario).get("NIA"), objectUsuario.get("nia"));
        assertEquals(new JSONObject(usuario).get("telefono"), objectUsuario.get("telefono"));
    }

    /*--- Test EspacioController ---*/

    EspacioController espacioController = new EspacioController();

    @Test
    @Order(6)
    public void puedeObtenerInformacion() throws Exception {

        JSONObject jsonEsperado = new JSONObject();
        jsonEsperado.put("id", "CRE.1200.03.060");
        jsonEsperado.put("tipo", "Laboratorio");
        jsonEsperado.put("nombre", "LABORATORIO L 3.04");
        jsonEsperado.put("capacidad", 60);
        List<EquipamientoDTO> equipamiento = new ArrayList<>();
        EquipamientoDTO equipamientoDTO = new EquipamientoDTO();
        equipamientoDTO.setTipo(TipoEquipamiento.PANTALLA);
        equipamientoDTO.setCantidad(1);
        equipamientoDTO.setMaxCantidad(1);
        equipamiento.add(equipamientoDTO);
        EquipamientoDTO equipamientoDTO2 = new EquipamientoDTO();
        equipamientoDTO2.setTipo(TipoEquipamiento.PIZARRA);
        equipamientoDTO2.setCantidad(5);
        equipamientoDTO2.setMaxCantidad(5);
        equipamiento.add(equipamientoDTO2);
        EquipamientoDTO equipamientoDTO3 = new EquipamientoDTO();
        equipamientoDTO3.setTipo(TipoEquipamiento.ORDENADOR);
        equipamientoDTO3.setCantidad(30);
        equipamientoDTO3.setMaxCantidad(30);
        equipamiento.add(equipamientoDTO3);
        jsonEsperado.put("equipamiento", equipamiento);
        UbicacionDTO ubicacionDTO = new UbicacionDTO();
        ubicacionDTO.setEdificio("Ada Byron");
        ubicacionDTO.setPlanta(3);
        jsonEsperado.put("ubicacion", new JSONObject(ubicacionDTO));
        jsonEsperado.put("notas", "");
        jsonEsperado.put("reservable", true);

        JSONObject jsonRecibido = new JSONObject((String) espacioController.obtenerInformacion("CRE.1200.03.060").getBody());

        assertEquals(jsonEsperado.get("id"), jsonRecibido.get("id"));
        assertEquals(jsonEsperado.get("tipo"), jsonRecibido.get("tipo"));
        assertEquals(jsonEsperado.get("nombre"), jsonRecibido.get("nombre"));
        assertEquals(jsonEsperado.get("capacidad"), jsonRecibido.get("capacidad"));
        assertEquals(jsonEsperado.get("equipamiento").toString(), jsonRecibido.get("equipamiento").toString());
        assertEquals(jsonEsperado.get("ubicacion").toString(), jsonRecibido.get("ubicacion").toString());
        assertEquals(jsonEsperado.get("notas"), jsonRecibido.get("notas"));
        assertEquals(jsonEsperado.get("reservable"), jsonRecibido.get("reservable"));
    }

    @Test
    @Order(7)
    public void puedeBuscar() throws Exception {

        String[] equip = new String[]{
                "CANON;1;1",
                "PANTALLA;1;1",
                "PIZARRA;3;3",
                "ORDENADOR;16;16"
        };

        JSONArray jsonRecibido = new JSONArray((String) espacioController.buscar("Ada Byron", "Laboratorio", equip, 60, Timestamp.valueOf("2007-09-23 10:10:10.0").getTime(), Timestamp.valueOf("2007-09-23 10:10:10.0").getTime(), 9, 10, new Dia[]{}, true).getBody());

        JSONObject jsonEsperado = new JSONObject();
        jsonEsperado.put("id", "CRE.1200.02.170");
        jsonEsperado.put("tipo", "Laboratorio");
        jsonEsperado.put("nombre", "LABORATORIO L 2.02");
        jsonEsperado.put("capacidad", 62);
        List<EquipamientoDTO> equipamiento = new ArrayList<>();
        EquipamientoDTO equipamientoDTO = new EquipamientoDTO();
        equipamientoDTO.setTipo(TipoEquipamiento.CANON);
        equipamientoDTO.setCantidad(1);
        equipamientoDTO.setMaxCantidad(1);
        equipamiento.add(equipamientoDTO);
        EquipamientoDTO equipamientoDTO2 = new EquipamientoDTO();
        equipamientoDTO2.setTipo(TipoEquipamiento.PANTALLA);
        equipamientoDTO2.setCantidad(1);
        equipamientoDTO2.setMaxCantidad(1);
        equipamiento.add(equipamientoDTO2);
        EquipamientoDTO equipamientoDTO3 = new EquipamientoDTO();
        equipamientoDTO3.setTipo(TipoEquipamiento.PIZARRA);
        equipamientoDTO3.setCantidad(3);
        equipamientoDTO3.setMaxCantidad(3);
        equipamiento.add(equipamientoDTO3);
        EquipamientoDTO equipamientoDTO4 = new EquipamientoDTO();
        equipamientoDTO4.setTipo(TipoEquipamiento.ORDENADOR);
        equipamientoDTO4.setCantidad(16);
        equipamientoDTO4.setMaxCantidad(16);
        equipamiento.add(equipamientoDTO4);
        jsonEsperado.put("equipamiento", equipamiento);
        UbicacionDTO ubicacionDTO = new UbicacionDTO();
        ubicacionDTO.setEdificio("Ada Byron");
        ubicacionDTO.setPlanta(2);
        jsonEsperado.put("ubicacion", new JSONObject(ubicacionDTO));
        jsonEsperado.put("notas", "");
        jsonEsperado.put("reservable", true);

        JSONObject jsonObject = (JSONObject) jsonRecibido.get(0);

        assertEquals(jsonEsperado.get("id"), jsonObject.get("id"));
        assertEquals(jsonEsperado.get("tipo"), jsonObject.get("tipo"));
        assertEquals(jsonEsperado.get("nombre"), jsonObject.get("nombre"));
        assertEquals(jsonEsperado.get("capacidad"), jsonObject.get("capacidad"));
        assertEquals(jsonEsperado.get("equipamiento").toString(), jsonObject.get("equipamiento").toString());
        assertEquals(jsonEsperado.get("ubicacion").toString(), jsonObject.get("ubicacion").toString());
        assertEquals(jsonEsperado.get("notas"), jsonObject.get("notas"));
        assertEquals(jsonEsperado.get("reservable"), jsonObject.get("reservable"));
    }

    @Test
    @Order(8)
    public void puedeFiltrarPorEdificioYTipo() throws Exception {

        JSONArray jsonRecibido = new JSONArray((String) espacioController.obtenerPorEdificioYTipo("Ada Byron", "Laboratorio").getBody());

        assertEquals(34, jsonRecibido.length());
    }

    @Test
    @Order(9)
    public void puedeModificarDatos() throws Exception {

        DatosDTO datosDTO = new DatosDTO();
        datosDTO.setId("CRE.1200.03.060");
        datosDTO.setCapacidad(60);
        datosDTO.setNotas("");
        datosDTO.setReservable(true);
        List<EquipamientoDTO> equipamiento = new ArrayList<>();
        EquipamientoDTO equipamientoDTO = new EquipamientoDTO();
        equipamientoDTO.setTipo(TipoEquipamiento.PANTALLA);
        equipamientoDTO.setCantidad(1);
        equipamientoDTO.setMaxCantidad(1);
        equipamiento.add(equipamientoDTO);
        EquipamientoDTO equipamientoDTO2 = new EquipamientoDTO();
        equipamientoDTO2.setTipo(TipoEquipamiento.PIZARRA);
        equipamientoDTO2.setCantidad(5);
        equipamientoDTO2.setMaxCantidad(5);
        equipamiento.add(equipamientoDTO2);
        EquipamientoDTO equipamientoDTO3 = new EquipamientoDTO();
        equipamientoDTO3.setTipo(TipoEquipamiento.ORDENADOR);
        equipamientoDTO3.setCantidad(30);
        equipamientoDTO3.setMaxCantidad(30);
        equipamiento.add(equipamientoDTO3);
        datosDTO.setEquipamiento(equipamiento);

        JSONObject jsonRecibido = new JSONObject((String) espacioController.modificarDatos(datosDTO).getBody());

        JSONObject jsonEsperado = new JSONObject();
        jsonEsperado.put("id", "CRE.1200.03.060");
        jsonEsperado.put("tipo", "Laboratorio");
        jsonEsperado.put("nombre", "LABORATORIO L 3.04");
        jsonEsperado.put("capacidad", 60);
        jsonEsperado.put("equipamiento", equipamiento);
        UbicacionDTO ubicacionDTO = new UbicacionDTO();
        ubicacionDTO.setEdificio("Ada Byron");
        ubicacionDTO.setPlanta(3);
        jsonEsperado.put("ubicacion", new JSONObject(ubicacionDTO));
        jsonEsperado.put("notas", "");
        jsonEsperado.put("reservable", true);

        assertEquals(jsonEsperado.get("id"), jsonRecibido.get("id"));
        assertEquals(jsonEsperado.get("tipo"), jsonRecibido.get("tipo"));
        assertEquals(jsonEsperado.get("nombre"), jsonRecibido.get("nombre"));
        assertEquals(jsonEsperado.get("capacidad"), jsonRecibido.get("capacidad"));
        assertEquals(jsonEsperado.get("equipamiento").toString(), jsonRecibido.get("equipamiento").toString());
        assertEquals(jsonEsperado.get("ubicacion").toString(), jsonRecibido.get("ubicacion").toString());
        assertEquals(jsonEsperado.get("notas"), jsonRecibido.get("notas"));
        assertEquals(jsonEsperado.get("reservable"), jsonRecibido.get("reservable"));
    }

    @Test
    @Order(10)
    public void puedeObtenerHorarioEntreFechas() throws Exception {

        JSONArray jsonRecibido = new JSONArray((String) espacioController.obtenerHorarioEntreFechas("CRE.1200.03.060", Timestamp.valueOf("2007-09-23 10:10:10.0").getTime(), Timestamp.valueOf("2007-09-23 10:10:10.0").getTime()).getBody());

        JSONObject jsonEsperado = new JSONObject();
        jsonEsperado.put("idEspacio", "CRE.1200.03.060");
        jsonEsperado.put("dia", Timestamp.valueOf("2007-09-23 0:0:0.0").getTime());
        jsonEsperado.put("horaInicio", 8);
        jsonEsperado.put("horaFin", 22);
        List<Integer> horasOcupadas = new ArrayList<>();
        jsonEsperado.put("horasOcupadas", horasOcupadas);

        JSONObject jsonObject = (JSONObject) jsonRecibido.get(0);

        assertEquals(jsonEsperado.toString(), jsonObject.toString());
    }

    /*--- Test GerenteController ---*/

    GerenteController gerenteController = new GerenteController();

    @Test
    @Order(11)
    public void puedeLoguear() throws Exception {

        Boolean result = (Boolean) gerenteController.logIn("nomUsuarioPrueba", "passUsuarioPrueba").getBody();

        System.out.println(result);

        assertEquals(false, result);

    }
}