package com.employee.notification;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(String message) {
        System.out.println("RabbitMQ RECEIVED: " + message);
    }
}
