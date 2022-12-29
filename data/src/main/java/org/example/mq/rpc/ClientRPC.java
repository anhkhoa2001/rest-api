package org.example.mq.rpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class ClientRPC {

    @Value("${mq.rpc.key}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    public void send(int n) {
        int response = (int) rabbitTemplate.convertSendAndReceive(directExchange.getName(), routingKey, n);

        log.info("Kết quả tính tổng số từ 1 đến " + n + " là: " + response);
    }
}
