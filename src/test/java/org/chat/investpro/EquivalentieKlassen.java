package org.chat.investpro;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EquivalentieKlassen {

    public CsvWriter csvWriter;
    IinvesteeringsVormFactory factory;


    @BeforeEach
    public void setUp() {
        csvWriter = new CsvWriter();
        factory = new ConcreteFactory();


    }

    @Test
    void testGeval1 () {

        IinvesteeringsVorm object = factory.createInvesteeringsVorm("Crypto","Bitcoin" ,50000.0, 2.0, 100000.0);
        csvWriter.writeToCSV(object, "crypto");

        IinvesteeringsVorm object2 = factory.createInvesteeringsVorm("Crypto","Ethereum" ,2000.0, 5.0, 10000.0);
        csvWriter.writeToCSV(object2, "crypto");

        IinvesteeringsVorm object3 = factory.createInvesteeringsVorm("Crypto","Dogecoin" ,0.5, 1000.0, 500.0);
        csvWriter.writeToCSV(object3, "crypto");

    }

    private void closeAndDeleteFile(Path path) throws IOException {
        Files.delete(path);
    }



}
