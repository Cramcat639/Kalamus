
package com.Marc.Kalamus.Modelo;


public class Vulcanian extends Esser{
    private Integer meditacio;

    public Vulcanian(String nom, String especie,String planeta, Integer meditacio) throws ExepcioAtribut {
        super(3, especie, nom, planeta);
        this.meditacio = meditacio;
        
        if(meditacio <0 || meditacio >10){
            throw new ExepcioAtribut("Error: nivell de meditació incorrecte.");
        }
    }

    public Integer getMeditacio() {
        return meditacio;
    }
}
