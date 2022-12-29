package org.example.mq.ps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
public class Server {

    @RabbitListener(queues = "#{pubQueue1.name}")
    public void receive1(Integer num) throws InterruptedException {
        log.info("Đã nhận được qua pubQueue1: " + num);
    }

    @RabbitListener(queues = "#{pubQueue2.name}")
    public void receive2(Integer num) throws InterruptedException {
        log.info("Đã nhận được qua pubQueue2: " + num);
    }

    @RabbitListener(queues = "#{pubQueue3.name}")
    public void receive3(Integer num) throws InterruptedException {
        log.info("Đã nhận được qua pubQueue3: " + num);
    }
}
