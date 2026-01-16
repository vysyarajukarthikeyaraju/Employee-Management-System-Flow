package com.employee.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.employee.dto.LeaveApplyDTO;
import com.employee.dto.LeaveStatusUpdateDTO;
import com.employee.model.LeaveRequest;
import com.employee.service.LeaveService;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private static final Logger log = LoggerFactory.getLogger(LeaveController.class);

    @Autowired
    private LeaveService leaveService;

    // APPLY FOR LEAVE
    @PostMapping
    public LeaveRequest applyLeave(
            @Valid @RequestBody LeaveApplyDTO dto) {

        log.info("Apply leave request received for employeeId={}", dto.getEmployeeId());
        return leaveService.applyLeave(dto);
    }

    // APPROVE / REJECT LEAVE
    @PutMapping("/{id}/status")
    public LeaveRequest updateLeaveStatus(
            @PathVariable Long id,
            @Valid @RequestBody LeaveStatusUpdateDTO dto) {

        log.info("Update leave status request for leaveId={}, status={}", id, dto.getStatus());
        return leaveService.updateStatus(id, dto);
    }

    // GET LEAVES BY EMPLOYEE
    @GetMapping("/employee/{empId}")
    public List<LeaveRequest> getLeavesByEmployee(
            @PathVariable Long empId) {

        log.info("Fetching leaves for employeeId={}", empId);
        return leaveService.getByEmployee(empId);
    }
}
