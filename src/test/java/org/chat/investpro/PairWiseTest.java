package org.chat.investpro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PairWiseTest {
    private CsvReader reader;

    @BeforeEach
    void setUp() {
        reader = new CsvReader();
    }

    // Helper method to create a CSV file for testing
    private void createCSV(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        }
    }

    @Test
    void testReadFromCSV_Pairwise() throws IOException {

        String csvContent1 = "naam1,10.0,5.0,2.0";
        createCSV("crypto.csv", csvContent1);
        ArrayList<IinvesteeringsVorm> result1 = reader.readFromCSV("crypto");
        assertEquals(1, result1.size());
        closeAndDeleteFile(Path.of("crypto.csv"));

        String csvContent2 = "naam1,10.0,5.0";
        //createCSV("crypto.csv", csvContent2);
        ArrayList<IinvesteeringsVorm> result2 = reader.readFromCSV("crypto");
        assertEquals(0, result2.size());

        String csvContent3 = "naam1,invalid, 5.0, 2.0";
        createCSV("aandeel.csv", csvContent3);
        ArrayList<IinvesteeringsVorm> result3 = reader.readFromCSV("aandeel");
        assertEquals(0, result3.size());

        String csvContent4 = "naam1,10.0,5.0, invalid";
        //createCSV("aandeel.csv", csvContent4);
        ArrayList<IinvesteeringsVorm> result4 = reader.readFromCSV("aandeel");
        assertEquals(0, result4.size());

        String csvContent5 = "naam1,10.0,2.0";
        //createCSV("obligatie.csv", csvContent5);
        ArrayList<IinvesteeringsVorm> result5 = reader.readFromCSV("obligatie");
        assertEquals(0, result5.size());

        String csvContent6 = "naam1,10.0,2.0";
        createCSV("obligatie.csv", csvContent6);
        ArrayList<IinvesteeringsVorm> result6 = reader.readFromCSV("obligatie");
        assertEquals(0, result6.size());

        closeAndDeleteFile(Path.of("aandeel.csv"));
        closeAndDeleteFile(Path.of("obligatie.csv"));


    }

    private void closeAndDeleteFile(Path path) throws IOException {
        Files.delete(path);
    }

}
