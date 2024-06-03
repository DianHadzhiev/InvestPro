package org.chat.investpro;

public class ConcreteSpaargeld implements IspaargeldFactory{
    @Override
    public IspaarGeld createIspaarGeld(double aantal) {
        return new SpaarGeld(aantal);
    }
}
