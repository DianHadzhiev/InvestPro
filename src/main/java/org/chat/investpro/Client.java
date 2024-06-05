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
    private static PortofolioMenuActions portMenu;

    public Client () {
        scanner = new Scanner(System.in);
        dataManager = new DataManager(factoryInvesteeringsVorm, spaargeldFactory, user, scanner);
        menu = new Menu();
        menuActions = new MenuActions(menu);
        portMenu = new PortofolioMenuActions(menu);
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
            if (choice == 1) viewPortfolioMenu();
            else menuActions.executeMainMenuAction(choice, dataManager, scanner);
        }
    }

    private static void viewPortfolioMenu() {
        clearScreen();
        while (runningChoice) {
            clearScreen();
            System.out.println("totaal portofeuille: " + dataManager.getUser().totalePortofolio());
            menu.viewPortofolioMenu();
            System.out.println("Voer uw keuze in:");
            var keuze = scanner.nextInt();
            scanner.nextLine();
            portMenu.executePortofolioAction(keuze, dataManager, scanner);
        }
    }
}