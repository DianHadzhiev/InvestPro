package org.chat.investpro;

import lombok.Data;
import java.io.IOException;
import java.util.ArrayList;

@Data
public class DataUser {

    private ArrayList<IinvesteeringsVorm> crypto;
    private ArrayList<IinvesteeringsVorm> aandeel;
    private ArrayList<IinvesteeringsVorm> obligatie;
    private ArrayList<IinvesteeringsVorm> diverse;
    private IspaarGeld spaargeld;

    private Server server = new Server();

    private static DataUser instance = null;

    private DataUser () {
        getAllData();
    }

    public void getAllData() {
        crypto = server.getReader().readFromCSV("crypto");
        aandeel = server.getReader().readFromCSV("aandeel");
        obligatie = server.getReader().readFromCSV("obligatie");
        diverse = server.getReader().readFromCSV("diverse");
        spaargeld = server.getReader().readSpaargeldFromCSV();
    }

    public static DataUser getInstance () {
        if (instance == null) return new DataUser();
        else return instance;
    }

    public ArrayList<IinvesteeringsVorm> getVorm(String vorm) {
        switch (vorm) {
            case "crypto":
                return crypto;
            case "aandeel":
                return aandeel;
            case "obligatie":
                return obligatie;
            case "diverse":
                return diverse;
            default:
                return null;
        }
    }

    public double getTotaleCrypto () {
        double sum = 0.0;
        for (IinvesteeringsVorm v : crypto) sum += v.getAankoopPrijs();
        return sum;
    }

    public double getTotalaandeel () {
        double sum = 0.0;
        for (IinvesteeringsVorm v : aandeel) sum += v.getAankoopPrijs();
        return sum;
    }

    public double getTotalobligatie () {
        double sum = 0.0;
        for (IinvesteeringsVorm v : obligatie) sum += v.getAankoopPrijs();
        return sum;
    }

    public double getTotaldiverse () {
        double sum = 0.0;
        for (IinvesteeringsVorm v : diverse) sum += v.getAankoopPrijs();
        return sum;
    }

    public double getTotalspaargeld () {
        if (spaargeld == null) return 0.0;
        else return spaargeld.getAantal();
    }

    public double totalePortofolio() {
        return getTotalaandeel() + getTotalobligatie() + getTotaldiverse() +getTotaleCrypto();
    }

    public void updateSpaargeld(IspaarGeld spaargeld) {
        server.getWriter().writeSpaargeldToCSV(spaargeld);
    }

    public void addVorm(IinvesteeringsVorm object, String vorm) {
        server.getWriter().writeToCSV(object, vorm);
    }

    public void verwijderVorm(int index, String vorm) {
        server.getEditor().verwijderKapitaalvorm(index,vorm);
    }

}