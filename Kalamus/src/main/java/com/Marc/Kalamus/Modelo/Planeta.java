
package com.Marc.Kalamus.Modelo;

import java.util.ArrayList;
import java.util.List;


public class Planeta {
    private String nom;
    private String galaxia;
    private Integer capacitatMax;
    private String clima;
    private boolean floraVermella;
    private boolean essersAcuatics;
    private ArrayList <String>possiblesClimas = new ArrayList <>(List.of("calid", "temperat", "fred"));
    
    public Planeta(String nom, String galaxia, Integer capacitatMax, String clima, Boolean floraVermella, Boolean essersAcuatics) throws ExepcioAtribut {
        this.nom = nom;
        this.capacitatMax= capacitatMax;
        this.clima= clima;
        this.essersAcuatics= essersAcuatics;
        this.floraVermella= floraVermella;
        this.galaxia= galaxia;
        if(!possiblesClimas.contains(clima)){
            throw new ExepcioAtribut("Error: Aquest clima no existeix.");
        }
    }
    
     public String getNom() {
        return nom;
    }

    public Integer getCapacitatMax() {
        return capacitatMax;
    }

    public String getClima() {
        return clima;
    }

    public boolean isFloraVermella() {
        return floraVermella;
    }

    public boolean isEssersAcuatics() {
        return essersAcuatics;
    }

    public String getGalaxia() {
        return galaxia;
    }
     
}
