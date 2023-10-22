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
public class Livre {
    private String codeLivre;
    private String libelleLivre;
    private String auteurLivre;
    private String nombrePage; 
    private int nombreLivre;
    private String groupe;
    private String type;
    private String etagere;

    public Livre(String codeLivre, String libelleLivre, String auteurLivre, String nombrePage, int nombreLivre,String groupe, String type) {
        this.codeLivre = codeLivre;
        this.libelleLivre = libelleLivre;
        this.auteurLivre = auteurLivre;
        this.nombrePage = nombrePage;
        this.nombreLivre = nombreLivre;
        this.groupe = groupe;
        this.type = type;
    }

    public Livre(String codeLivre, String libelleLivre, String auteurLivre, String nombrePage, int nombreLivre,String groupe, String type, String etagere) {
        this.codeLivre = codeLivre;
        this.libelleLivre = libelleLivre;
        this.auteurLivre = auteurLivre;
        this.nombrePage = nombrePage;
        this.nombreLivre = nombreLivre;
        this.groupe = groupe;
        this.type = type;
        this.etagere = etagere;
    }
      
    public String getCodeLivre() {
        return codeLivre;
    }

    public void setCodeLivre(String codeLivre) {
        this.codeLivre = codeLivre;
    }

    public String getLibelleLivre() {
        return libelleLivre;
    }

    public void setLibelleLivre(String libelleLivre) {
        this.libelleLivre = libelleLivre;
    }

    public String getAuteurLivre() {
        return auteurLivre;
    }

    public void setAuteurLivre(String auteurLivre) {
        this.auteurLivre = auteurLivre;
    }

    public String getNombrePage() {
        return nombrePage;
    }

    public void setNombrePage(String nombrePage) {
        this.nombrePage = nombrePage;
    }

    public int getNombreLivre() {
        return nombreLivre;
    }

    public void setNombreLivre(int nombreLivre) {
        this.nombreLivre = nombreLivre;
    }
    
    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtagere() {
        return etagere;
    }

    public void setEtagere(String etagere) {
        this.etagere = etagere;
    }
    
}
