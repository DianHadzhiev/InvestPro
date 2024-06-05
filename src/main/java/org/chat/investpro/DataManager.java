package org.chat.investpro;

import lombok.Data;

import java.util.ArrayList;
import java.util.Scanner;

@Data
public class DataManager {
    private static IinvesteeringsVormFactory factoryInvesteeringsVorm;
    private static IspaargeldFactory spaargeldFactory;
    private DataUser user;
    private Scanner scanner;

    public DataManager(IinvesteeringsVormFactory factoryInvesteeringsVorm, IspaargeldFactory spaargeldFactory, DataUser user,Scanner scanner) {
        this.factoryInvesteeringsVorm = factoryInvesteeringsVorm;
        this.spaargeldFactory = spaargeldFactory;
        this.user = user;
        this.scanner = scanner;
    }

    public void addVorm(Scanner scanner, String vorm) {
        System.out.println("Welke " + vorm + " wilt u toevoegen?");
        String naam = scanner.nextLine();
        System.out.println("Hoeveel " + vorm + " wilt u toevoegen?");
        double aantal = scanner.nextInt();
        System.out.println("wat was de waarde?");
        double waarde = scanner.nextDouble();
        System.out.println("Hoeveel heeft u ervoor betaalt?");
        double prijsAankoop = scanner.nextDouble();
        IinvesteeringsVorm object = factoryInvesteeringsVorm.createInvesteeringsVorm(vorm, naam, waarde, aantal, prijsAankoop);
        user.addVorm(object, vorm);
    }

    public void verwijderVorm(Scanner scanner, String vorm) {
        ArrayList<IinvesteeringsVorm> vormen = user.getVorm(vorm);
        if (vormen == null) {
            System.out.println("U heeft geen " + vorm + " in uw portefeuille");
        } else {
            System.out.println("Welke " + vorm + " wilt u verwijderen? of voer 0 in om terug te gaan");
            for (int i = 0; i < vormen.size(); i++) {
                System.out.println((i + 1) + " " + vormen.get(i).getNaam());
            }
            System.out.println("Voer u keuze in: ");
            int keuze = scanner.nextInt();
            if (keuze != 0) {
                user.verwijderVorm(keuze, vorm);
            }
        }
    }

    public void addspaargeld(Scanner scanner) {
        System.out.println("Hoeveel wilt u toevoegen?");
        double aantal = scanner.nextDouble();
        IspaarGeld nieuwspaargeld = user.getSpaargeld();
        nieuwspaargeld.setAantal(nieuwspaargeld.getAantal() + aantal);
        user.updateSpaargeld(nieuwspaargeld);

        System.out.println("Succesvol toegevoegd");
    }

    public void setSpaargeld(Scanner scanner) {
        System.out.println("Wat is het nieuwe bedrag");
        double aantal = scanner.nextDouble();
        IspaarGeld spaargeld = spaargeldFactory.createIspaarGeld(aantal);
        user.updateSpaargeld(spaargeld);
        System.out.println("Succesvol toegevoegd");
        scanner.nextLine();
    }

    public void berekenRente(Scanner scanner) {
        System.out.println("Hoeveel jaar gaat u het geld sparen");
        int jaar = scanner.nextInt();
        double rentebedrag = 0.0;
        double rentePerc;
        if (user.getTotalspaargeld() < 20000) {
            rentebedrag = (user.getTotalspaargeld() * Math.pow(0.15, jaar)) - user.getTotalspaargeld();
        } else if (user.getTotalspaargeld() <= 100000) {
            rentePerc = 1.016;
            rentebedrag = (user.getTotalspaargeld() * Math.pow(rentePerc, jaar)) - user.getTotalspaargeld();
        } else if (user.getTotalspaargeld() <= 5000000) {
            rentePerc = 1.015;
            rentebedrag = (user.getTotalspaargeld() * Math.pow(rentePerc, jaar)) - user.getTotalspaargeld();
        }
        System.out.printf("U gaat $ %.2f aan rente ontvangen %n", rentebedrag);
    }

    public void berekendividend(Scanner scanner) {
        System.out.println("Hoeveel dividend heeft u ontvangen?");
        double dividend = scanner.nextDouble();
        dividend *= 0.15;
        System.out.println("Te betalen belasting" + dividend);
        scanner.nextLine();
    }

    public void viewPortofolioVorm(String form) {
        System.out.println(form + ":");
        System.out.println();
        for (IinvesteeringsVorm vorm : user.getVorm(form)) {
            System.out.println(vorm.getNaam() + ": " + vorm.getAantal());
        }
        scanner.nextLine();
    }

}