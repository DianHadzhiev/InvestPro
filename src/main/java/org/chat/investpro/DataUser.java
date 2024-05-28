package org.chat.investpro;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;

@Data
public class DataUser {

    private ArrayList<IinvesteeringsVorm> crypto;
    private ArrayList<IinvesteeringsVorm> aandeel;
    private ArrayList<IinvesteeringsVorm> obligatie;
    private ArrayList<IinvesteeringsVorm> diverse;
    private IspaarGeld spaargeld;

    private static final DataUser instance = new DataUser();

    private DataUser () {
        Server server = new Server();
        crypto = server.reader.readFromCSV("crypto");
        aandeel = server.reader.readFromCSV("aandelen");
        obligatie = server.reader.readFromCSV("obligaties");
        diverse = server.reader.readFromCSV("diverse");
        spaargeld = server.reader.readSpaargeldFromCSV();

    }

    public static DataUser getInstance () {
        if (instance == null) return new DataUser();
        else return instance;
    }

    public double getTotalecrypto () {
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
        for (IinvesteeringsVorm v : obligatie) {
            sum += v.getAankoopPrijs();
        }
        return sum;
    }

    public double getTotaldiverse () {
        double sum = 0.0;
        for (IinvesteeringsVorm v : diverse) {
            sum += v.getAankoopPrijs();
        }
        return sum;
    }

    public double getTotalspaargeld () {
        return spaargeld.getAantal();
    }

    public double totalePortofolio() {
        double sum = getTotalaandeel() + getTotalobligatie() + getTotaldiverse() +getTotalecrypto();
        return sum;
    }


}
