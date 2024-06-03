package org.chat.investpro;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    private static IinvesteeringsVormFactory factoryInvesteeringsVorm;
    private static IspaargeldFactory spaargeldFactory;
    private static DataUser user;
    static Scanner scanner = new Scanner(System.in);
    public boolean running = true;

    public Client () {
        spaargeldFactory = new ConcreteSpaargeld();
        user = DataUser.getInstance();
        factoryInvesteeringsVorm = new ConcreteFactory();
    }

    private static void terugNaarHoofdmenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Druk op Enter om terug te gaan naar het hoofdmenu.");
        scanner.nextLine();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void menuStart() {

        while (running) {
            printMenu();
            System.out.println("Voer uw keuze in:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            makeChoice(choice);
        }
    }

    public void printMenu() {
        System.out.println("<*-------InvestPro------*>");
        System.out.println();
        System.out.println("1. Bekijk portofolio");
        System.out.println("");
        System.out.println("2. Voeg aandeel toe");
        System.out.println("3. Verwijder aandeel");
        System.out.println("");
        System.out.println("4. Voeg crypto toe");
        System.out.println("5. Verwijder crypto");
        System.out.println("");
        System.out.println("6. Voeg obligatie toe");
        System.out.println("7. Verwijder obligatie");
        System.out.println("");
        System.out.println("8. Voeg spaargeld toe");
        System.out.println("9. bewerk spaargeld");
        System.out.println("");
        System.out.println("10. bereken dividend belastingbedrag");
        System.out.println("11. bereken rente uit spaargeld");
        System.out.println("");
        System.out.println("12. Voeg diverse toe");
        System.out.println("13. verwijder eigen kapitaalvorm");
        System.out.println("");
        System.out.println("14. sluit af");
    }

    public void makeChoice (int choice) {
        switch (choice) {
            case 1:
                viewPortfolio();
                break;
            case 2:
                addAandeel();
                break;
            case 3:
                verwijderAandeel();
                break;
            case 4:
                addCrypto();
                break;
            case 5:
                verwijderCrypto();
                break;
            case 6:
                addObligatie();
                break;
            case 7:
                verwijderobligatie();
                break;
            case 8:
                addspaargeld();
                break;
            case 9:
                setSpaargeld();
                break;
            case 10:
                berekendividend();
                break;
            case 11:
                berekenRente();
                break;
            case 12:
                addDiverse();
                break;
            case 13:
                verwijderdiverse();
                break;
            case 14:
                clearScreen();
                System.out.println("Het programma wordt afgesloten");
                running = false;
                break;
            case 15:

                break;
            default:
                System.out.println("Ongeldige keuze, Probeer opnieuw.");
        }
    }

    private static void viewPortfolio() {
        clearScreen();

        boolean running = true;

        while (running) {
            clearScreen();
            System.out.println("totaal portofeuille: " + user.totalePortofolio());
            System.out.println();
            System.out.println("1. aandelen");
            System.out.println("2. crypto");
            System.out.println("3. obligaties");
            System.out.println("4. diverse");
            System.out.println("5. spaargeld");
            System.out.println("6. terug naar hoofdmenu");
            System.out.println("Wat wilt u bekijken?");
            int keuze =scanner.nextInt();
            switch(keuze) {
                case 1 :
                    clearScreen();
                    System.out.println(user.getTotalaandeel());
                    scanner.nextLine();
                    break;
                case 2 :
                    clearScreen();
                    System.out.println(user.getTotaleCrypto());
                    break;
                case 3:
                    clearScreen();
                    System.out.println(user.getTotalobligatie());
                    break;
                case 4 :
                    clearScreen();
                    System.out.println(user.getTotaldiverse());
                    break;
                case 5 :
                    clearScreen();
                    System.out.println("Spaargeld: $" + user.getTotalspaargeld());
                    break;
                case 6:
                    clearScreen();
                    running = false;
                    terugNaarHoofdmenu();
                    break;
                default:
                    System.out.println("Ongeldige keuze. Probeer opnieuw.");
                    break;
            }
        }
    }

    private static void addAandeel() {
        System.out.println("Welke aandeel wilt u toevoegen?");
        String naam = scanner.nextLine();
        System.out.println("Hoeveel aandelen wilt u toevoegen?");
        double aantal = scanner.nextInt();
        System.out.println("wat was de waarde?");
        double waarde = scanner.nextDouble();
        System.out.println("Hoeveel heeft u ervoor betaalt?");
        double prijsAankoop = scanner.nextDouble();
        IinvesteeringsVorm aandeel = factoryInvesteeringsVorm.createInvesteeringsVorm("aandeel", naam, waarde, aantal, prijsAankoop);
        user.addAandeel(aandeel);
        terugNaarHoofdmenu();
    }

    private static void addCrypto() {
        System.out.println("Welke crypto wilt u toevoegen?");
        String naam = scanner.nextLine();
        System.out.println("Hoeveel wilt u toevoegen?");
        double aantal = scanner.nextInt();
        System.out.println("wat was de waarde?");
        double waarde = scanner.nextDouble();
        System.out.println("Hoeveel heeft u ervoor betaalt?");
        double prijsAankoop = scanner.nextDouble();
        IinvesteeringsVorm crypto = factoryInvesteeringsVorm.createInvesteeringsVorm("crypto", naam, waarde, aantal, prijsAankoop);
        user.addCrypto(crypto);
        System.out.println("Succesvol toegevoegd");
        terugNaarHoofdmenu();
    }

    private static void addObligatie() {
        System.out.println("Welke obligatie wilt u toevoegen?");
        String naam = scanner.nextLine();
        System.out.println("Hoeveel obligaties wilt u toevoegen?");
        double aantal = scanner.nextInt();
        System.out.println("wat was de waarde?");
        double prijs = scanner.nextDouble();
        System.out.println("Hoeveel heeft u ervoor betaalt?");
        double prijsAankoop = scanner.nextDouble();
        IinvesteeringsVorm obligatie = factoryInvesteeringsVorm.createInvesteeringsVorm("obligatie", naam, prijs, aantal, prijsAankoop);
        user.addObligatie(obligatie);
        terugNaarHoofdmenu();
    }

    private static void addDiverse() {
        System.out.println("Welke diverse wilt u toevoegen?");
        String naam = scanner.nextLine();
        System.out.println("Hoeveel stuks wilt u toevoegen?");
        double aantal = scanner.nextInt();
        System.out.println("wat was de waarde?");
        double prijs = scanner.nextDouble();
        System.out.println("Hoeveel heeft u ervoor betaalt?");
        double prijsAankoop = scanner.nextDouble();
        IinvesteeringsVorm diverse = factoryInvesteeringsVorm.createInvesteeringsVorm("diverse", naam, prijs, aantal, prijsAankoop);
        user.addDiverse(diverse);
        System.out.println("Succesvol toegevoegd");
        scanner.nextLine();
        terugNaarHoofdmenu();
    }

    private static void verwijderAandeel() {
        ArrayList<IinvesteeringsVorm> aandelen = user.getAandeel();
        int i = 1;
        if(aandelen == null) {
            System.out.println("U heeft geen aandelen");
            terugNaarHoofdmenu();
        }
        else {
            System.out.println("Welke aandeel wilt u verwijderen? of voer 0 in om terug te gaan");
            for (IinvesteeringsVorm aandeel : aandelen) {
                System.out.println(i + " " + aandeel.getNaam());
                i++;
            }
            System.out.println("Voer u keuze in: ");
            int keuze = scanner.nextInt();
            if (keuze == 0) { terugNaarHoofdmenu();
            } else {
                user.verwijderAandeel(keuze, "aandeel");
                terugNaarHoofdmenu();
            }
        }
    }

    private static void verwijderCrypto() {
        ArrayList<IinvesteeringsVorm> crypto = user.getCrypto();
        int i = 1;
        System.out.println("Welke crypto wilt u verwijderen? of voer 0 in om terug te gaan");
        for (IinvesteeringsVorm cryptos : crypto) {
            System.out.println(i + " " + cryptos.getNaam());
            i++;
        }
        System.out.println("Voer u keuze in: ");
        int keuze = scanner.nextInt();
        if (keuze == 0) { terugNaarHoofdmenu();
        } else {
            user.verwijderCrypto(keuze, "crypto");
            terugNaarHoofdmenu();
        }
    }

    private static void verwijderobligatie () {
        System.out.println("Welke obligatie wilt u verwijderen? of voer 0 in om terug te gaan");
        ArrayList<IinvesteeringsVorm> obligaties = user.getObligatie();
        int i = 1;
        for (IinvesteeringsVorm obli : obligaties) {
            System.out.println(i + " " + obli.getNaam());
            i++;
        }
        System.out.println("Voer u keuze in: ");
        int keuze = scanner.nextInt();
        if (keuze == 0) { terugNaarHoofdmenu();
        } else {
            user.verwijderObligatie(keuze, "obligatie");
            terugNaarHoofdmenu();
        }
    }

    private static void verwijderdiverse () {
        System.out.println("Welke diverse wilt u verwijderen?");
        ArrayList<IinvesteeringsVorm> diverse = user.getDiverse();
        int i = 1;
        for (IinvesteeringsVorm div : diverse) {
            System.out.println(i + " " + div.getNaam());
            i++;
        }
        System.out.println("Voer u keuze in of voer 0 in om terug te gaan");
        int keuze = scanner.nextInt();
        user.verwijderDiverse(keuze, "diverse");
        terugNaarHoofdmenu();
    }

    private static void addspaargeld() {
        System.out.println("Hoeveel wilt u toevoegen?");
        double aantal = scanner.nextDouble();
        IspaarGeld spaargeld = spaargeldFactory.createIspaarGeld(aantal);
        user.addSpaargeld(spaargeld);
        System.out.println("Succesvol toegevoegd");
        terugNaarHoofdmenu();
    }

    private static void setSpaargeld() {
        System.out.println("Wat is het nieuwe bedrag");
        double aantal = scanner.nextDouble();
        IspaarGeld spaargeld = spaargeldFactory.createIspaarGeld(aantal);
        user.updateSpaargeld(spaargeld);
        System.out.println("Succesvol toegevoegd");
        scanner.nextLine();
        terugNaarHoofdmenu();
    }

    private static void berekenRente() {
        System.out.println("Hoeveel jaar gaat u het geld sparen");
        int jaar = scanner.nextInt();
            double rentebedrag = 0.0;
            double rentePerc;
            if (user.getTotalspaargeld() < 20000) {
                rentebedrag = (user.getTotalspaargeld() * Math.pow(0.15,jaar)) - user.getTotalspaargeld();
            } else if (user.getTotalspaargeld() <=100000) {
                rentePerc = 1.016;
                rentebedrag = (user.getTotalspaargeld() * Math.pow(rentePerc,jaar))- user.getTotalspaargeld();
            } else if (user.getTotalspaargeld() <=5000000) {
                rentePerc = 1.015;
                rentebedrag = (user.getTotalspaargeld() * Math.pow(rentePerc,jaar)) - user.getTotalspaargeld();
            }
        System.out.printf("U gaat $ %.2f aan rente ontvangen %n", rentebedrag);
        terugNaarHoofdmenu();
    }

    public void berekendividend() {
        System.out.println("Hoeveel dividend heeft u ontvangen?");
        double dividend = scanner.nextDouble();
        dividend *= 0.15;
        System.out.println("U ontvangt $ " + dividend);
        scanner.nextLine();
        terugNaarHoofdmenu();
    }
}











