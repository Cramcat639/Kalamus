
package com.Marc.Kalamus.Modelo;


public class Ferengi extends Esser{
    private Float lantanium;

    public Ferengi(String nom, String especie,String planeta, Float lantanium) throws ExepcioAtribut {
        super(1, especie, nom, planeta);
        this.lantanium = lantanium;
        
        if(lantanium <0){
            throw new ExepcioAtribut("Error: l'or no pot ser negatiu.");
        }
    }

    public Float getLantanium() {
        return lantanium;
    }
    
}
