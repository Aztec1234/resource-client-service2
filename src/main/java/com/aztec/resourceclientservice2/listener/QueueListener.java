package com.aztec.resourceclientservice2.listener;

import com.aztec.constants.RabbitConstants;
import com.aztec.dto.RabbitDTO;
import com.aztec.object.QueueConfig;
import com.aztec.resourceclientservice2.service.RabbitService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueListener {

    @Autowired
    private RabbitService rabbitService;
    @RabbitListener(queues = RabbitConstants.QUEUE_1)
    public void receiveMessage(RabbitDTO rabbitDTO) {
        System.out.println("Message received: " + rabbitDTO.getMessage());
        // Send acknowledgment or process completion message to another queue
        String completionMessage = "Processing completed";
        RabbitDTO ack = RabbitDTO.builder()
                .id("1")
                .message(completionMessage)
                .statusCode("0")
                .build();
        QueueConfig queueConfig = QueueConfig.builder()
                .exchangeName(RabbitConstants.EX_2)
                .routingKey(RabbitConstants.RK_2)
                .rabbitDTO(ack)
                .build();
        rabbitService.sendMessage(queueConfig);
    }
}
