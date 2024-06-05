package org.chat.investpro;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PortofolioMenuActions {
    private final Map<Integer, PortofolioMenuAction> PortofolioActions = new HashMap<>();

    private final Menu menu;

    public PortofolioMenuActions(Menu menu) {
        putPortofolioActions();
        this.menu = menu;
    }

    public void putPortofolioActions() {
        PortofolioActions.put(1, (dataManager) -> dataManager.viewPortofolioVorm("aandeel"));
        PortofolioActions.put(2, (dataManager) -> dataManager.viewPortofolioVorm("crypto"));
        PortofolioActions.put(3, (dataManager) -> dataManager.viewPortofolioVorm("obligatie"));
        PortofolioActions.put(4, (dataManager) -> dataManager.viewPortofolioVorm("diverse"));
        PortofolioActions.put(5, (dataManager) -> dataManager.viewPortofolioVorm("spaargeld"));
        PortofolioActions.put(6, (dataManager) -> menu.displayMainMenu());
    }

    public void executePortofolioAction(int choice, DataManager dataManager, Scanner scanner) {
        PortofolioMenuAction action = PortofolioActions.get(choice);
        if (action != null) {
            action.execute(dataManager);
        } else {
            System.out.println("Ongeldige keuze, probeer opnieuw.");
        }
        scanner.nextLine();
    }
}
