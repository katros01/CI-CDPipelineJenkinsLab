package com.gtp2.CI.CD_Jenkins.service;

import com.gtp2.CI.CD_Jenkins.entity.Employee;
import com.gtp2.CI.CD_Jenkins.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeService() {
        employees.add(new Employee(1L, "John", "Doe", "john.doe@example.com", "IT"));
        employees.add(new Employee(2L, "Jane", "Smith", "jane.smith@example.com", "HR"));
        employees.add(new Employee(3L, "Tom", "Smith", "tom@example.com", "software development"));
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee getEmployeeById(Long id) throws EmployeeNotFoundException {
        return employees.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
}
