package org.example.mq.rpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
public class ServerRPC {

    @RabbitListener(queues = "${mq.rpc.queuename}")
    public int fibonacci(int n) {
        int sum = 0;
        for(int i=1; i<n; i++) {
            sum += i;
        }
        log.info("Đã nhận được n của queue RPC: " + sum);

        return sum;
    }
}
