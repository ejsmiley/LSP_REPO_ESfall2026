/**
 * ETLPipeline - Assignment3
 * Same as assignment two but following object-oriented programming
 * Evan Smiley 004002998
 */

package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ETLPipeline {

    private static final String INPUT_PATH = "data/employees.csv";
    private static final String OUTPUT_PATH = "data/transformed_employees.csv";
    private static final String HEADER = "EmployeeID,Name,Department,HoursWorked,HourlyRate,GrossPay,PayLevel,EmploymentStatus";
    
    public static void main(String[] args) {
        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;

        EmployeeParser parser = new EmployeeParser();
        EmployeeTransformer transformer = new EmployeeTransformer();

        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_PATH));
        CsvWriter writer = new CsvWriter(OUTPUT_PATH)) {
            writer.writeHeader(HEADER);

            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                rowsRead++;

                Employee employee = parser.parse(line);
                if (employee == null) {
                    rowsSkipped++;
                } else {
                    writer.writeLine(transformer.toCsvLine(employee));
                    rowsTransformed++;
                }
            }
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            return;
        }

        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output file: " + OUTPUT_PATH);
    }
}
