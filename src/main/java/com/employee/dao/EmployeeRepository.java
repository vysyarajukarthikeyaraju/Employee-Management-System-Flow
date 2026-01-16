package com.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentId(Long departmentId);
}
