package org.chat.investpro;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PortofolioMenuActions {
    private final Map<Integer, PortofolioMenuAction> PortofolioActions = new HashMap<>();

    public PortofolioMenuActions() {
        putPortofolioActions();
    }

    public void putPortofolioActions() {
        PortofolioActions.put(1, (dataManager) -> dataManager.viewPortofolioVorm("aandeel"));
        PortofolioActions.put(2, (dataManager) -> dataManager.viewPortofolioVorm("crypto"));
        PortofolioActions.put(3, (dataManager) -> dataManager.viewPortofolioVorm("obligatie"));
        PortofolioActions.put(4, (dataManager) -> dataManager.viewPortofolioVorm("diverse"));
        PortofolioActions.put(5, DataManager::viewSpaargeld);
    }

    public void executePortofolioAction(int choice, DataManager dataManager, Scanner scanner) {
        PortofolioMenuAction action = PortofolioActions.get(choice);
        if (action != null) {
            action.execute(dataManager);
        }else if (choice == 6) {
            System.out.println();
        }
        else {
            System.out.println("Ongeldige keuze, probeer opnieuw.");
        }
    }
}
