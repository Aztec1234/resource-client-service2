package com.aztec.resourceclientservice2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {

    private final RestTemplate restTemplate;

    @Autowired
    public TestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callClient1() {
        String client1Endpoint = "http://localhost:9092/resource-server/test";
        ResponseEntity<String> response = restTemplate.getForEntity(client1Endpoint, String.class);
        return response.getBody();
    }

}
