/**
 * Represents an employee payroll record
 * Encapsulates the raw input and derived payroll values
 * Evan Smiley 004002998
 */
package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Employee {
    
    private final int employeeId;
    private final String name;
    private final String department;
    private final double hoursWorked;
    private final double hourlyRate;

    public Employee(int employeeId, String name, String department, double hoursWorked, double hourlyRate) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public int getEmployeeId()  { return employeeId; }
    public String getName()     { return name; }
    public String getDepartment() { return department; }
    public double getHoursWorked() { return hoursWorked; }
    public double getHourlyRate() { return hourlyRate; }

    public double getGrossPay() {
        double pay;
        if (hoursWorked <= 40.00) {
            pay = hoursWorked * hourlyRate;
        } else {
            double regularPay = 40.00 * hourlyRate;
            double overtimePay = (hoursWorked - 40.00) * hourlyRate * 1.5;
            pay = regularPay + overtimePay;
        }

        if ("IT".equals(department)) {
            pay = pay * 1.05;
        }

        BigDecimal rounded = BigDecimal.valueOf(pay).setScale(2, RoundingMode.HALF_UP);
        return rounded.doubleValue();
    }

    public String getPayLevel() {
        double g = getGrossPay();
        if (g < 500.00) return "Low";
        if (g < 1000.00) return "Standard";
        if (g < 2000.00) return "High";
        return "Executive";
    }

    public String getEmploymentStatus() {
        return (hoursWorked < 30.00) ? "Part-Time" : "Full-Time";
    }
}
