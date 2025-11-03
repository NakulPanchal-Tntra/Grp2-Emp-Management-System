package com.group2.employeeManagementSystem.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
//import jakarta.validation.constraints.Email;

@Entity
@Table(name = "employeeDB")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, max = 50, message = "The characters must be between 2 to 50")
    @NotNull(message = "The name must not null")
    @NotBlank(message = "The name must not blank")
    private String name;

    @Email
    @NotNull(message = "The email must not null")
    @NotBlank(message = "The email must not blank")
    private String email;

    @Size(min = 2, max = 50, message = "The characters must be between 2 to 50")
    @NotNull(message = "The designation must not null")
    @NotBlank(message = "The designation must not blank")
    private String designation;

    @Size(min = 2, max = 50, message = "The characters must be between 2 to 50")
    @NotNull(message = "The department must not null")
    @NotBlank(message = "The department must not blank")
    private String department;

    @NotNull(message = "The salary must not null")
    @NotBlank(message = "The salary must not blank")
    private int salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
