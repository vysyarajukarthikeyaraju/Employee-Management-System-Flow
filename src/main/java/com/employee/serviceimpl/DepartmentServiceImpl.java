package com.employee.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dao.DepartmentRepository;
import com.employee.dao.EmployeeRepository;
import com.employee.dto.DepartmentDTO;
import com.employee.model.Department;
import com.employee.model.Employee;
import com.employee.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Department create(DepartmentDTO dto) {

        log.info("Creating department: {}", dto.getName());

        Department department = new Department();
        department.setName(dto.getName());
        department.setLocation(dto.getLocation());

        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAll() {

        log.info("Fetching all departments from DB");
        return departmentRepository.findAll();
    }

    @Override
    public List<Employee> getEmployees(Long departmentId) {

        log.info("Fetching employees for departmentId={}", departmentId);
        return employeeRepository.findByDepartmentId(departmentId);
    }
}
