package com.group2.employeeManagementSystem.Repository;
import com.group2.employeeManagementSystem.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@SuppressWarnings("unused")
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    boolean existsByEmail(String email);

    List<Employee> findByDepartment(String department);

    List<Employee> findByDepartmentAndActiveTrue(String department);

    @Query("SELECT COUNT(e) FROM Employee e WHERE e.department = :department AND e.active = true")
    long countByDepartmentAndActiveTrue(@Param("department") String department);

    @Query("SELECT COALESCE(SUM(e.salary), 0) FROM Employee e WHERE e.department = :department AND e.active = true")
    long sumSalaryByDepartmentAndActiveTrue(@Param("department") String department);

    @Query("SELECT DISTINCT e.department FROM Employee e WHERE e.active = true")
    List<String> findAllDistinctDepartments();

    long countByActiveTrue();
}