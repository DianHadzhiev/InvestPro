package org.chat.investpro;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    private static IinvesteeringsVormFactory factoryInvesteeringsVorm;
    private static IspaargeldFactory spaargeldFactory;
    private static DataUser user;



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
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
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

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
            case 8:

                break;
            case 9:

                break;
            case 10:

                break;
            case 11:

                break;
            case 12:

                break;
            case 13:

                break;
            case 14:

                break;
            case 15:

                break;

            default:
                System.out.println("Ongeldige keuze, Probeer opnieuw.");
        }

    }

    private static void viewPortfolio(Scanner scanner) {
        clearScreen();

        boolean running = true;

        while (running) {
            clearScreen();
            System.out.println("totaal portofeuille: " + user.totalePortofolio());
            System.out.println();
            System.out.println("1. aandelen");
            System.out.println("2. crypto");
            System.out.println("3. obligaties");
            System.out.println("4. eigen kapitaalvormen");
            System.out.println("5. spaargeld");
            System.out.println("6. terug naar hoofdmenu");
            System.out.println("Wat wilt u bekijken?");

            int keuze =scanner.nextInt();


            switch(keuze) {

                case 1 :
                    clearScreen();
                    user.getTotalaandeel();
                    scanner.nextLine();
                    break;

                case 2 :
                    clearScreen();
                    user.getTotaleCrypto();
                    break;

                case 3:
                    clearScreen();
                    user.getTotalobligatie();
                    break;

                case 4 :
                    clearScreen();
                    user.getTotaldiverse();
                    break;

                case 5 :
                    clearScreen();
                    System.out.println("Spaargeld: $" + user.getTotalspaargeld());
                    break;

                case 6:
                    clearScreen();
                    running = false;
                    break;

                default:
                    System.out.println("Ongeldige keuze. Probeer opnieuw.");
                    break;
            }
            terugNaarHoofdmenu();
        }
    }

    private static void addAandeel(Scanner scanner) {
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

    private static void addCrypto(Scanner scanner) {
        System.out.println("Welke crypto wilt u toevoegen?");
        String naam = scanner.nextLine();
        System.out.println("Hoeveel wilt u toevoegen?");
        int aantal = scanner.nextInt();
        System.out.println("wat was de waarde?");
        double waarde = scanner.nextDouble();
        System.out.println("Hoeveel heeft u ervoor betaalt?");
        double prijsAankoop = scanner.nextDouble();
        IinvesteeringsVorm crypto = factoryInvesteeringsVorm.createInvesteeringsVorm("crypto", naam, waarde, aantal, prijsAankoop);
        user.addCrypto(crypto);
        System.out.println("Succesvol toegevoegd");
        terugNaarHoofdmenu();
    }

    private static void addObligatie(Scanner scanner) {
        System.out.println("Welke obligatie wilt u toevoegen?");
        String naam = scanner.nextLine();
        System.out.println("Hoeveel obligaties wilt u toevoegen?");
        int aantal = scanner.nextInt();
        System.out.println("wat was de waarde?");
        double prijs = scanner.nextDouble();
        System.out.println("Hoeveel heeft u ervoor betaalt?");
        double prijsAankoop = scanner.nextDouble();
        IinvesteeringsVorm obligatie = factoryInvesteeringsVorm.createInvesteeringsVorm("obligatie", naam, prijs, aantal, prijsAankoop);
        user.addObligatie(obligatie);
        terugNaarHoofdmenu();
    }

    private static void addDiverse(Scanner scanner) {
        System.out.println("Welke diverse wilt u toevoegen?");
        String naam = scanner.nextLine();
        System.out.println("Hoeveel stuks wilt u toevoegen?");
        int aantal = scanner.nextInt();
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

    private static void verwijderAandeel(Scanner scanner) {
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

    private static void verwijderCrypto(Scanner scanner) {
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

    private static void verwijderobligatie (Scanner scanner) {
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

    private static void verwijderdiverse (Scanner scanner) {
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

    private static void addspaargeld(Scanner scanner) {
        System.out.println("Hoeveel Euro  wilt u toevoegen?");
        double aantal = scanner.nextDouble();
        System.out.println("Succesvol toegevoegd");
        user.addSpaargeld(aantal);
        terugNaarHoofdmenu();
    }

    private static void setSpaargeld (Scanner scanner) {
        System.out.println("Voer uw nieuw spaargeld bedrag: (0,00) of voer 0 in om terug te gaan");
        double bedrag = scanner.nextDouble();
        if (bedrag == 0) {
            terugNaarHoofdmenu();
        } else {
            Spaargeld spaargeld = new Spaargeld(bedrag);
            user.setSpaarGeld(spaargeld);
            System.out.println("Succecvol bewerkt");
            terugNaarHoofdmenu();
        }
    }



    private static void berekenrente(Scanner scanner) {
        System.out.println("Hoeveel jaar gaat u het geld sparen");
        int jaar = scanner.nextInt();
        double renteBedrag = user.berekenrente(jaar);
        System.out.printf("U gaat $ %.2f aan rente ontvangen %n", renteBedrag);
        terugNaarHoofdmenu();
    }
}











