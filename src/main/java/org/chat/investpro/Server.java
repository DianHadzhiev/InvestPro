package org.chat.investpro;


import java.util.ArrayList;

public class Server {

    public CsvReader reader = new CsvReader();
    public CsvWriter writer = new CsvWriter();
    public CsvEditor editor = new CsvEditor();

    public ArrayList<IinvesteeringsVorm> getInvesteeringsVorm(String type) {
        return reader.readFromCSV(type);
    }

    public IspaarGeld getIspaarGeld() {
        return reader.readSpaargeldFromCSV();
    }

}
