package com.test.iot;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * mqtt订阅者
 */
public class MqttSubscriber {
    public static void main(String[] args) {
        String broker = "tcp://127.0.0.1:1883";
        String topic = "ddd";
        String clientId = "JavaSubscriber";

        try {
            MqttClient client = new MqttClient(broker, clientId);
            client.connect();
            client.subscribe(topic);
            client.setCallback(new MqttCallback() {
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost!");
                }

                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Message arrived: " + message);
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("Delivery complete");
                }
            });

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
