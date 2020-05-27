package com.LS.Aplicacion.Controlador;

import DTO.BusquedaDTO;
import DTO.DatosDTO;
import com.LS.Aplicacion.Mensajeria.Emisor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;

public class EspacioController {

    @Autowired
    Emisor emisor;

    @GetMapping(path = "/getInfo")
    public ResponseEntity<Object> obtenerInformacion(@RequestBody String id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("id", id);
        String json = "obtenerEspacioPorId," + jsonObject.toString();
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(JSONObject.stringToValue(response));
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.readValue(response, EspacioDTO.class));
        }
    }

    @GetMapping(path = "/search")
    public ResponseEntity<Object> buscar(@RequestBody BusquedaDTO busqueda) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String dto = mapper.writeValueAsString(busqueda);
        String json = "filtrarBusquedaEspacios," + dto;
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(JSONObject.stringToValue(response));
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.readValue(response, EspacioDTO.class));
        }
    }

    @GetMapping(path = "/getInfoFiltered")
    public ResponseEntity<Object> obtenerPorEdificioYTipo(@RequestBody String edificio, @RequestBody String tipo) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("edificio", edificio);
        jsonObject.append("tipo", tipo);
        String json = "obtenerEspacioPorEdificioYTipo," + jsonObject.toString();
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(JSONObject.stringToValue(response));
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.readValue(response, EspacioDTO.class));
        }
    }

    @PatchMapping(path = "/modifySpace")
    public ResponseEntity<Object> modificarDatos(@RequestBody DatosDTO datosDTO) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String dto = mapper.writeValueAsString(datosDTO);
        String json = "modificarEspacio," + dto;
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(JSONObject.stringToValue(response));
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.readValue(response, EspacioDTO.class));
        }
    }

    @PatchMapping(path = "/getSpacesBetween")
    public ResponseEntity<Object> obtenerHorarioEntreFechas(@RequestBody String idEspacio, @RequestBody Timestamp inicio, @RequestBody Timestamp fin) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("idEspacio", idEspacio);
        jsonObject.append("inicio", inicio);
        jsonObject.append("fin", fin);
        String json = "obtenerHorarioEntreFechas," + jsonObject.toString();
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(JSONObject.stringToValue(response));
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.readValue(response, EspacioDTO.class));
        }
    }
}
