package de.tbelmega.rocketlauncher.communication;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Thiemo on 11.01.2016.
 */
public abstract class AbstractRabbitMQConnector {

    protected final static String QUEUE_NAME = "rocket_launcher";
    protected static Channel channel;

    public AbstractRabbitMQConnector() throws IOException, TimeoutException {
        channel = createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    }


    protected static Channel createChannel() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        return connection.createChannel();
    }
}
