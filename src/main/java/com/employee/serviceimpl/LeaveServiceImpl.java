package com.employee.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dao.EmployeeRepository;
import com.employee.dao.LeaveRepository;
import com.employee.dto.LeaveApplyDTO;
import com.employee.dto.LeaveStatusUpdateDTO;
import com.employee.model.Employee;
import com.employee.model.LeaveRequest;
import com.employee.service.LeaveService;
import com.employee.service.NotificationService;

@Service
public class LeaveServiceImpl implements LeaveService {

    private static final Logger log = LoggerFactory.getLogger(LeaveServiceImpl.class);

    @Autowired
    private LeaveRepository leaveRepo;

    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private NotificationService notificationService;

    @Override
    public LeaveRequest applyLeave(LeaveApplyDTO dto) {

        log.info("Applying leave for employeeId={}", dto.getEmployeeId());

        Employee emp = empRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        LeaveRequest leave = new LeaveRequest();
        leave.setEmployee(emp);
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setReason(dto.getReason());
        leave.setStatus("PENDING");

        LeaveRequest saved = leaveRepo.save(leave);

        notificationService.sendLeaveApplied(saved);
        log.info("Leave applied notification sent for leaveId={}", saved.getId());

        return saved;
    }

    @Override
    public LeaveRequest updateStatus(Long id, LeaveStatusUpdateDTO dto) {

        log.info("Updating leave status for leaveId={} to {}", id, dto.getStatus());

        LeaveRequest leave = leaveRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus(dto.getStatus());

        LeaveRequest saved = leaveRepo.save(leave);

        notificationService.sendLeaveStatus(saved);
        log.info("Leave status notification sent for leaveId={}", id);

        return saved;
    }

    @Override
    public List<LeaveRequest> getByEmployee(Long empId) {

        log.info("Fetching leave records for employeeId={}", empId);
        return leaveRepo.findByEmployeeId(empId);
    }
}
