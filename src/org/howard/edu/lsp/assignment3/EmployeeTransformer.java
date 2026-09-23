/**
 * Formats validated Employee into a single csv output lin
 * with formatting rules
 * Evan Smiley 004002998
 */

package org.howard.edu.lsp.assignment3;

public class EmployeeTransformer {
    public String toCsvLine(Employee employee) {
        return String.format("%d, %s, %s, %.2f, %.2f, %.2f, %s, %s",
            employee.getEmployeeId(),
            employee.getName(),
            employee.getDepartment(),
            employee.getHoursWorked(),
            employee.getHourlyRate(),
            employee.getGrossPay(),
            employee.getPayLevel(),
            employee.getEmploymentStatus());
    }
}
