package org.chat.investpro;

import lombok.Data;
import lombok.Getter;

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

    private static final DataUser instance = new DataUser();

    private DataUser () {
        crypto = server.reader.readFromCSV("crypto");
        aandeel = server.reader.readFromCSV("aandeel");
        obligatie = server.reader.readFromCSV("obligaties");
        diverse = server.reader.readFromCSV("diverse");
        spaargeld = server.reader.readSpaargeldFromCSV();
    }

    public static DataUser getInstance () {
        if (instance == null) return new DataUser();
        else return instance;
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
        double sum = getTotalaandeel() + getTotalobligatie() + getTotaldiverse() +getTotaleCrypto();
        return sum;
    }

    public void addSpaargeld(IspaarGeld spaargeld) {
        server.writer.writeSpaargeldToCSV(spaargeld);
    }

    public void addCrypto(IinvesteeringsVorm crypto) {
        server.writer.writeToCSV(crypto, "crypto");
    }

    public void addAandeel(IinvesteeringsVorm aandeel) {
        server.writer.writeToCSV(aandeel, "aandeel");
    }

    public void addObligatie(IinvesteeringsVorm obligatie) {
        server.writer.writeToCSV(obligatie, "obligaties");
    }

    public void addDiverse(IinvesteeringsVorm diverse) {
        server.writer.writeToCSV(diverse, "diverse");
    }

    public void updateSpaargeld(IspaarGeld spaargeld) {
        server.writer.writeSpaargeldToCSV(spaargeld);
    }

    public void verwijderCrypto(int index, String vorm) {
        server.editor.verwijderKapitaalvorm(index,vorm);
    }

    public void verwijderAandeel(int index, String vorm) {
        server.editor.verwijderKapitaalvorm(index,vorm);
    }

    public void verwijderObligatie(int index, String vorm) {
        server.editor.verwijderKapitaalvorm(index,vorm);
    }

    public void verwijderDiverse(int index, String vorm) {
        server.editor.verwijderKapitaalvorm(index,vorm);
    }

}
