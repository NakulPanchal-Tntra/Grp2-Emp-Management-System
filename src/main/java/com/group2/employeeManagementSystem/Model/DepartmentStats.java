package com.group2.employeeManagementSystem.Model;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@SuppressWarnings("unused")
public class DepartmentStats {
    private String departmentName;
    private long employeeCount;
    private long totalSalary;
    private double averageSalary;

    public DepartmentStats() {
    }

    public DepartmentStats(String departmentName, long employeeCount, long totalSalary) {
        this.departmentName = departmentName;
        this.employeeCount = employeeCount;
        this.totalSalary = totalSalary;
        this.averageSalary = employeeCount > 0 ? (double) totalSalary / employeeCount : 0;
    }

    @Override
    public String toString() {
        return "DepartmentStats{" +
                "departmentName='" + departmentName + '\'' +
                ", employeeCount=" + employeeCount +
                ", totalSalary=" + totalSalary +
                ", averageSalary=" + averageSalary +
                '}';
    }
}