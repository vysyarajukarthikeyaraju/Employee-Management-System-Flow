package com.employee.service;

import java.util.List;

import com.employee.dto.DepartmentDTO;
import com.employee.model.Department;
import com.employee.model.Employee;

public interface DepartmentService {

    Department create(DepartmentDTO dto);

    List<Department> getAll();

    List<Employee> getEmployees(Long departmentId);
}
