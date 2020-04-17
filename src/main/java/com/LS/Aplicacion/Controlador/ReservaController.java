package com.LS.Aplicacion.Controlador;

import DTO.BusquedaDTO;
import DTO.ReservaDTO;
import com.LS.Aplicacion.Mensajeria.Emisor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class ReservaController {

    @Autowired
    Emisor emisor;

    @PostMapping(path = "/createReserva")
    public ResponseEntity crear(@RequestBody ReservaDTO reserva) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String dto = mapper.writeValueAsString(reserva);
        String json = "nombrefuncion," + dto;
        emisor.enviarMensaje(json);
        emisor.recibirMensaje();
        // Hay que cambiar esta intruccion para que devuelva el DTO
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ReservaDTO.class);
    }

    @PatchMapping(path = "/changeState/{id}")
    public ResponseEntity cambiarEstado(@PathVariable UUID id, @RequestBody String estado, @RequestBody String motivo) throws Exception {
        String dto = "{\"id\":" + id.toString() + ",\"estado\":" + estado + ",\"motivo\":" + motivo + "}";
        String json = "nombrefuncion," + dto;
        emisor.enviarMensaje(json);
        emisor.recibirMensaje();
        // Hay que cambiar esta intruccion para que devuelva el DTO
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ReservaDTO.class);
    }

    @GetMapping(path = "/getReservasByEspacio")
    public ResponseEntity getReservasByEspacio(UUID idEspacio) throws Exception {
        String dto = "{\"id\":" + idEspacio + "}";
        String json = "nombrefuncion," + dto;
        emisor.enviarMensaje(json);
        emisor.recibirMensaje();
        // Hay que cambiar esta intruccion para que devuelva el DTO
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ReservaDTO.class);
    }

    @GetMapping(path = "/getReservasFiltradas")
    public ResponseEntity getFilteredReservas(BusquedaDTO busqueda) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String dto = mapper.writeValueAsString(busqueda);
        String json = "nombrefuncion," + dto;
        emisor.enviarMensaje(json);
        emisor.recibirMensaje();
        // Hay que cambiar esta intruccion para que devuelva el DTO
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ReservaDTO.class);
    }


}