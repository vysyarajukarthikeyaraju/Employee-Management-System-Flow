package com.employee.service;

import com.employee.model.Employee;
import com.employee.model.LeaveRequest;

public interface NotificationService {

    void sendEmployeeCreated(Employee employee);

    void sendLeaveApplied(LeaveRequest leaveRequest);

    void sendLeaveStatus(LeaveRequest leaveRequest);
}
