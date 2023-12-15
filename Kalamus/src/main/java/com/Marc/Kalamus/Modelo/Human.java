
package com.Marc.Kalamus.Modelo;


public class Human extends Esser{
    private Integer edat;
    private String genere;

    public Human(String nom, String especie,String planeta, Integer edat, String genere) throws ExepcioAtribut {
       
        super(1, especie, nom, planeta);
        this.edat = edat;
        this.genere = genere;
        
         if(edat <0 || edat >130){
            throw new ExepcioAtribut("Error: Edat incorrecte");
        }
    }

    public Integer getEdat() {
        return edat;
    }

    public String getGenere() {
        return genere;
    }
    
}
