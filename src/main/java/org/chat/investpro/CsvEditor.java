package org.chat.investpro;

import java.io.*;

public class CsvEditor {

    public void verwijderKapitaalvorm(int Index, String vorm ) {

        String temp = "temp.csv";
        String filepath = vorm + ".csv";
        File oldfile = new File(filepath);
        File newfile = new File(temp);

        int line = 0;
        String currentLine;

        try {
            FileWriter fw = new FileWriter(temp);

            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null) {
                line++;

                if (Index != line) {
                    pw.println(currentLine);
                }
            }
            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bw.close();
            fw.close();

            if (!oldfile.delete()) {
                System.out.println("Failed to delete the old file.");
                return;
            }
            File dump = new File(filepath);
            if (!newfile.renameTo(dump)) {
                System.out.println("Failed to rename the new file.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
