package org.chat.investpro;


import lombok.Getter;

public class Aandeel extends IinvesteeringsVorm {

    public Aandeel(String naam, double aantal, double waardeBijaankoop, double aankoopPrijs) {
        super(naam, aantal, waardeBijaankoop, aankoopPrijs);
    }
}
