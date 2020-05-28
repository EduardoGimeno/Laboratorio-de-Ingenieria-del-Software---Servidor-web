package com.LS.Aplicacion.Controlador;

import DTO.BusquedaDTO;
import com.LS.Aplicacion.Mensajeria.Emisor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class EspacioController {

    @Autowired
    Emisor emisor;

    @GetMapping(path = "/getInfo")
    public ResponseEntity obtenerInformacion (@RequestBody String id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        String json = "obtenerEspacioPorId," + jsonObject.toString();
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(JSONObject.stringToValue(response));
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.readValue(response, EspacioDTO.class));
        }
    }

    @GetMapping(path = "/search")
    public ResponseEntity buscar (@RequestBody BusquedaDTO busqueda) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String dto = mapper.writeValueAsString(busqueda);
        String json = "nombrefuncion," + dto;
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(JSONObject.stringToValue(response));
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.readValue(response, EspacioDTO.class));
        }
    }

    @GetMapping(path = "/getInfoFiltered")
    public ResponseEntity obtenerPorEdificioYTipo (@RequestBody String edificio, @RequestBody String tipo) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("edificio", edificio);
        jsonObject.put("tipo", tipo);
        String json = "obtenerEspacioPorEdificioYTipo," + jsonObject.toString();
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(JSONObject.stringToValue(response));
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.readValue(response, EspacioDTO.class));
        }
    }

    @PatchMapping(path = "/modifySpace")
    public ResponseEntity modificarDatos (@RequestBody String edificio, @RequestBody String tipo) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("edificio", edificio);
        jsonObject.put("tipo", tipo);
        String json = "modificarEspacio," + jsonObject.toString();
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(JSONObject.stringToValue(response));
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.readValue(response, EspacioDTO.class));
        }
    }
}

