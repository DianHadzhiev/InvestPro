package org.chat.investpro;

import com.opencsv.CSVWriter;
import org.junit.jupiter.api.*;

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

    void testReadFromCSV_invalidFile() {
        ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("nonexistent_file");

        assertEquals(0, result.size());
    }

    void testReadFromCSV_invalidData() throws IOException {
        String content = "name1,invalid,2.0,50.0";
        Path tempFile = createCSVFile("aandeel.csv", content);

        ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("aandeel");

        assertEquals(0, result.size());

        closeAndDeleteFile(tempFile);
    }


    void testReadFromCSV_validData() throws IOException {
        Path path = Paths.get("aandeel.csv");
        closeAndDeleteFile(path);
        String content = "name1,2.0, 50.0, 100.0";
        Path tempFile = createCSVFile("aandeel.csv", content);

        ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("aandeel");

        assertEquals(1, result.size());
        assertEquals("name1", result.get(0).getNaam());
        assertEquals(100.0, result.get(0).getAankoopPrijs());
        assertEquals(2.0, result.get(0).getAantal());
        assertEquals(50.0, result.get(0).getWaardeBijAankoop());

        closeAndDeleteFile(tempFile);
    }


    void testReadFromCsv_validDataLength() throws IOException {
        try {
            Path path = Paths.get("aandeel.csv");
            closeAndDeleteFile(path);
        } catch (NoSuchFileException e) {
            System.out.println("No such file: " + e.getMessage());
        }
        String content = "name1,2.0, 50.0, 100.0";
        Path tempFile = createCSVFile("aandeel.csv", content);

        ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("aandeel");

        assertEquals(1, result.size());
        assertEquals(4, result.get(0).toString().split(",").length);

        closeAndDeleteFile(tempFile);
    }


    void testReadFromCsv_invalidDataLength() throws IOException {
        try {
            Path path = Paths.get("aandeel.csv");
            closeAndDeleteFile(path);
        } catch (NoSuchFileException e) {
            System.out.println("No such file: " + e.getMessage());
        }

        String content = "name1, 2.0, 50.0";
        Path tempFile = createCSVFile("aandeel.csv", content);
        ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("aandeel");

        assertEquals(0, result.size());
        closeAndDeleteFile(tempFile);

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

    @Test
    void mfcd1() throws IOException {
        try {
            Path path = Paths.get("aandeel.csv");
            closeAndDeleteFile(path);
        } catch (NoSuchFileException e) {
            System.out.println("No such file: " + e.getMessage());
        }

        String content = "name1,2.0, 50.0, 100.0";
        Path tempFile = createCSVFile("aandeel.csv", content);

        ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("aandeel");
        assertEquals(1, result.size());
        assertEquals("name1", result.get(0).getNaam());
        assertEquals(100.0, result.get(0).getAankoopPrijs());
        assertEquals(2.0, result.get(0).getAantal());
        assertEquals(50.0, result.get(0).getWaardeBijAankoop());
        closeAndDeleteFile(tempFile);
    }

    @Test
    void mfcd2() throws Exception {
        try {
            Path path = Paths.get("InvalidFile.csv");
            closeAndDeleteFile(path);
        } catch (NoSuchFileException e) {
            System.out.println("No such file: " + e.getMessage());
        }
        String content = "name1,2.0, 50.0, 100.0";

        try {
            ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("InvalidFile");
            assertNotEquals(1, result.size());
            assertNotEquals("name1", result.get(0).getNaam());
            assertNotEquals(100.0, result.get(0).getAankoopPrijs());
            assertNotEquals(2.0, result.get(0).getAantal());
            assertNotEquals(50.0, result.get(0).getWaardeBijAankoop());

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage() + " Test should throw an exception");
        }

    }

    @Test
    void mfdc3() throws Exception {
        try {
            Path path = Paths.get("aandeel.csv");
            closeAndDeleteFile(path);
        } catch (NoSuchFileException e) {
            System.out.println("No such file: " + e.getMessage());
        }

        String content = "name1,2.0, 50.0";
        Path tempFile = createCSVFile("aandeel.csv", content);

        try {
            ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("InvalidFile");
            assertNotEquals(1, result.size());
            assertNotEquals("name1", result.get(0).getNaam());
            assertNotEquals(100.0, result.get(0).getAankoopPrijs());
            assertNotEquals(2.0, result.get(0).getAantal());
            assertNotEquals(50.0, result.get(0).getWaardeBijAankoop());

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage() + " Test should throw an exception");
        }
        closeAndDeleteFile(tempFile);
    }

    @Test
    void mfdc4() throws Exception {
        try {
            Path path = Paths.get("aandeel.csv");
            closeAndDeleteFile(path);
        } catch (NoSuchFileException e) {
            System.out.println("No such file: " + e.getMessage());
        }

        String content = "name1,invalid, 50.0, 100.0";
        Path tempFile = createCSVFile("aandeel.csv", content);

        try{
            ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("aandeel");
            assertNotEquals(1, result.size());
            assertNotEquals("name1", result.get(0).getNaam());
            assertNotEquals(100.0, result.get(0).getAankoopPrijs());
            assertNotEquals(2.0, result.get(0).getAantal());
            assertNotEquals(50.0, result.get(0).getWaardeBijAankoop());
        }catch (Exception e){
            System.out.println("Error " + e.getMessage() + " Test should throw an exception");
        }
        closeAndDeleteFile(tempFile);

    }

    @Test
    void mfdc5() throws Exception {
        try {
            Path path = Paths.get("invalid.csv");
            closeAndDeleteFile(path);
        } catch (NoSuchFileException e) {
            System.out.println("No such file: " + e.getMessage());
        }

        String content = "name1,invalid, 100.0";
        Path tempFile = createCSVFile("invalid.csv", content);

        try{
            ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("aandeel");
            assertNotEquals(1, result.size());
            assertNotEquals("name1", result.get(0).getNaam());
            assertNotEquals(100.0, result.get(0).getAankoopPrijs());
            assertNotEquals(2.0, result.get(0).getAantal());
            assertNotEquals(50.0, result.get(0).getWaardeBijAankoop());
        }catch (Exception e){
            System.out.println("Error " + e.getMessage() + " Test should throw an exception");
        }
        closeAndDeleteFile(tempFile);

    }

}
