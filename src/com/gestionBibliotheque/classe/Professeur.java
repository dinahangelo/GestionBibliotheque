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
public class Professeur {
    private String codeProfesseur;
    private String nomProfesseur;

    public Professeur(String codeProfesseur, String nomProfesseur) {
        this.codeProfesseur = codeProfesseur;
        this.nomProfesseur = nomProfesseur;
    }

    public String getCodeProfesseur() {
        return codeProfesseur;
    }

    public void setCodeProfesseur(String codeProfesseur) {
        this.codeProfesseur = codeProfesseur;
    }

    public String getNomProfesseur() {
        return nomProfesseur;
    }

    public void setNomProfesseur(String nomProfesseur) {
        this.nomProfesseur = nomProfesseur;
    }
    
}
