package org.chat.investpro;

import com.opencsv.CSVWriter;
import org.junit.jupiter.api.*;
import org.chat.investpro.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CsvReaderTest {

    private CsvReader csvReader;

    @BeforeEach
    void setUp() {
        csvReader = new CsvReader();
    }

    @Test
    void testReadFromCSV_validFile() throws IOException {
        String content = "name1, 100.0, 2.0, 50.0";
        Path tempFile = createCSVFile("crypto.csv", content);

        ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("crypto");

        assertNotNull(result);
        // Add more assertions here based on the expected structure of IinvesteeringsVorm
        // For example, assuming IinvesteeringsVorm has a getName method:
        assertEquals(1, result.size());
        assertEquals("name1", result.get(0).getNaam());

        closeAndDeleteFile(tempFile);
    }

    @Test
    void testReadFromCSV_invalidData() throws IOException {
        String content = "name1,invalid,2.0,50.0";
        Path tempFile = createCSVFile("aandeel.csv", content);

        ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("aandeel");

        assertEquals(0, result.size());

        closeAndDeleteFile(tempFile);
    }

    @Test
    void testReadFromCSV_invalidFile() {
        ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("nonexistent_file");

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    private Path createCSVFile(String fileName, String content) throws IOException {
        Path path = Paths.get(fileName);
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, true))) {
            String[] data = content.split(", ");
            csvWriter.writeNext(data);
        }
        return path;
    }

    private void closeAndDeleteFile(Path path) throws IOException {
        Files.delete(path);
    }
}
