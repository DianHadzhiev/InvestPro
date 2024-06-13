package org.chat.investpro;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CsvEditorTest {

    private CsvEditor csvEditor;

    @BeforeEach
    public void setUp() throws Exception {
        csvEditor = new CsvEditor();

        // Create a sample crypto.csv file
        try (FileWriter writer = new FileWriter("crypto.csv")) {
            writer.write("\"Bitcoin\",50000.0,2.0,100000.0\n");
            writer.write("\"Ethereum\",2000.0,5.0,10000.0\n");
            writer.write("\"Dogecoin\",0.5,1000.0,500.0\n");
        }
    }

    @AfterEach
    public void tearDown() {
        new File("crypto.csv").delete();
        new File("temp.csv").delete();
    }


    @Test
    public void testVerwijderKapitaalvorm_ExistingLine() throws Exception {
        // Condition: Deleting a Line that Exists
        csvEditor.verwijderKapitaalvorm(2, "crypto");

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("crypto.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        assertEquals(2, lines.size());
        assertEquals("\"Bitcoin\",50000.0,2.0,100000.0", lines.get(0));
        assertEquals("\"Dogecoin\",0.5,1000.0,500.0", lines.get(1));
    }

    @Test
    public void testVerwijderKapitaalvorm_NonExistingLine() throws Exception {
        // Condition: Deleting a Line that Does Not Exist (Index Out of Bounds)
        csvEditor.verwijderKapitaalvorm(10, "crypto");

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("crypto.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        assertEquals(3, lines.size());
        assertEquals("\"Bitcoin\",50000.0,2.0,100000.0", lines.get(0));
        assertEquals("\"Ethereum\",2000.0,5.0,10000.0", lines.get(1));
        assertEquals("\"Dogecoin\",0.5,1000.0,500.0", lines.get(2));
    }

    @Test
    public void testVerwijderKapitaalvorm_EmptyFile() throws Exception {
        // Condition: Deleting from an Empty File
        try (FileWriter writer = new FileWriter("crypto.csv")) {
            writer.write("");  // Create an empty file
        }

        csvEditor.verwijderKapitaalvorm(1, "crypto");

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("crypto.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        assertTrue(lines.isEmpty());
    }
}
