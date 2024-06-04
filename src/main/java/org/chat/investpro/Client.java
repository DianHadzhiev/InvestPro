package org.chat.investpro;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    private static DataManager dataManager;
    private static IinvesteeringsVormFactory factoryInvesteeringsVorm = new ConcreteFactory();
    private static IspaargeldFactory spaargeldFactory = new ConcreteSpaargeld();
    private DataUser user = DataUser.getInstance();;
    static Scanner scanner;
    private static boolean running = true;
    private static boolean runningChoice = true;
    private static Menu menu;
    private static MenuActions menuActions;

    public Client () {
        scanner = new Scanner(System.in);
        dataManager = new DataManager(factoryInvesteeringsVorm, spaargeldFactory, user);
        menu = new Menu();
        menuActions = new MenuActions(menu);
    }

    private static void terugNaarHoofdmenu(){
        System.out.println("Druk op Enter om terug te gaan naar het hoofdmenu.");
        scanner.nextLine();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void menuStart() {
        while (running) {
            menu.displayMainMenu();
            System.out.println("Voer uw keuze in:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            menuActions.executeMainMenuAction(choice, dataManager, scanner);
        }
    }


    private static void viewPortfolio() {
        clearScreen();
        while (runningChoice) {
            clearScreen();
            System.out.println("totaal portofeuille: " + dataManager.getUser().totalePortofolio());
            menu.viewPortofolioMenu();
            System.out.println("Voer uw keuze in:");
            var keuze = scanner.nextInt();
            scanner.nextLine();
            maakPortofolioKeuze(keuze);
        }
    }

    public static void maakPortofolioKeuze(int keuze ) {
        switch(keuze) {
            case 1 -> System.out.println("Totaal aandeel: " + dataManager.getUser().getTotalaandeel());
            case 2 -> System.out.println("Totaal crypto: " + dataManager.getUser().getTotaleCrypto());
            case 3 -> System.out.println(dataManager.getUser().getTotalobligatie());
            case 4 -> System.out.println(dataManager.getUser().getTotaldiverse());
            case 5 -> System.out.println("Spaargeld: $" + dataManager.getUser().getTotalspaargeld());
            case 6 -> runningChoice = false;
            default -> System.out.println("Ongeldige keuze. Probeer opnieuw.");
        }
        terugNaarHoofdmenu();
    }


}