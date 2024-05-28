package org.chat.investpro;

import java.util.Scanner;

public class Client {

    IinvesteeringsVormFactory factoryInvesteeringsVorm;
    IspaargeldFactory ispaargeldFactory;
    private DataUser userData;
    MainMenu menu = new MainMenu(userData);

    public Client () {
        userData = DataUser.getInstance();
    }

    }









