package com.LS.Aplicacion.Controlador;

import com.LS.Aplicacion.DTO.BusquedaDTO;
import com.LS.Aplicacion.DTO.EspacioDTO;
import com.LS.Aplicacion.Mensajeria.Emisor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public class EspacioController {

    @Autowired
    Emisor emisor;

    @GetMapping(path = "/getInfo")
    public ResponseEntity obtenerInformacion (@RequestBody UUID id) throws Exception {
        String dto = "{\"id\":" + id.toString() + "}";
        String json = "nombrefuncion," + dto;
        emisor.enviarMensaje(json);
        emisor.recibirMensaje();
        // Hay que cambiar esta intruccion para que devuelva el DTO
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(EspacioDTO.class);
    }

    @GetMapping(path = "/search")
    public ResponseEntity buscar (@RequestBody BusquedaDTO busqueda) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String dto = mapper.writeValueAsString(busqueda);
        String json = "nombrefuncion," + dto;
        emisor.enviarMensaje(json);
        emisor.recibirMensaje();
        // Hay que cambiar esta intruccion para que devuelva el DTO
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(EspacioDTO.class);
    }

    @GetMapping(path = "/getInfoFiltered")
    public ResponseEntity obtenerPorEdificioYTipo (@RequestBody String edificio, @RequestBody String tipo) throws Exception {
        String dto = "{\"id\":" + edificio + ",\"tipo\":" + tipo + "}";
        String json = "nombrefuncion," + dto;
        emisor.enviarMensaje(json);
        emisor.recibirMensaje();
        // Hay que cambiar esta intruccion para que devuelva el DTO
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(EspacioDTO.class);
    }

    @PatchMapping(path = "/getInfoFiltered")
    public ResponseEntity modificarDatos (@RequestBody String edificio, @RequestBody String tipo) throws Exception {
        String dto = "{\"id\":" + edificio + ",\"tipo\":" + tipo + "}";
        String json = "nombrefuncion," + dto;
        emisor.enviarMensaje(json);
        emisor.recibirMensaje();
        // Hay que cambiar esta intruccion para que devuelva el DTO
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(EspacioDTO.class);
    }
}
