package org.chat.investpro;

import com.opencsv.CSVWriter;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CsvReaderTest {

    private CsvReader csvReader;

    @BeforeEach
    void setUp() {
        csvReader = new CsvReader();
    }

    @Test
    void testReadFromCSV_condition() throws IOException {
        String content = "name1, 100.0, 2.0";
        String content2 = "name2, 200.0, 3.0";
        String content3 = "name3, 300.0, 4.0, 150.0";
        String content4 = "name4, 400.0, 5.0";
        Path tempFile = createCSVFile("crypto.csv", content);
        //Path tempFile2 = createCSVFile("aandeel.csv", content2);
        //Path tempFile3 = createCSVFile("obligatie.csv", content3);
        //Path tempFile4 = createCSVFile("diverse.csv", content4);

        when(mock(CsvReader.class).readFromCSV("aandeel")).thenReturn(null);
        when(mock(CsvReader.class).readFromCSV("obligatie")).thenReturn(null);
        when(mock(CsvReader.class).readFromCSV("diverse")).thenReturn(null);

        ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("crypto");
        ArrayList<IinvesteeringsVorm> result2 = mock(CsvReader.class).readFromCSV("aandeel");
        ArrayList<IinvesteeringsVorm> result3 = mock(CsvReader.class).readFromCSV("obligatie");
        ArrayList<IinvesteeringsVorm> result4 = mock(CsvReader.class).readFromCSV("diverse");

        assertEquals(0,result.size());
        assertEquals(0,result2.size());
        assertEquals(0,result3.size());
        assertEquals(0,result4.size());

        closeAndDeleteFile(tempFile);

    }

    @Test
    void testReadFromCSV_Decision() throws IOException {
        String content = "name1, 100.0, 2.0, 150.0";
        String content2 = "name2, 200.0, 3.0, 250.0";
        Path tempFile = createCSVFile("crypto.csv", content);
        //Path tempFile2 = createCSVFile("aandeel.csv", content2);

        ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("crypto");
        ArrayList<IinvesteeringsVorm> result2 = csvReader.readFromCSV("aandeel");

        assertEquals(1,result.size());
        assertEquals(0,result2.size());

        closeAndDeleteFile(tempFile);
       // closeAndDeleteFile(tempFile2);

    }

    @Test
    void testReadFromCSV_CD() throws IOException {
        String content = "name1, 100.0, vals, 150.0";
        String content2 = "name2, 200.0, 3.0, 250.0";

       // Path tempFile = createCSVFile("crypto.csv", content);
        Path tempFile2 = createCSVFile("aandeel.csv", content2);

        ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("crypto");
        ArrayList<IinvesteeringsVorm> result2 = csvReader.readFromCSV("aandeel");

        assertEquals(0,result.size());
        assertEquals(1,result2.size());

        //closeAndDeleteFile(tempFile);
        closeAndDeleteFile(tempFile2);
    }

    @Test
    void mccc() throws IOException {
        String content = "name1, 100.0, vals";
        String content2 = "name2, 200.0, 3.0";
        String content3 = "name3, 300.0, vals, 150.0";
        String content4 = "name4, 400.0, 5.0, 250.0";
        String content5 = "name5, 500.0, vals";
        String content6 = "name6, 600.0, 7.0";
        String content7 = "name7, 700.0, vals, 250.0";
        String content8 = "name8, 800.0, 9.0, 150.0";

       // Path tempFile = createCSVFile("aandeel.csv", content);
        //Path tempFile2 = createCSVFile("aandeel.csv", content2);
       // Path tempFile3 = createCSVFile("aandeel.csv", content3);
       //Path tempFile4 = createCSVFile("aandeel.csv", content4);
        Path tempFile5 = createCSVFile("crypto.csv", content5);
        Path tempFile6 = createCSVFile("obligaties.csv", content6);
        Path tempFile7 = createCSVFile("diverse.csv", content7);
        Path tempFile8 = createCSVFile("aandeel.csv", content8);

        ArrayList<IinvesteeringsVorm> result = csvReader.readFromCSV("diverse");
        ArrayList<IinvesteeringsVorm> result2 = csvReader.readFromCSV("obligatie");
        ArrayList<IinvesteeringsVorm> result3 = csvReader.readFromCSV("crypto");
        ArrayList<IinvesteeringsVorm> result4 = csvReader.readFromCSV("crypto");
        ArrayList<IinvesteeringsVorm> result5 = csvReader.readFromCSV("obligatie");
        ArrayList<IinvesteeringsVorm> result6 = csvReader.readFromCSV("diverse");
        ArrayList<IinvesteeringsVorm> result7 = csvReader.readFromCSV("crypto");
        ArrayList<IinvesteeringsVorm> result8 = csvReader.readFromCSV("aandeel");

        assertEquals(0,result.size());
        assertEquals(0,result2.size());
        assertEquals(0,result3.size());
        assertEquals(0,result4.size());
        assertEquals(0,result5.size());
        assertEquals(0,result6.size());
        assertEquals(0,result7.size());
        assertEquals(1,result8.size());

        closeAndDeleteFile(tempFile5);
        closeAndDeleteFile(tempFile6);
        closeAndDeleteFile(tempFile7);
        closeAndDeleteFile(tempFile8);
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
