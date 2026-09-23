/**
 * Parses and validates one raw csv line into an employee
 * returns null when row should be skipped
 * Evan Smiley 004002998
 */
package org.howard.edu.lsp.assignment3;

public class EmployeeParser {

    public Employee parse(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] fields = line.split(",", -1);
        if (fields.length != 5) {
            return null;
        }

        String idRaw = fields[0].trim();
        String nameRaw = fields[1].trim().toUpperCase();
        String deptRaw = fields[2].trim();
        String hoursRaw = fields[3].trim();
        String rateRaw = fields[4].trim();

        int employeeId;
        try {
            employeeId = Integer.parseInt(idRaw);
        } catch (NumberFormatException e) {
            return null;
        }

        double hoursWorked;
        double hourlyRate;
        try {
            hoursWorked = Double.parseDouble(hoursRaw);
            hourlyRate = Double.parseDouble(rateRaw);
        } catch (NumberFormatException e) {
            return null;
        }

        if (hoursWorked < 0 || hourlyRate < 0) {
            return null;
        }

        return new Employee(employeeId, nameRaw, deptRaw, hoursWorked, hourlyRate);
    }
}
