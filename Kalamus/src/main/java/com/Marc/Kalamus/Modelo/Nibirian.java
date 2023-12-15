
package com.Marc.Kalamus.Modelo;


public class Nibirian extends Esser{
    private boolean vegetaria;

    public Nibirian(String nom, String especie,String planeta, boolean vegetaria) {
        super(2, especie, nom, planeta);
        this.vegetaria = vegetaria;
    }

    public boolean isVegetaria() {
        return vegetaria;
    }
    
}
