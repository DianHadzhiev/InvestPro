package org.chat.investpro;

import java.util.Scanner;

public class Client {

    private static DataManager dataManager;
    static Scanner scanner;
    private static Menu menu;

    /**
     * client class to start up application
     */
    public Client () {
        scanner = new Scanner(System.in);
        dataManager = new DataManager(scanner);
        menu = new Menu();
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
        MenuActions menuActions = new MenuActions();
        boolean running = true;
        while (running) {
            clearScreen();
            menu.displayMainMenu();
            System.out.println("Voer uw keuze in:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) viewPortfolioMenu();
            else menuActions.executeMainMenuAction(choice, dataManager, scanner);
            terugNaarHoofdmenu();
        }
    }

    private static void viewPortfolioMenu() {
        PortofolioMenuActions portMenu = new PortofolioMenuActions();
        boolean runningChoice = true;
        clearScreen();
        while (runningChoice) {
            clearScreen();
            System.out.println("totaal portofeuille: " + dataManager.getUser().totalePortofolio());
            menu.viewPortofolioMenu();
            System.out.println("Voer uw keuze in:");
            var keuze = scanner.nextInt();
            scanner.nextLine();
            portMenu.executePortofolioAction(keuze, dataManager, scanner);
            if (keuze == 6) runningChoice = false;
        }
    }
}