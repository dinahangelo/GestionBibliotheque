/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.classe;

/**
 *
 * @author solofonirina
 */
public class Eleve {
    private String numeroMatricule;
    private String nom;

    public Eleve(String numeroMatricule, String nom) {
        this.numeroMatricule = numeroMatricule;
        this.nom = nom;
    }

    public String getNumeroMatricule() {
        return numeroMatricule;
    }

    public void setNumeroMatricule(String numeroMatricule) {
        this.numeroMatricule = numeroMatricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
