package org.chat.investpro;

import java.util.ArrayList;

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

}
