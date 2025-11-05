package com.group2.employeeManagementSystem.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("unused")
@Setter
@Getter
@Entity
@Table(name = "employees",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_email", columnNames = "email")
        })
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Name must not be null")
    @NotBlank(message = "Name must not be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    private String email;

    @NotNull(message = "Designation must not be null")
    @NotBlank(message = "Designation must not be blank")
    @Size(min = 2, max = 50, message = "Designation must be between 2 and 50 characters")
    private String designation;

    @NotNull(message = "Department must not be null")
    @NotBlank(message = "Department must not be blank")
    @Size(min = 2, max = 50, message = "Department must be between 2 and 50 characters")
    private String department;

    @NotNull(message = "Salary must not be null")
    @Min(value = 1000, message = "Salary must be at least 1000")
    private Integer salary;

    private boolean active = true;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", designation='" + designation + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", active=" + active +
                '}';
    }
}

