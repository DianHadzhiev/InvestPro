package org.chat.investpro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader  {

    private IinvesteeringsVormFactory factory;

    public ArrayList<IinvesteeringsVorm> readFromCSV(String typeInvest) {
        String filePath = typeInvest + ".csv";
        BufferedReader reader = null;
        String line ="";
        ArrayList<IinvesteeringsVorm> lines = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String naam = data[0].replaceAll("\"", "");
                    double waardeBijaankoop = Double.parseDouble(data[1].replaceAll("\"", ""));
                    double aantal = Integer.parseInt(data[2].replaceAll("\"", ""));
                    double aankoopPrijs = Double.parseDouble(data[3].replaceAll("\"", ""));

                    IinvesteeringsVorm object = factory.createInvesteeringsVorm(typeInvest,naam, waardeBijaankoop, aantal, aankoopPrijs);

                    lines.add(object);

                }
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("Er is iets misgegaan " + e.getMessage());
        }
        return lines;

    }

    public IspaarGeld readSpaargeldFromCSV() {
        String filePath = "spaargeld.csv";
        BufferedReader reader = null;
        String line ="";
        IspaarGeld spaargeld = null;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {

                    String naam = data[0].replaceAll("\"", "");
                    double rentePercent = Double.parseDouble(data[1].replaceAll("\"", ""));
                    double aantal = Double.parseDouble(data[2].replaceAll("\"", ""));


                    spaargeld = new SpaarGeld(naam,rentePercent, aantal);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Er is iets misgegaan " + e.getMessage());
        }
        return spaargeld;
    }

}