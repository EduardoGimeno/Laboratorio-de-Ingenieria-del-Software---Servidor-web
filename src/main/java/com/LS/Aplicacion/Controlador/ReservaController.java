package com.LS.Aplicacion.Controlador;

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
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
        RequestMethod.DELETE,RequestMethod.PATCH})
@RequestMapping(path="/reserva")
public class ReservaController {

    @Autowired
    Emisor emisor;

    @PostMapping(path = "/createReserva")
    public ResponseEntity<Object> crear(@RequestBody ReservaDTO reserva) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String dto = mapper.writeValueAsString(reserva);
        String json = "crearReserva," + dto;
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(JSONObject.stringToValue(response));
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.readValue(response, ReservaDTO.class));
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
        if (response.equals("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(JSONObject.stringToValue(response));
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.readValue(response, ReservaDTO.class));
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
        if (response.equals("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(JSONObject.stringToValue(response));
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.readValue(response, ReservaDTO.class));
        }
    }

    @GetMapping(path = "/getReservasFiltradas")
    public ResponseEntity<Object> getFilteredReservas(BusquedaDTO busqueda) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String dto = mapper.writeValueAsString(busqueda);
        String json = "nombrefuncion," + dto;
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(JSONObject.stringToValue(response));
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.readValue(response, ReservaDTO.class));
        }
    }
}