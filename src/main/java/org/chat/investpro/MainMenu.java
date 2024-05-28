package org.chat.investpro;

import java.util.Scanner;

public class MainMenu {

    private static DataUser user;
    public MainMenu(DataUser userData) {
        user = userData;
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
                    user.getTotalecrypto();
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
        double prijsAankoop = scanner.nextDouble();
        System.out.println("Hoeveel heeft u ervoor betaalt?");


        terugNaarHoofdmenu();
    }

    private static void addCrypto(Scanner scanner) {
        System.out.println("Welke crypto wilt u toevoegen?");
        String naam = scanner.nextLine();
        System.out.println("Hoeveel wilt u toevoegen?");
        int aantal = scanner.nextInt();
        System.out.println("wat was de waarde?");
        double waarde = scanner.nextDouble();
        Crypto crypto = new Crypto(naam,waarde,aantal);
        gebruiker.addCrypto(crypto); // hier dubbel in gedaan misschine later weg
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
        Obligatie obligatie = new Obligatie(naam, prijs, aantal);
        gebruiker.addObligatie(obligatie);
        scanner.nextLine();
        terugNaarHoofdmenu();
    }

    private static void addkapitaalvorm(Scanner scanner) {
        System.out.println("Welke kapitaalvorm wilt u toevoegen?");
        String naam = scanner.nextLine();
        System.out.println("Hoeveel stuks wilt u toevoegen?");
        int aantal = scanner.nextInt();
        System.out.println("wat was de waarde?");
        double prijs = scanner.nextDouble();
        Kapitaalvorm kapitaal = new Kapitaalvorm(naam, prijs,aantal);
        gebruiker.addkapitaalvorm(kapitaal);
        scanner.nextLine();
        System.out.println("Succesvol toegevoegd");
        terugNaarHoofdmenu();
    }

    private static void verwijderAandeel(Scanner scanner) {
        ArrayList <Aandeel> aandelen = gebruiker.getAandelen();
        int i = 1;
        if(aandelen == null) {
            System.out.println("U heeft geen aandelen");
            terugNaarHoofdmenu();
        }
        else {
            System.out.println("Welke aandeel wilt u verwijderen? of voer 0 in om terug te gaan");
            for (Aandeel aandeel : aandelen) {
                System.out.println(i + " " + aandeel.getNaam());
                i++;
            }
            System.out.println("Voer u keuze in: ");
            int keuze = scanner.nextInt();
            if (keuze == 0) { terugNaarHoofdmenu();
            } else {
                gebruiker.DelAandeel(keuze);
                terugNaarHoofdmenu();
            }
        }
    }

    private static void verwijderCrypto(Scanner scanner) {
        ArrayList <Crypto> crypto = gebruiker.getCrypto();
        int i = 1;
        System.out.println("Welke crypto wilt u verwijderen? of voer 0 in om terug te gaan");
        for (Crypto cryptos : crypto) {
            System.out.println(i + " " + cryptos.getNaam());
            i++;
        }
        System.out.println("Voer u keuze in: ");
        int keuze = scanner.nextInt();
        if (keuze == 0) { terugNaarHoofdmenu();
        } else {
            gebruiker.Delcrypto(keuze);
            terugNaarHoofdmenu();
        }
    }

    private static void verwijderobligatie (Scanner scanner) {
        System.out.println("Welke obligatie wilt u verwijderen? of voer 0 in om terug te gaan");
        ArrayList <Obligatie> obligaties = gebruiker.getObligaties();
        int i = 1;
        for (Obligatie obli : obligaties) {
            System.out.println(i + " " + obli.getNaam());
            i++;
        }
        System.out.println("Voer u keuze in: ");
        int keuze = scanner.nextInt();
        if (keuze == 0) { terugNaarHoofdmenu();
        } else {
            gebruiker.Delobligatie(keuze);
            terugNaarHoofdmenu();
        }
    }

    private static void verwijderKapitaal (Scanner scanner) {
        System.out.println("Welke Kapitaalvorm wilt u verwijderen?");
        ArrayList<Kapitaalvorm> kapitaal = gebruiker.getKapitaalvorms();
        int i = 1;
        for (Kapitaalvorm kapitaalvorm : kapitaal) {
            System.out.println(i + " " + kapitaalvorm.getNaam());
            i++;
        }
        System.out.println("Voer u keuze in of voer 0 in om terug te gaan");
        int keuze = scanner.nextInt();
        gebruiker.Delkapitaal(keuze);
        terugNaarHoofdmenu();
    }

    private static void addspaargeld(Scanner scanner) {
        System.out.println("Hoeveel Euro  wilt u toevoegen?");
        double aantal = scanner.nextDouble();
        System.out.println("Succesvol toegevoegd");
        gebruiker.addSpaarGeld(aantal);
        terugNaarHoofdmenu();
    }

    private static void setSpaargeld (Scanner scanner) {
        System.out.println("Voer uw nieuw spaargeld bedrag: (0,00) of voer 0 in om terug te gaan");
        double bedrag = scanner.nextDouble();
        if (bedrag == 0) {
            terugNaarHoofdmenu();
        } else {
            Spaargeld spaargeld = new Spaargeld(bedrag);
            gebruiker.setSpaarGeld(spaargeld);
            System.out.println("Succecvol bewerkt");
            terugNaarHoofdmenu();
        }
    }

    public double berekendividend(Double dividend) {
        double belasting = dividend * 0.15;
        return belasting;
    }

    private static void berekenrente(Scanner scanner) {
        System.out.println("Hoeveel jaar gaat u het geld sparen");
        int jaar = scanner.nextInt();
        double rentebedrag = gebruiker.berekenrente(jaar);
        System.out.printf("U gaat $ %.2f aan rente ontvangen %n", rentebedrag);
        terugNaarHoofdmenu();
    }
}

