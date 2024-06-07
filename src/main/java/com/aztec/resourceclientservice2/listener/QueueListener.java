package com.aztec.resourceclientservice2.listener;

import com.aztec.resourceclientservice2.service.RabbitService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueListener {

    @Autowired
    private RabbitService rabbitService;
    @RabbitListener(queues = "q.queue1")
    public void receiveMessage(String message) {
        System.out.println("Message received: " + message);
        // Send acknowledgment or process completion message to another queue
        String completionMessage = "Processing completed";
        try {
            rabbitService.sendMessage("q.queue2", completionMessage);
        }catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Completion message sent: " + completionMessage);
    }
}
