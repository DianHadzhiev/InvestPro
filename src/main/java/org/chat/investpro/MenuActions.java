package org.chat.investpro;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuActions {
    private final Map<Integer, MenuAction> MainMenuActions = new HashMap<>();

    private final Menu menu;

    public MenuActions(Menu menu) {
        putMainMenuActions();
        this.menu = menu;
    }

    public void putMainMenuActions() {
        //MainMenuActions.put(1, (scanner, dataManager) -> menu.viewPortofolioMenu());
        MainMenuActions.put(2, (scanner, dataManager) -> dataManager.addVorm(scanner,"aandeel"));
        MainMenuActions.put(3, (scanner, dataManager) -> dataManager.verwijderVorm(scanner,"aandeel"));
        MainMenuActions.put(4, (scanner, dataManager) -> dataManager.addVorm(scanner,"crypto"));
        MainMenuActions.put(5, (scanner, dataManager) -> dataManager.verwijderVorm(scanner,"crypto"));
        MainMenuActions.put(6, (scanner, dataManager) -> dataManager.addVorm(scanner,"obligatie"));
        MainMenuActions.put(7, (scanner, dataManager) -> dataManager.verwijderVorm(scanner,"obligatie"));
        MainMenuActions.put(8, (scanner, dataManager) -> dataManager.addspaargeld(scanner));
        MainMenuActions.put(9, (scanner, dataManager) -> dataManager.setSpaargeld(scanner));
        MainMenuActions.put(10, (scanner, dataManager) -> dataManager.berekendividend(scanner));
        MainMenuActions.put(11, (scanner, dataManager) -> dataManager.berekenRente(scanner));
        MainMenuActions.put(12, (scanner, dataManager) -> dataManager.addVorm(scanner,"diverse"));
        MainMenuActions.put(13, (scanner, dataManager) -> dataManager.verwijderVorm(scanner,"diverse"));
        MainMenuActions.put(14, (scanner, dataManager) -> System.exit(0));
    }

    public void executeMainMenuAction(int choice, DataManager dataManager, Scanner scanner) {
        MenuAction action = MainMenuActions.get(choice);
        if (action != null) {
            action.execute(scanner, dataManager);
        } else {
            System.out.println("Ongeldige keuze, probeer opnieuw.");
        }
        scanner.nextLine();
    }

}
