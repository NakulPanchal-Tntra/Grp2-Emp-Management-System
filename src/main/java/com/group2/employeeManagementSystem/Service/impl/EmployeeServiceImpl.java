package com.group2.employeeManagementSystem.Service.impl;

import com.group2.employeeManagementSystem.Exception.DepartmentNotFoundException;
import com.group2.employeeManagementSystem.Exception.DuplicateEmailException;
import com.group2.employeeManagementSystem.Exception.EmployeeNotFoundException;
import com.group2.employeeManagementSystem.Exception.InvalidEmployeeDataException;
import com.group2.employeeManagementSystem.Model.DepartmentStats;
import com.group2.employeeManagementSystem.Model.Employee;
import com.group2.employeeManagementSystem.Repository.EmployeeRepository;
import com.group2.employeeManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee createEmployee(Employee employee) {
        try {
            String name = employee.getName();

            if (name == null || name.isBlank()) {
                throw new InvalidEmployeeDataException("Name cannot be null or blank for email generation");
            }

            String[] parts = name.trim().split("\\s+");
            String firstName = parts[0].toLowerCase();
            String lastName = (parts.length > 1) ? parts[1].toLowerCase() : "";
            String generatedEmail = firstName + (lastName.isEmpty() ? "" : "." + lastName) + "@gmail.com";

            // Check if email already exists
            if (employeeRepository.existsByEmail(generatedEmail)) {
                throw new DuplicateEmailException("Email " + generatedEmail + " already exists in the system");
            }

            employee.setEmail(generatedEmail);

            return employeeRepository.save(employee);
        } catch (DuplicateEmailException | InvalidEmployeeDataException e) {
            throw e;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException("Email already exists in the system", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create employee: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve employees: " + e.getMessage(), e);
        }
    }

    @Override
    public Employee getEmployeeByID(int id) {
        try {
            return employeeRepository.findById(id)
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
        } catch (EmployeeNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve employee with ID " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteEmployee(Integer id) {
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(id);

            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                boolean activeStatus = employee.isActive();
                employee.setActive(!activeStatus);
                employeeRepository.save(employee);
            } else {
                throw new EmployeeNotFoundException("Employee not found with ID: " + id);
            }
        } catch (EmployeeNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete employee with ID " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String updateEmployee(Integer id, Employee employee) {
        try {
            Optional<Employee> emp = employeeRepository.findById(id);

            if (emp.isPresent()) {
                Employee empData = emp.get();
                empData.setSalary(employee.getSalary());
                employeeRepository.save(empData);
                return "Employee with id:" + id + " updated";

            } else {
                throw new EmployeeNotFoundException("Employee not found with ID: " + id);
            }
        } catch (EmployeeNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update employee with ID " + id + ": " + e.getMessage(), e);
        }
    }

    // NEW METHODS FOR DEPARTMENT STATISTICS

    @Override
    public List<DepartmentStats> getDepartmentStatistics() {
        try {
            List<String> departments = employeeRepository.findAllDistinctDepartments();
            List<DepartmentStats> statsList = new ArrayList<>();

            for (String department : departments) {
                long employeeCount = employeeRepository.countByDepartmentAndActiveTrue(department);
                long totalSalary = employeeRepository.sumSalaryByDepartmentAndActiveTrue(department);
                statsList.add(new DepartmentStats(department, employeeCount, totalSalary));
            }

            return statsList;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve department statistics: " + e.getMessage(), e);
        }
    }

    @Override
    public DepartmentStats getDepartmentStatsByName(String departmentName) {
        try {
            if (departmentName == null || departmentName.isBlank()) {
                throw new InvalidEmployeeDataException("Department name cannot be null or blank");
            }

            List<String> existingDepartments = employeeRepository.findAllDistinctDepartments();
            boolean departmentExists = existingDepartments.stream()
                    .anyMatch(dept -> dept.equalsIgnoreCase(departmentName));

            if (!departmentExists) {
                throw new DepartmentNotFoundException("Department '" + departmentName + "' not found");
            }

            long employeeCount = employeeRepository.countByDepartmentAndActiveTrue(departmentName);
            long totalSalary = employeeRepository.sumSalaryByDepartmentAndActiveTrue(departmentName);

            return new DepartmentStats(departmentName, employeeCount, totalSalary);
        } catch (DepartmentNotFoundException | InvalidEmployeeDataException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve statistics for department '" + departmentName + "': " + e.getMessage(), e);
        }
    }

    @Override
    public long getEmployeeCountByDepartment(String departmentName) {
        try {
            if (departmentName == null || departmentName.isBlank()) {
                throw new InvalidEmployeeDataException("Department name cannot be null or blank");
            }

            List<String> existingDepartments = employeeRepository.findAllDistinctDepartments();
            boolean departmentExists = existingDepartments.stream()
                    .anyMatch(dept -> dept.equalsIgnoreCase(departmentName));

            if (!departmentExists) {
                throw new DepartmentNotFoundException("Department '" + departmentName + "' not found");
            }

            return employeeRepository.countByDepartmentAndActiveTrue(departmentName);
        } catch (DepartmentNotFoundException | InvalidEmployeeDataException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to count employees in department '" + departmentName + "': " + e.getMessage(), e);
        }
    }

    @Override
    public long getTotalSalaryByDepartment(String departmentName) {
        try {
            if (departmentName == null || departmentName.isBlank()) {
                throw new InvalidEmployeeDataException("Department name cannot be null or blank");
            }

            List<String> existingDepartments = employeeRepository.findAllDistinctDepartments();
            boolean departmentExists = existingDepartments.stream()
                    .anyMatch(dept -> dept.equalsIgnoreCase(departmentName));

            if (!departmentExists) {
                throw new DepartmentNotFoundException("Department '" + departmentName + "' not found");
            }

            return employeeRepository.sumSalaryByDepartmentAndActiveTrue(departmentName);
        } catch (DepartmentNotFoundException | InvalidEmployeeDataException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate total salary for department '" + departmentName + "': " + e.getMessage(), e);
        }
    }
}