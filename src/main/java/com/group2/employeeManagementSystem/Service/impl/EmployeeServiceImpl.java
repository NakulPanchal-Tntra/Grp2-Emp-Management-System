package com.group2.employeeManagementSystem.Service.impl;


import com.group2.employeeManagementSystem.Model.Employee;
import com.group2.employeeManagementSystem.Repository.EmployeeRepository;
import com.group2.employeeManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {

        String name = employee.getName();


        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank for email generation");
        }


        String[] parts = name.trim().split("\\s+");
        String firstName = parts[0].toLowerCase();
        String lastName = (parts.length > 1) ? parts[1].toLowerCase() : "";
        String generatedEmail = firstName + (lastName.isEmpty() ? "" : "." + lastName) + "@gmail.com";
        employee.setEmail(generatedEmail);

        return employeeRepository.save(employee);
    }
}
