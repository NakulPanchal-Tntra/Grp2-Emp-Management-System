package com.group2.employeeManagementSystem.Controller;

import ch.qos.logback.core.joran.spi.EventPlayer;
import com.group2.employeeManagementSystem.Model.Employee;
import com.group2.employeeManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public Map<String, Object> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.createEmployee(employee);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employee created successfully and email has been added.");
        response.put("employee", savedEmployee);

        return response;
    }

    @PostMapping("/employees/bulk")
    public Map<String, Object> createMultipleEmployees(@RequestBody List<Employee> employees) {
        List<Employee> savedEmployees = employees.stream()
                .map(employeeService::createEmployee)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("message", savedEmployees.size() + " employees created successfully and emails have been added.");
        response.put("employees", savedEmployees);

        return response;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmp(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmpByID(@PathVariable int id){
        return employeeService.getEmployeeByID(id);
    }
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        try {
            employeeService.deleteEmployee(id);
            return "Employee with ID " + id + " has been deleted successfully.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }


}}
