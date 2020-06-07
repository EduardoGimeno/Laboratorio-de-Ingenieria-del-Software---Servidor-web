package com.LS.Aplicacion.Controlador;

import Enum.EstadoReserva;
import DTO.BusquedaDTO;
import DTO.ReservaDTO;
import com.LS.Aplicacion.Mensajeria.Emisor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("ReservaController")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.PATCH})
@RequestMapping(path = "/reserva")
public class ReservaController {

    @Autowired
    Emisor emisor = new Emisor();

    public ReservaController() throws Exception {
    }

    @PostMapping(path = "/createReserva")
    public ResponseEntity<Object> crear(@RequestBody ReservaDTO reserva) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String dto = mapper.writeValueAsString(reserva);
        String json = "crearReserva," + dto;
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("ERROR")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(JSONObject.stringToValue(response));
        }
    }

    @PatchMapping(path = "/changeState/{id}")
    public ResponseEntity<Object> cambiarEstado(@PathVariable String id, @RequestBody String estado, @RequestBody String motivo) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("estado", estado);
        jsonObject.put("motivo", motivo);
        String json = "modificarEstadoReserva," + jsonObject.toString();
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("ERROR")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(JSONObject.stringToValue(response));
        }
    }

    @GetMapping(path = "/getReservasByEspacio")
    public ResponseEntity<Object> getReservasByEspacio(String idEspacio) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idEspacio", idEspacio);
        String json = "obtenerReservasEspacio," + jsonObject.toString();
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("ERROR")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(JSONObject.stringToValue(response));
        }
    }

    @GetMapping(path = "/getHorarios")
    public ResponseEntity<Object> getHorarios(String idEspacio, long fechaInicio,
                                              long fechaFin) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idEspacio", idEspacio);
        jsonObject.put("fechaInicio", fechaInicio);
        jsonObject.put("fechaFin", fechaFin);
        String json = "obtenerHorarioEntreFechas," + jsonObject.toString();
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("ERROR")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(JSONObject.stringToValue(response));
        }
    }

    @GetMapping(path = "/getReservasFiltradas")
    public ResponseEntity<Object> getFilteredReservas(String edificio, String tipo, long fechaIni, long fechaFin, int horaIni, int horaFin, EstadoReserva estado) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("edificio", edificio);
        jsonObject.put("tipo", tipo);
        jsonObject.put("fechaIni", fechaIni);
        jsonObject.put("fechaFin", fechaFin);
        jsonObject.put("horaIni", horaIni);
        jsonObject.put("horaFin", horaFin);
        jsonObject.put("estado", estado);
        String json = "filtrarBusquedaEspacios," + jsonObject.toString();
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("ERROR")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(JSONObject.stringToValue(response));
        }
    }
}