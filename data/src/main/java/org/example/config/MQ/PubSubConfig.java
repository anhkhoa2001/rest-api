package org.example.config.MQ;

import org.example.mq.ps.Client;
import org.example.mq.ps.Server;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PubSubConfig {

    @Value("${mq.ps.xchange}")
    private String xchangeName;

    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange(xchangeName);
    }

    @Bean
    public Queue pubQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue pubQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue pubQueue3() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1(FanoutExchange fanout, Queue pubQueue1) {
        return BindingBuilder.bind(pubQueue1).to(fanout);
    }

    @Bean
    public Binding binding2(FanoutExchange fanout, Queue pubQueue2) {
        return BindingBuilder.bind(pubQueue2).to(fanout);
    }

    @Bean
    public Binding binding3(FanoutExchange fanout, Queue pubQueue3) {
        return BindingBuilder.bind(pubQueue3).to(fanout);
    }

    @Bean
    public Client client() {
        return new Client();
    }

    @Bean
    public Server server() {
        return new Server();
    }

}
