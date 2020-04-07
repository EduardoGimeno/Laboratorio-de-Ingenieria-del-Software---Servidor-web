package com.LS.Aplicacion.Controlador;

import com.LS.Aplicacion.Entidad.Reserva;
import com.LS.Aplicacion.Mensajeria.Emisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
public class ReservaController {

    @Autowired
    Emisor emisor;

    @RequestMapping(value = "/getReservas", method = RequestMethod.GET)
    public String getAllReservas(Model model) throws Exception {
        emisor.enviarMensaje();
        emisor.recibirMensaje();
        model.addAttribute("reservas", "");
        return "reservas";
    }

    @RequestMapping(value = "/createReserva/{ }", method = RequestMethod.GET, consumes = "application/json")
    public String crear(@RequestBody Reserva reserva, Model model) throws Exception {
        emisor.enviarMensaje();
        emisor.recibirMensaje();
        model.addAttribute("reserva", "");
        return "reserva";
    }

    @RequestMapping(value = "/changeState", method = RequestMethod.GET)
    public String cambiarEstado(@RequestBody UUID id, @RequestBody String estado, @RequestBody String motivo, Model model) throws Exception {
        emisor.enviarMensaje();
        emisor.recibirMensaje();
        model.addAttribute("estado", "");
        return "estado";
    }

    @RequestMapping(value = "/getReservas", method = RequestMethod.GET)
    public String getReservasByEspacio(@RequestBody UUID idEspacio, Model model) throws Exception {
        emisor.enviarMensaje();
        emisor.recibirMensaje();
        model.addAttribute("reservasByEspacio", "");
        return "reservasByEspacio";
    }

    @RequestMapping(value = "/getReservas", method = RequestMethod.GET)
    public String getFilteredReservas(Model model) throws Exception {
        emisor.enviarMensaje();
        emisor.recibirMensaje();
        model.addAttribute("filteredReservas", "");
        return "filteredReservas";
    }


}