package org.example.mq.worker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;

@Slf4j
public class ClientWorker {


    @Autowired
    @Qualifier("queueWorker")
    private Queue queue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(final Integer number) {
        rabbitTemplate.convertAndSend(queue.getName(), number);
        log.info("Đã gửi number vào lúc "+ new Date() +" == : " + number);
    }
}
