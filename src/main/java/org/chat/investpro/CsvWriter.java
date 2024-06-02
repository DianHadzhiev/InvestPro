package org.chat.investpro;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {

    public void writeToCSV(IinvesteeringsVorm object, String vorm) {
        try {
            String filePath = vorm + ".csv";
            CSVWriter csvWriter = new CSVWriter((new FileWriter(filePath, true)));
            String[] data = {String.valueOf(object.naam), String.valueOf(object.waardeBijaankoop), String.valueOf(object.aankoopPrijs), String.valueOf(object.aantal)};

            csvWriter.writeNext(data);
            System.out.println();
            System.out.println("Succesvol opgelslagen");
            csvWriter.close();

        } catch (IOException e) {
            System.out.println("Er is iets missgegaan ");
        }
    }

    public void writeSpaargeldToCSV(IspaarGeld spaargeld) {
        try {
            String filePath = "spaargeld.csv";
            CSVWriter csvWriter = new CSVWriter((new FileWriter(filePath, false)));
            String[] data = {String.valueOf(spaargeld.aantal)};

            csvWriter.writeNext(data);
            System.out.println();
            System.out.println("Uw spaargeld is opgelslagen");
            csvWriter.close();

        } catch (IOException e) {
            System.out.println("Er is iets missgegaan ");
        }
    }
}



