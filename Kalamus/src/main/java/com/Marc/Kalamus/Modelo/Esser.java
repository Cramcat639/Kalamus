
package com.Marc.Kalamus.Modelo;


public class Esser {
    protected Integer categoria;
    protected String especie;
    protected String nom;
    protected String planeta;

    public Esser(Integer categoria, String especie, String nom, String planeta) {
        this.categoria = categoria;
        this.especie = especie;
        this.nom = nom;
        this.planeta = planeta;
    }

    public String getEspecie() {
        return especie;
    }

    public String getPlaneta() {
        return planeta;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public String getNom() {
        return nom;
    }
    
}
