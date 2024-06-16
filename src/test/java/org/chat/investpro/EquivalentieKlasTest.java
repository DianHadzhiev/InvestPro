package org.chat.investpro;

import com.opencsv.CSVWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class EquivalentieKlasTest {

    public CsvWriter csvWriter;
    public CsvReader csvReader;
    IinvesteeringsVormFactory factory;
    ArrayList <IinvesteeringsVorm> result;


    @BeforeEach
    public void setUp() {
        csvWriter = new CsvWriter();
        factory = new ConcreteFactory();
        csvReader = new CsvReader();

    }

    @Test
    void testEquivalentie1 () throws IOException {
        Path tempFile = createCSVFile("crypto.csv");
        IinvesteeringsVorm object = factory.createInvesteeringsVorm("Crypto","Bitcoin" ,50000.0, 2.0, 100000.0);
        csvWriter.writeToCSV(object, "crypto");

        IinvesteeringsVorm object2 = factory.createInvesteeringsVorm("Crypto","Ethereum" ,2000.0, 5.0, 10000.0);
        csvWriter.writeToCSV(object2, "crypto");

        IinvesteeringsVorm object3 = factory.createInvesteeringsVorm("Crypto","Dogecoin" ,0.5, 1000.0, 500.0);
        csvWriter.writeToCSV(object3, "crypto");

        result = csvReader.readFromCSV("crypto");
        assertEquals(3, result.size());
        assertEquals("Bitcoin", result.get(0).getNaam());
        assertEquals("Ethereum", result.get(1).getNaam());
        assertEquals("Dogecoin", result.get(2).getNaam());
        //alles is opgeslagen en is uit te lezen
        closeAndDeleteFile(tempFile);

    }

    @Test
    void testEquivalentie2 () {

        try {
            IinvesteeringsVorm object = factory.createInvesteeringsVorm("valseVorm", "name", 2.0, 50.0, 100.0);
            csvWriter.writeToCSV(object, "valseVorm");

            IinvesteeringsVorm object2 = factory.createInvesteeringsVorm("valseVorm", "name2", 2.0, 50.0, 100.0);
            csvWriter.writeToCSV(object2, "valseVorm");

            IinvesteeringsVorm object3 = factory.createInvesteeringsVorm("valseVorm", "name3", 2.0, 50.0, 100.0);
            csvWriter.writeToCSV(object3, "valseVorm");
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        result = csvReader.readFromCSV("valseVorm");

        assertEquals(0,result.size());
        // Niks is opgeslagen(valse vorm, geldige object)

    }

    @Test
    void testEquivalentie3 () {
       try {
           when(factory.createInvesteeringsVorm("valseVorm", "name", Double.parseDouble("vals"), 50.0, 100.0)).thenReturn(null);
           when(factory.createInvesteeringsVorm("valseVorm", "name2", 2.0, Double.parseDouble("vals"), 100.0)).thenReturn(null);
           when(factory.createInvesteeringsVorm("valseVorm", "name3", 2.0, 50.0, Double.parseDouble("vals"))).thenReturn(null);
       }catch (Exception e) {
           System.out.println(e.getMessage());
       }
       result = csvReader.readFromCSV("valseVorm");

       assertEquals(0,result.size());
        // Niks is opgeslagen(valse vorm, ongeldige object)
    }


    private void closeAndDeleteFile(Path path) throws IOException {
        Files.delete(path);
    }

    private Path createCSVFile(String fileName) throws IOException {
        return Paths.get(fileName);
    }

}
