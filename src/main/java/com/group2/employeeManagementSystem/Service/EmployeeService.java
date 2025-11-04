package com.group2.employeeManagementSystem.Service;

import com.group2.employeeManagementSystem.Model.DepartmentStats;
import com.group2.employeeManagementSystem.Model.Employee;

import java.util.List;

@SuppressWarnings("unused")
public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployee();
    Employee getEmployeeByID(int id);
    void deleteEmployee(Integer id);
    String updateEmployee(Integer id, Employee employee);

    // New methods for department statistics
    List<DepartmentStats> getDepartmentStatistics();
    DepartmentStats getDepartmentStatsByName(String departmentName);
    long getEmployeeCountByDepartment(String departmentName);
    long getTotalSalaryByDepartment(String departmentName);
}