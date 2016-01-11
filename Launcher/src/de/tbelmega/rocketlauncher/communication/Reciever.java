package de.tbelmega.rocketlauncher.communication;

import com.rabbitmq.client.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Reciever extends AbstractRabbitMQConnector implements Runnable {

    public Reciever() throws IOException, TimeoutException {
        super();
    }

    public static void main(String[] args) throws Exception {
        Reciever r = new Reciever();
        r.run();
    }

    @Override
    public void run() {
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");


                try {
                    JSONObject content = new JSONObject(message);
                    long time = content.getLong("time");
                    if ((System.currentTimeMillis()/ 1000) - time <= 1){
                        System.out.println("TRIGGER");
                    } else {
                        System.out.println("Message arrived to late, will not shoot.");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        try {
            channel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
