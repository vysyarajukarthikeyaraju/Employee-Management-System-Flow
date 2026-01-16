package com.employee.notification;

import java.io.Serializable;

public class NotificationPayload implements Serializable {

    private String type;
    private Long employeeId;
    private String message;

    public NotificationPayload() {
    }

    public NotificationPayload(String type, Long employeeId, String message) {
        this.type = type;
        this.employeeId = employeeId;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
