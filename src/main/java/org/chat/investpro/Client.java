package org.chat.investpro;

import java.util.Scanner;

public class Client {

    IinvesteeringsVormFactory factoryInvesteeringsVorm;
    IspaargeldFactory ispaargeldFactory;
    private DataUser userData;
    MainMenu menu = new MainMenu();;

    public Client () {
        userData = DataUser.getInstance();
    }

    public void menuStart() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            menu.printMenu();
            System.out.println("Voer uw keuze in:");
            int option = scanner.nextInt();
            scanner.nextLine();
        }
    }








}
