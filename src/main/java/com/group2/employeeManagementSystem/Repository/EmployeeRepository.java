package com.group2.employeeManagementSystem.Repository;

import com.group2.employeeManagementSystem.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    // Check if email already exists
    boolean existsByEmail(String email);

    // Find all employees by department
    List<Employee> findByDepartment(String department);

    // Find all active employees by department
    List<Employee> findByDepartmentAndActiveTrue(String department);

    // Count employees by department (only active)
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.department = :department AND e.active = true")
    long countByDepartmentAndActiveTrue(@Param("department") String department);

    // Get total salary by department (only active employees)
    @Query("SELECT COALESCE(SUM(e.salary), 0) FROM Employee e WHERE e.department = :department AND e.active = true")
    long sumSalaryByDepartmentAndActiveTrue(@Param("department") String department);

    // Get all distinct departments
    @Query("SELECT DISTINCT e.department FROM Employee e WHERE e.active = true")
    List<String> findAllDistinctDepartments();

    // Get total count of all active employees
    long countByActiveTrue();
}