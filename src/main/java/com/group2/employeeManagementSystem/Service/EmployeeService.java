package com.group2.employeeManagementSystem.Service;

import com.group2.employeeManagementSystem.Model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployee();
    Employee getEmployeeByID(int id);
    void deleteEmployee(Integer id);
}

