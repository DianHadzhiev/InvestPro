package org.chat.investpro;

import lombok.Data;

/**
 * Lombok is een library die standaard getters setters en toString methodes heeft. Met @Data kan je die methodes voor heel de class gebruiken.
 */
@Data
public abstract class IinvesteeringsVorm {
    /**
     * waardeBijaankoop is de waarde van precies 1 aantal van het investeeringsvorm,
     * aankoopPrijs is hoeveel er betaald is bij het transactie.
     *
     */
    protected double aantal, waardeBijaankoop, aankoopPrijs;
    protected String naam;

    public IinvesteeringsVorm(String naam, double aantal, double waardeBijaankoop, double aankoopPrijs) {
        this.naam = naam;
        this.aantal = aantal;
        this.waardeBijaankoop = waardeBijaankoop;
        this.aankoopPrijs = aankoopPrijs;
    }

}
