package com.LS.Aplicacion.Controlador;

import com.LS.Aplicacion.Mensajeria.Emisor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("GerenteController")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
@RequestMapping(path = "/gerente")
public class GerenteController {

    @Autowired
    Emisor emisor = new Emisor();

    public GerenteController() throws Exception {
    }

    @GetMapping(path = "/signIn")
    public ResponseEntity<Object> logIn(String nomUsuario, String password) throws Exception {
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
