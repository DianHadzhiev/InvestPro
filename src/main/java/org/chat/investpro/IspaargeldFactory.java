package org.chat.investpro;

public interface IspaargeldFactory {

    IspaarGeld createIspaarGeld(String naamValuta, double rente, double aantal);

}
