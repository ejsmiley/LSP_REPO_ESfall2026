/**
 * writes csv rows to a file. closes when done
 */

package org.howard.edu.lsp.assignment3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CsvWriter implements AutoCloseable {

    private final PrintWriter writer;
    
    public CsvWriter(String path) throws IOException {
        this.writer = new PrintWriter(new FileWriter(path));
    }

    public void writeHeader(String header) {
        writer.println(header);
    }

    public void writeLine(String line) {
        writer.println(line);
    }

    @Override
    public void close() {
        writer.close();
    }
}
