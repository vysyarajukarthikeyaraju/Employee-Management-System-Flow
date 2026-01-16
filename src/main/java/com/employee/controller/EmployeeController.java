package com.employee.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employee.dto.EmployeeRequestDTO;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private static final Logger log =
            LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    // ================= CREATE EMPLOYEE =================
    @PostMapping
    public Employee createEmployee(
            @Valid @RequestBody EmployeeRequestDTO dto) {

        log.info("Create employee request received: {}", dto);
        return employeeService.create(dto);
    }

    // ================= GET ALL EMPLOYEES =================
    @GetMapping
    public List<Employee> getAllEmployees() {

        log.info("Fetching all employees");
        return employeeService.getAll();
    }

    // ================= GET EMPLOYEE BY ID =================
    @GetMapping("/{id}")
    public Employee getEmployeeById(
            @PathVariable Long id) {

        log.info("Fetching employee by id={}", id);
        return employeeService.getById(id);
    }

    // ================= UPDATE EMPLOYEE =================
    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO dto) {

        log.info("Updating employee id={} with data={}", id, dto);
        return employeeService.update(id, dto);
    }

    // ================= DELETE EMPLOYEE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {

        log.info("Deleting employee with id={}", id);

        employeeService.delete(id);
        return ResponseEntity.ok(
            "Employee deleted successfully with id " + id
        );
    }

}
