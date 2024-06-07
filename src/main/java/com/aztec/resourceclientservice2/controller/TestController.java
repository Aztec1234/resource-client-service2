package com.aztec.resourceclientservice2.controller;

import com.aztec.resourceclientservice2.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    public String testController(){
        System.out.println("Hello from Resource Server");
        return "Hello from Resource Server 2";
    }

    @GetMapping("/call-client1")
    public String callClient1() {
        return testService.callClient1();
    }
}
