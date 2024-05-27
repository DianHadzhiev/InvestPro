package org.chat.investpro;

import lombok.Data;

/**
 * Lombok is een library die standaard getters setters en toString methodes heeft. Met @Data kan je die methodes voor heel de class gebruiken.
 */
@Data
public  abstract class IspaarGeld {

    protected String naamValuta;
    protected double rentePerc, aantal;

    public IspaarGeld(String naamValuta, double rentePerc, double aantal) {
        this.naamValuta = naamValuta;
        this.rentePerc = rentePerc;
        this.aantal = aantal;

    }
}
