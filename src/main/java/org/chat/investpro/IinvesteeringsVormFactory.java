package org.chat.investpro;

public interface IinvesteeringsVormFactory {

    IinvesteeringsVorm createInvesteeringsVorm(String type, String naam, double waardeBijaankoop, double aantal, double aankoopPrijs);

}
