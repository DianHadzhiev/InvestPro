package org.chat.investpro;

public class ConcreteFactory implements IinvesteeringsVormFactory{
    @Override
    public IinvesteeringsVorm createInvesteeringsVorm(String type, String naam, double waardeBijaankoop, double aantal, double aankoopPrijs) {
        switch (type.toLowerCase()) {
            case "crypto":
                return new Crypto(naam, waardeBijaankoop, aantal, aankoopPrijs);
            case "aandeel":
                return new Aandeel(naam, waardeBijaankoop, aantal, aankoopPrijs);
            case "obligatie":
                return new Obligatie(naam, waardeBijaankoop, aantal, aankoopPrijs);
            case "diverse":
                return new Diverse(naam, waardeBijaankoop, aantal, aankoopPrijs);
            default:
                throw new IllegalArgumentException("onbekende type: " + type);
        }
    }
}
