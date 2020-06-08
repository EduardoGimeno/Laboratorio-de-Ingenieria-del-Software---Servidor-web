package com.LS.Aplicacion.Mensajeria;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class Emisor {

    private final Logger log = LoggerFactory.getLogger(Emisor.class);

    private final static String COLA_ENTRADA = "entrada";
    private final static String COLA_SALIDA = "salida";
    private final static String ENV_AMQPURL_NAME = "CLOUDAMQP_URL";
    private final Channel canal;

    public Emisor() throws Exception {
        ConnectionFactory factoria = new ConnectionFactory();
        String amqpURL = System.getenv(ENV_AMQPURL_NAME) != null ?
                System.getenv().get(ENV_AMQPURL_NAME) : "amqp://localhost";
        try {
            factoria.setUri(amqpURL);
        } catch (Exception e) {
            System.out.println(" [*] AQMP broker no encontrado en " + amqpURL);
            System.exit(-1);
        }
        Connection conexion = factoria.newConnection();
        canal = conexion.createChannel();
        canal.queueDeclare(COLA_ENTRADA, false, false, false, null);
        canal.queueDeclare(COLA_SALIDA, false, false, false, null);
    }

    public void enviarMensaje(String mensaje) throws Exception {
        canal.basicPublish("", COLA_ENTRADA, null, mensaje.getBytes());
        //log.info(" [x] Enviado '" + mensaje + "'");
    }

    public String recibirMensaje() throws Exception {
        boolean autoAck = false;
        GetResponse response = canal.basicGet(COLA_SALIDA, autoAck);
        while (response == null) {
            response = canal.basicGet(COLA_SALIDA, autoAck);
        }
        String mensaje = new String(response.getBody(), StandardCharsets.UTF_8);
        //log.info(" [x] Recibido '" + mensaje + "'");
        canal.basicAck(response.getEnvelope().getDeliveryTag(), false);
        return mensaje;
    }
}
