package com.aztec.resourceclientservice2.service;

import com.aztec.object.QueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitService {

    private final RabbitAdmin rabbitAdmin;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitService(RabbitAdmin rabbitAdmin, RabbitTemplate rabbitTemplate) {
        this.rabbitAdmin = rabbitAdmin;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(QueueConfig queueConfig) {
        try {
            rabbitTemplate.convertAndSend(queueConfig.getExchangeName(), queueConfig.getRoutingKey(), queueConfig.getRabbitDTO());
            log.info("Sent message to RabbitMQ exchangeName: {}", queueConfig.getExchangeName());
        } catch (Exception ex) {
            log.error("Error occurred due to - {}", ex.getMessage());
        }
    }

    private boolean isQueueExists(String queueName) {
        return rabbitAdmin.getQueueProperties(queueName) != null;
    }

}

