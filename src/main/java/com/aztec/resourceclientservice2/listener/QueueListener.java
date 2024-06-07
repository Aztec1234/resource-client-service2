package com.aztec.resourceclientservice2.listener;

import com.aztec.dto.RabbitDTO;
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
    public void receiveMessage(RabbitDTO rabbitDTO) {
        System.out.println("Message received: " + rabbitDTO.getMessage());
        // Send acknowledgment or process completion message to another queue
        String completionMessage = "Processing completed";
        try {
            RabbitDTO sendAck = RabbitDTO.builder()
                    .id("2")
                    .message("Acknowledged")
                    .statusCode("0")
                    .build();
            rabbitService.sendMessage("q.queue2", sendAck);
        }catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Completion message sent: " + completionMessage);
    }
}
