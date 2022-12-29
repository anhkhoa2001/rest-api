package org.example.config.MQ;

import org.example.mq.worker.ClientWorker;
import org.example.mq.worker.ServerWorker;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkerConfig {

    @Value("${mq.worker.queuename}")
    private String queueName;

    @Bean
    public Queue queueWorker() {
        return new Queue(queueName);
    }

    @Bean
    public ClientWorker clientWorker() {
        return new ClientWorker();
    }

    @Bean
    public ServerWorker serverWorker() {
        return new ServerWorker();
    }
}
