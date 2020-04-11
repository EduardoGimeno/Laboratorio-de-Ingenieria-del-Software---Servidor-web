package com.LS.Aplicacion.Controlador;

import com.LS.Aplicacion.DTO.ReservaDTO;
import com.LS.Aplicacion.Mensajeria.Emisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class GerenteController {

    @Autowired
    Emisor emisor;

    @PatchMapping(path = "/logIn")
    public ResponseEntity cambiarEstado(@RequestBody String nomUsuario, @RequestBody String password) throws Exception {
        String dto = "{\"nomUsuario\":" + nomUsuario + ",\"password\":" + password + "}";
        String json = "nombrefuncion," + dto;
        emisor.enviarMensaje(json);
        emisor.recibirMensaje();
        // Hay que cambiar esta intruccion para que devuelva el DTO
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ReservaDTO.class);
    }
}
