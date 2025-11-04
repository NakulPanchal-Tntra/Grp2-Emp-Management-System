package com.group2.employeeManagementSystem.Model;

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

    // Getters and Setters
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(long employeeCount) {
        this.employeeCount = employeeCount;
    }

    public long getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(long totalSalary) {
        this.totalSalary = totalSalary;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
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