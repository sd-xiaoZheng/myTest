package com.test.iot;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * mqtt发布者
 */
public class MqttPublisher {
    public static void main(String[] args) {
        String broker = "tcp://127.0.0.1:1883";
        String topic = "ddd";
        String content = "{\"userNmae\":1}";
        int qos = 2;
        String clientId = "JavaPublisher";

        try {
            MqttClient client = new MqttClient(broker, clientId);
            client.connect();
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            client.publish(topic, message);
            client.disconnect();
            System.out.println("Message published");
            client.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
