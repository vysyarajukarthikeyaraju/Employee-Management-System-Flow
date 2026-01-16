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

import com.employee.dao.DepartmentRepository;
import com.employee.dao.EmployeeRepository;
import com.employee.dto.EmployeeRequestDTO;
import com.employee.model.Department;
import com.employee.model.Employee;
import com.employee.service.NotificationService;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void createEmployee_success() {

        EmployeeRequestDTO dto = new EmployeeRequestDTO();
        dto.setName("Ramesh");
        dto.setEmail("ramesh@gmail.com");
        dto.setSalary(45000.0);
        dto.setJoiningDate(LocalDate.now());
        dto.setDepartmentId(1L);

        Department dept = new Department();
        dept.setId(1L);
        dept.setName("IT");

        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(dept));

        when(employeeRepository.save(any(Employee.class)))
                .thenAnswer(i -> i.getArgument(0));

        Employee result = employeeService.create(dto);

        assertNotNull(result);
        assertEquals("Ramesh", result.getName());

        verify(notificationService, times(1))
                .sendEmployeeCreated(any(Employee.class));
    }

    @Test
    void getEmployee_notFound() {

        when(employeeRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> employeeService.getById(99L));
    }
}
