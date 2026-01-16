package com.employee.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.employee.dto.DepartmentDTO;
import com.employee.model.Department;
import com.employee.model.Employee;
import com.employee.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    // CREATE DEPARTMENT
    @PostMapping
    public Department createDepartment(
            @Valid @RequestBody DepartmentDTO dto) {

        log.info("Create department request: {}", dto.getName());
        return departmentService.create(dto);
    }

    // GET ALL DEPARTMENTS
    @GetMapping
    public List<Department> getAllDepartments() {

        log.info("Fetching all departments");
        return departmentService.getAll();
    }

    // GET EMPLOYEES BY DEPARTMENT
    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByDepartment(
            @PathVariable Long id) {

        log.info("Fetching employees for departmentId={}", id);
        return departmentService.getEmployees(id);
    }
}
