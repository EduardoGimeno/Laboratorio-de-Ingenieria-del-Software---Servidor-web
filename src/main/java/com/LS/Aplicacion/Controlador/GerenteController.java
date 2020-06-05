package com.LS.Aplicacion.Controlador;

import com.LS.Aplicacion.Mensajeria.Emisor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class GerenteController {

    @Autowired
    Emisor emisor = new Emisor();

    public GerenteController() throws Exception {
    }

    @PatchMapping(path = "/signIn")
    public ResponseEntity<Object> logIn(@RequestBody String nomUsuario, @RequestBody String password) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nomUsuario", nomUsuario);
        jsonObject.put("password", password);
        String json = "logInGerente," + jsonObject.toString();
        emisor.enviarMensaje(json);
        String response = emisor.recibirMensaje();
        if (response.equals("ERROR")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(JSONObject.stringToValue(response));
        }
    }
}
