package org.chat.investpro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {

    private IinvesteeringsVormFactory factory;
    private IspaargeldFactory spaargeldFactory = new ConcreteSpaargeldFactory();

    public ArrayList<IinvesteeringsVorm> readFromCSV(String typeInvest) {
        String filePath = typeInvest + ".csv";
        ArrayList<IinvesteeringsVorm> lines = new ArrayList<>();
        factory = new ConcreteFactory();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    try {
                        String naam = data[0].replaceAll("\"", "");
                        double waardeBijaankoop = Double.parseDouble(data[1].replaceAll("\"", ""));
                        double aantal = Double.parseDouble(data[2].replaceAll("\"", ""));
                        double aankoopPrijs = Double.parseDouble(data[3].replaceAll("\"", ""));
                        IinvesteeringsVorm object = factory.createInvesteeringsVorm(typeInvest, naam, waardeBijaankoop, aantal, aankoopPrijs);
                        lines.add(object);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format in line: " + line);
                    }
                } else {
                    System.out.println("Invalid CSV format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }

    public IspaarGeld readSpaargeldFromCSV() {
        String filePath = "spaargeld.csv";
        IspaarGeld spaargeld = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 1) {
                    try {
                        double aantal = Double.parseDouble(data[0].replaceAll("\"", ""));
                        spaargeld = spaargeldFactory.createIspaarGeld(aantal);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format in line: " + line);
                    }
                } else {
                    System.out.println("Invalid CSV format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return spaargeld;
    }

}
