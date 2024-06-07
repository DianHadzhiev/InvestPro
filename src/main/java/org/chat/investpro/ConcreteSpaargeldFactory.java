package org.chat.investpro;

public class ConcreteSpaargeldFactory implements IspaargeldFactory{
    @Override
    public IspaarGeld createIspaarGeld(double aantal) {
        return new SpaarGeld(aantal);
    }
}
