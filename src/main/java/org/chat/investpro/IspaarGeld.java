package org.chat.investpro;

import lombok.Data;

/**
 * Lombok is een library die standaard getters setters en toString methodes heeft. Met @Data kan je die methodes voor heel de class gebruiken.
 */
@Data
public  abstract class IspaarGeld {

    protected double aantal;

    public IspaarGeld(double aantal) {
        this.aantal = aantal;
    }
}
