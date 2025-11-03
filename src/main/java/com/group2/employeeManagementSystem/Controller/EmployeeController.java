package com.group2.employeeManagementSystem.Controller;

import com.group2.employeeManagementSystem.Model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @PostMapping("/employees")
    public String createEmployee(@RequestBody Employee employee) {
        return "Employee created successfully: " + employee.getName();
    }

    @PostMapping("/employees/bulk")
    public String createMultipleEmployees(@RequestBody List<Employee> employees) {
        return employees.size() + " employees created successfully.";
    }

    @GetMapping("/getAll")
    public String getAllEmployee(){
        return "list of employee";
    }

    @GetMapping("/getEmp/{id}")
    public String getEmployeeByID(@PathVariable int id){
        return "details of employee" + id;
    }

    @DeleteMapping("/deleteEmp/{id}")
    public String deleteEmployee(@PathVariable int id){
        return "Employee deleted successfully" + id;
    }
}