/**
 * ETLPipeline - Assignment 2
 *
 *Evan Smiley
 */
package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    private static final String INPUT_PATH  = "data/employees.csv";
    private static final String OUTPUT_PATH = "data/transformed_employees.csv";

    public static void main(String[] args) {
        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_PATH));
             PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_PATH))) {

            // Always write the header row
            writer.println("EmployeeID,Name,Department,HoursWorked,HourlyRate,GrossPay,PayLevel,EmploymentStatus");

            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;   // skip header, do not count it
                    continue;
                }

                rowsRead++;  // every non-header line counts, including blank/malformed

                String outputRow = transform(line);
                if (outputRow == null) {
                    rowsSkipped++;
                } else {
                    writer.println(outputRow);
                    rowsTransformed++;
                }
            }

        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            return;
        }

        // Run summary
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output file: " + OUTPUT_PATH);
    }

    private static String transform(String line) {
        // Skip blank lines
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        // Split on comma. A blank line above is caught first, so split here always yields >= 1 part.
        String[] fields = line.split(",", -1);
        if (fields.length != 5) {
            return null;
        }

        // Normalize
        String idRaw     = fields[0].trim();
        String nameRaw   = fields[1].trim().toUpperCase();
        String deptRaw   = fields[2].trim();
        String hoursRaw  = fields[3].trim();
        String rateRaw   = fields[4].trim();

        // Validate numeric values
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
            hourlyRate  = Double.parseDouble(rateRaw);
        } catch (NumberFormatException e) {
            return null;
        }

        if (hoursWorked < 0 || hourlyRate < 0) {
            return null;
        }

        // Base + overtime pay
        double grossPay;
        if (hoursWorked <= 40.00) {
            grossPay = hoursWorked * hourlyRate;
        } else {
            double regularPay = 40.00 * hourlyRate;
            double overtimePay = (hoursWorked - 40.00) * hourlyRate * 1.5;
            grossPay = regularPay + overtimePay;
        }

        // IT bonus: +5% when Department is exactly "IT"
        if (deptRaw.equals("IT")) {
            grossPay = grossPay * 1.05;
        }

        // Round GrossPay to 2 decimals, round-half-up
        BigDecimal grossBD = BigDecimal.valueOf(grossPay).setScale(2, RoundingMode.HALF_UP);
        double grossRounded = grossBD.doubleValue();

        // PayLevel from the final rounded GrossPay
        String payLevel;
        if (grossRounded < 500.00) {
            payLevel = "Low";
        } else if (grossRounded < 1000.00) {
            payLevel = "Standard";
        } else if (grossRounded < 2000.00) {
            payLevel = "High";
        } else {
            payLevel = "Executive";
        }

        // EmploymentStatus
        String status = (hoursWorked < 30.00) ? "Part-Time" : "Full-Time";

        // Build output row. HoursWorked, HourlyRate, GrossPay all formatted to 2 decimals.
        // HourlyRate is formatted for OUTPUT only — the payroll math above used the raw value.
        return String.format("%d,%s,%s,%.2f,%.2f,%.2f,%s,%s",
                employeeId,
                nameRaw,
                deptRaw,
                hoursWorked,
                hourlyRate,
                grossRounded,
                payLevel,
                status);
    }
}
