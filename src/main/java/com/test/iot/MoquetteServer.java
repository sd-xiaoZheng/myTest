package com.test.iot;

import io.moquette.broker.Server;
import io.moquette.broker.config.MemoryConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

/**
 * @author myTest
 * @since 2025/7/17
 **/
@Slf4j
public class MoquetteServer {
    public static void main(String[] args) {
        Server MQTTBroker = new Server();
        Properties properties = new Properties();
        properties.setProperty("port", "1883");
        properties.setProperty("host", "127.0.0.1");
        properties.setProperty("allow_anonymous", "true");
        properties.setProperty("telemetry.enabled", "false");

        MemoryConfig config = new MemoryConfig(properties);
        try {
            MQTTBroker.startServer(config);
            log.info("MQTTBroker started 监听端口1883");

            //阻塞主线程 保持服务运行
            Thread.currentThread().join();
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }finally {
            MQTTBroker.stopServer();
        }
    }
}
