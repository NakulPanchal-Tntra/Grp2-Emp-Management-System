package com.group2.employeeManagementSystem.Service.impl;


import com.group2.employeeManagementSystem.Model.Employee;
import com.group2.employeeManagementSystem.Repository.EmployeeRepository;
import com.group2.employeeManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeByID(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEmployee(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            boolean activeStatus = employee.isActive();
            employee.setActive(!activeStatus); // mark inactive instead of delete
            employeeRepository.save(employee);
        } else {
            throw new RuntimeException("Employee not found with ID: " + id);
        }
    }

    @Override
    public String updateEmployee(Integer id, Employee employee) {
        Optional<Employee> emp = employeeRepository.findById(id);

        if (emp.isPresent()) {
            Employee empData = emp.get();
            if (!employee.getName().isEmpty()) {
                empData.setName(employee.getName());
            }
            if (!employee.getDepartment().isEmpty()) {
                empData.setName(employee.getDepartment());
            }
            if (!employee.getDepartment().isEmpty()) {
                empData.setName(employee.getDepartment());
            } if (!employee.getDesignation().isEmpty()) {
                empData.setName(employee.getDesignation());
            }
            empData.setSalary(employee.getSalary());
            employeeRepository.save(empData);
            return "Employee with id:" + id + " updated";

        } else {
            throw new RuntimeException("Employee not found");
        }
    }


}
