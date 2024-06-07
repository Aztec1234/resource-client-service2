package com.aztec.resourceclientservice2.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private RabbitService rabbitService;

    public void printProps() {
        rabbitService.sendMessage("q.queue2","Hello from resource server 2 !");
    }
}
