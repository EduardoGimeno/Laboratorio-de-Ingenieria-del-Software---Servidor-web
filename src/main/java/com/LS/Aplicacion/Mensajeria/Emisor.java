package com.LS.Aplicacion.Mensajeria;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public void enviarMensaje() throws Exception {
        String mensaje = "Hola mundo!";
        canal.basicPublish("", COLA_ENTRADA, null, mensaje.getBytes());
        log.info(" [x] Enviado '" + mensaje + "'");
    }
    public void recibirMensaje() throws Exception {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String mensaje = new String(delivery.getBody(), "UTF-8");
            log.info(" [x] Recibido: '"+ mensaje + "'");
        };
        canal.basicConsume(COLA_SALIDA, true, deliverCallback, consumerTag -> {});
        log.info("Esperando mensajes...");

    }
}
