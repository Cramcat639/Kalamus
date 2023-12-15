
package com.Marc.Kalamus.Modelo;

import java.util.ArrayList;
import java.util.List;


public class Andorian extends Esser{
    private String rang;
    private boolean aenar;
    public static final ArrayList <String>POSSIBLES_RANGS = new ArrayList<>(List.of("entrenador", "defensor", "lluitador", "cavaller")) ;
    public Andorian(String nom, String especie , String planeta, String rang, boolean aenar) throws ExepcioAtribut {
        super(2, especie, nom, planeta);
        this.rang = rang;
        this.aenar = aenar;
        
        if(!POSSIBLES_RANGS.contains(rang)){
            throw new ExepcioAtribut("Error: Aquest rang no existeix.");
        }
    }

    public String getRang() {
        return rang;
    }

    public boolean isAenar() {
        return aenar;
    }

    public static ArrayList<String> getPOSSIBLES_RANGS() {
        return POSSIBLES_RANGS;
    }
    
}
