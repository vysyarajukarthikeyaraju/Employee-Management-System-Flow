package com.employee.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.employee.dao.EmployeeRepository;
import com.employee.dao.LeaveRepository;
import com.employee.dto.LeaveApplyDTO;
import com.employee.dto.LeaveStatusUpdateDTO;
import com.employee.model.Employee;
import com.employee.model.LeaveRequest;
import com.employee.service.NotificationService;

@ExtendWith(MockitoExtension.class)
class LeaveServiceImplTest {

    @Mock
    private LeaveRepository leaveRepo;

    @Mock
    private EmployeeRepository empRepo;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private LeaveServiceImpl leaveService;

    @Test
    void applyLeave_success() {

        Employee emp = new Employee();
        emp.setId(1L);

        LeaveApplyDTO dto = new LeaveApplyDTO();
        dto.setEmployeeId(1L);
        dto.setStartDate(LocalDate.now());
        dto.setEndDate(LocalDate.now().plusDays(2));
        dto.setReason("Sick leave");

        when(empRepo.findById(1L))
                .thenReturn(Optional.of(emp));

        when(leaveRepo.save(any(LeaveRequest.class)))
                .thenAnswer(i -> i.getArgument(0));

        LeaveRequest result = leaveService.applyLeave(dto);

        assertEquals("PENDING", result.getStatus());

        verify(notificationService, times(1))
                .sendLeaveApplied(any(LeaveRequest.class));
    }

    @Test
    void updateLeaveStatus_success() {

        LeaveRequest leave = new LeaveRequest();
        leave.setId(10L);
        leave.setStatus("PENDING");

        LeaveStatusUpdateDTO dto = new LeaveStatusUpdateDTO();
        dto.setStatus("APPROVED");

        when(leaveRepo.findById(10L))
                .thenReturn(Optional.of(leave));

        when(leaveRepo.save(any(LeaveRequest.class)))
                .thenReturn(leave);

        LeaveRequest updated = leaveService.updateStatus(10L, dto);

        assertEquals("APPROVED", updated.getStatus());

        verify(notificationService, times(1))
                .sendLeaveStatus(any(LeaveRequest.class));
    }
}
