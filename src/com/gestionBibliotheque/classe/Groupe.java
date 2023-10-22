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
public class Groupe {
    private String idGroupe;
    private String libelleGroupe;
    private String pfEtagere;
    private String idEtagere;

    public Groupe(String idGroupe, String libelleGroupe, String pfEtagere, String idEtagere) {
        this.idGroupe = idGroupe;
        this.libelleGroupe = libelleGroupe;
        this.pfEtagere = pfEtagere;
        this.idEtagere = idEtagere;
    }

    public Groupe(String libelleGroupe, String pfEtagere, String idEtagere) {
        this.libelleGroupe = libelleGroupe;
        this.pfEtagere = pfEtagere;
        this.idEtagere = idEtagere;
    }
    
    public String getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(String idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getLibelleGroupe() {
        return libelleGroupe;
    }

    public void setLibelleGroupe(String libelleGroupe) {
        this.libelleGroupe = libelleGroupe;
    }

    public String getPfEtagere() {
        return pfEtagere;
    }

    public void setPfEtagere(String pfEtagere) {
        this.pfEtagere = pfEtagere;
    }

    public String getIdEtagere() {
        return idEtagere;
    }

    public void setIdEtagere(String idEtagere) {
        this.idEtagere = idEtagere;
    }
    
}
