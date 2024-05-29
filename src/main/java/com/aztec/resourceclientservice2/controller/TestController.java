package com.aztec.resourceclientservice2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String testController(){
        System.out.println("Hello from Resource Server");
        return "Hello from Resource Server 2";
    }
}
