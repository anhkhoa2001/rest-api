package org.example.controller;

import org.example.mq.ps.Client;
import org.example.mq.rpc.ClientRPC;
import org.example.mq.worker.ClientWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo")
public class DemoMQController {

    @Autowired
    private ClientRPC clientRPC;

    @Autowired
    private Client client;

    @Autowired
    private ClientWorker clientWorker;


    @GetMapping("/rpc/{number}")
    public void testRPC(@PathVariable Integer number) {
        clientRPC.send(number);
    }

    @GetMapping("/ps/{number}")
    public void testPS(@PathVariable Integer number) {
        client.send(number);
    }

    @GetMapping("/worker/{number}")
    public void testWorker(@PathVariable Integer number) {
        clientWorker.send(number);
    }
}
