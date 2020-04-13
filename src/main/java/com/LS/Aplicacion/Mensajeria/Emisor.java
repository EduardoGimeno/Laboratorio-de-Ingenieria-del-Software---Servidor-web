package com.LS.Aplicacion.Mensajeria;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class Emisor {

    private Logger log = LoggerFactory.getLogger(Emisor.class);

    private final static String COLA_ENTRADA = "entrada";
    private final static String COLA_SALIDA = "salida";
    private Channel canal;



    public Emisor() throws Exception {
        ConnectionFactory factoria = new ConnectionFactory();
        factoria.setHost("localhost");
        Connection conexion = factoria.newConnection();
        canal = conexion.createChannel();
        canal.queueDeclare(COLA_ENTRADA, false, false, false, null);
        canal.queueDeclare(COLA_SALIDA, false, false, false, null);
    }

    public void enviarMensaje(String mensaje) throws Exception {
        canal.basicPublish("", COLA_ENTRADA, null, mensaje.getBytes());
        log.info(" [x] Enviado '" + mensaje + "'");
        log.info(recibirMensaje());
    }
    public String recibirMensaje() throws Exception {
        boolean autoAck = false;
        GetResponse response = canal.basicGet(COLA_SALIDA, autoAck);
        while (response == null) {
            response = canal.basicGet(COLA_SALIDA, autoAck);
        }
        String mensaje = new String(response.getBody(), StandardCharsets.UTF_8);
        canal.basicAck(response.getEnvelope().getDeliveryTag(), false);
        return mensaje;
    }
}
