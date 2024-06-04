package org.chat.investpro;

public class Menu {

    public Menu() {
    }

    public void displayMainMenu() {
        System.out.println("<*-------InvestPro------*>");
        System.out.println();
        System.out.println("1. Bekijk portofolio");
        System.out.println();
        System.out.println("2. Voeg aandeel toe");
        System.out.println("3. Verwijder aandeel");
        System.out.println();
        System.out.println("4. Voeg crypto toe");
        System.out.println("5. Verwijder crypto");
        System.out.println();
        System.out.println("6. Voeg obligatie toe");
        System.out.println("7. Verwijder obligatie");
        System.out.println();
        System.out.println("8. Voeg spaargeld toe");
        System.out.println("9. bewerk spaargeld");
        System.out.println();
        System.out.println("10. bereken dividend belastingbedrag");
        System.out.println("11. bereken rente uit spaargeld");
        System.out.println();
        System.out.println("12. Voeg diverse toe");
        System.out.println("13. verwijder eigen diverse");
        System.out.println();
        System.out.println("14. sluit af");
    }

    public void viewPortofolioMenu() {
        System.out.println("<*-------Portofolio------*>");
        System.out.println();
        System.out.println("1. aandelen");
        System.out.println("2. crypto");
        System.out.println("3. obligaties");
        System.out.println("4. diverse");
        System.out.println("5. spaargeld");
        System.out.println("6. terug naar hoofdmenu");
        System.out.println("Wat wilt u bekijken?");
    }

}