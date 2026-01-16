package com.employee.notification;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.config.RabbitMQConfig;
import com.employee.model.Employee;
import com.employee.model.LeaveRequest;
import com.employee.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // ================= EMPLOYEE CREATED =================
    @Override
    public void sendEmployeeCreated(Employee employee) {

        String message =
                "EMPLOYEE CREATED | ID=" + employee.getId()
              + ", Name=" + employee.getName()
              + ", Email=" + employee.getEmail();

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                message
        );

        System.out.println("RabbitMQ SENT: " + message);
    }

    // ================= LEAVE APPLIED =================
    @Override
    public void sendLeaveApplied(LeaveRequest leaveRequest) {

        String message =
                "LEAVE APPLIED | Employee="
              + leaveRequest.getEmployee().getName()
              + ", From=" + leaveRequest.getStartDate()
              + ", To=" + leaveRequest.getEndDate();

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                message
        );

        System.out.println("RabbitMQ SENT: " + message);
    }

    // ================= LEAVE STATUS =================
    @Override
    public void sendLeaveStatus(LeaveRequest leaveRequest) {

        String message =
                "LEAVE STATUS | Employee="
              + leaveRequest.getEmployee().getName()
              + ", Status=" + leaveRequest.getStatus();

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                message
        );

        System.out.println("RabbitMQ SENT: " + message);
    }
}
