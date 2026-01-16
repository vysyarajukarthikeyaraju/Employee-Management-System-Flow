package com.employee.service;

import java.util.List;

import com.employee.dto.EmployeeRequestDTO;
import com.employee.model.Employee;

public interface EmployeeService {

    Employee create(EmployeeRequestDTO dto);

    List<Employee> getAll();

    Employee getById(Long id);

    Employee update(Long id, EmployeeRequestDTO dto);

    void delete(Long id);
}
