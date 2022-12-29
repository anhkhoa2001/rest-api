package org.example.mq.worker;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.Date;

@RabbitListener(queues = "${mq.worker.queuename}")
@Slf4j
public class ServerWorker {
    @RabbitHandler
    public void receive(Integer number) {
        log.info("Đã nhân được number vào lúc " + new Date() + " có giá trị: " + number);
    }
}
