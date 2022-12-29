package org.example.mq.ps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class Client {

    @Autowired
    private FanoutExchange exchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(final Integer number) {
        rabbitTemplate.convertAndSend(exchange.getName(), "", number);

        log.info("Đã gửi: " + number);
    }
}
