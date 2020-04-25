package com.LS.Aplicacion.Controlador;

import com.LS.Aplicacion.Mensajeria.Emisor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class GerenteController {

    @Autowired
    Emisor emisor;

    @PatchMapping(path = "/signIn")
    public ResponseEntity signIn(@RequestBody String nomUsuario, @RequestBody String password) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("nomUsuario", nomUsuario);
        jsonObject.append("password", password);
        String json = "nombrefuncion," + jsonObject.toString();
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
        else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(JSONObject.stringToValue(response));
            //return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.readValue(response, GerenteDTO.class));
        }
    }
}
