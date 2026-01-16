package com.employee.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dao.DepartmentRepository;
import com.employee.dao.EmployeeRepository;
import com.employee.dto.EmployeeRequestDTO;
import com.employee.exception.ResourceNotFoundException;
import com.employee.model.Department;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import com.employee.service.NotificationService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private NotificationService notificationService;

    // ================= CREATE EMPLOYEE =================
    @Override
    public Employee create(EmployeeRequestDTO dto) {

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id: " + dto.getDepartmentId()));

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setSalary(dto.getSalary());
        employee.setJoiningDate(dto.getJoiningDate());
        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);

        // Notification
        notificationService.sendEmployeeCreated(savedEmployee);

        return savedEmployee;
    }

    // ================= GET ALL =================
    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    // ================= GET BY ID =================
    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id));
    }

    // ================= UPDATE =================
    @Override
    public Employee update(Long id, EmployeeRequestDTO dto) {

        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id));

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id: " + dto.getDepartmentId()));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setSalary(dto.getSalary());
        existing.setJoiningDate(dto.getJoiningDate());
        existing.setDepartment(department);

        return employeeRepository.save(existing);
    }

    // ================= DELETE =================
    @Override
    public void delete(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id));

        employeeRepository.delete(employee);
    }
}
