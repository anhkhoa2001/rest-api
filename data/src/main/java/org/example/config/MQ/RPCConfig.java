package org.example.config.MQ;

import com.rabbitmq.client.impl.AMQImpl;
import org.example.mq.rpc.ClientRPC;
import org.example.mq.rpc.ServerRPC;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RPCConfig {

    @Value("${mq.rpc.queuename}")
    private String queueName;

    @Value("${mq.rpc.xchange}")
    private String xchangeName;

    @Value("${mq.rpc.key}")
    private String routingKey;

    @Bean
    public Queue queueRPC() {
        return new Queue(queueName);
    }

    //có thêm key để xác định hàng đợi
    //tốc độ hơn fanout vì định tuyến được hàng đợi
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(xchangeName);
    }

    @Bean
    public Binding binding(DirectExchange exchange, Queue queueRPC) {
        return BindingBuilder.bind(queueRPC).to(exchange).with(routingKey);
    }

    @Bean
    public ClientRPC clientRPC() {
        return new ClientRPC();
    }

    @Bean
    public ServerRPC serverRPC() {
        return new ServerRPC();
    }
}
