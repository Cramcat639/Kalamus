
package com.Marc.Kalamus.Modelo;


public class Klingon extends Esser{
    private Integer força;

    public Klingon(String nom, String especie, String planeta, Integer força) throws ExepcioAtribut {
        super(3, especie, nom, planeta);
        this.força = força;
        
        if(força < 50 || força >350){
            throw new ExepcioAtribut("Error: El nivell de força no es correcte.");
        }
    }

    public Integer getForça() {
        return força;
    }
    
}
