package org.chat.investpro;

public class ConcreteSpaargeld implements IspaargeldFactory{
    @Override
    public IspaarGeld createIspaarGeld(String naamValuta, double rente, double aantal) {
        return new SpaarGeld(naamValuta, rente, aantal);
    }
}
