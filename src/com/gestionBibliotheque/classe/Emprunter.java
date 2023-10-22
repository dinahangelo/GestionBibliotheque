/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.classe;

import java.sql.Date;

/**
 *
 * @author solofonirina
 */
public class Emprunter {
    private int id;
    private String codeEmprunteur;
    private String codeLivre;
    private Date debutEmprunt;
    private Date finEmprunt;
    private String classe;
    private String anneeScolaire;
    private int nombreLivre;
    private String statut;

    public Emprunter(int id, String codeEmprunteur, String codeLivre, Date debutEmprunt, Date finEmprunt, String classe, String anneeScolaire, int nombreLivre, String statut) {
        this.id = id;
        this.codeEmprunteur = codeEmprunteur;
        this.codeLivre = codeLivre;
        this.debutEmprunt = debutEmprunt;
        this.finEmprunt = finEmprunt;
        this.classe = classe;
        this.anneeScolaire = anneeScolaire;
        this.nombreLivre = nombreLivre;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeEmprunteur() {
        return codeEmprunteur;
    }

    public void setCodeEmprunteur(String codeEmprunteur) {
        this.codeEmprunteur = codeEmprunteur;
    }

    public String getCodeLivre() {
        return codeLivre;
    }

    public void setCodeLivre(String codeLivre) {
        this.codeLivre = codeLivre;
    }

    public Date getDebutEmprunt() {
        return debutEmprunt;
    }

    public void setDebutEmprunt(Date debutEmprunt) {
        this.debutEmprunt = debutEmprunt;
    }

    public Date getFinEmprunt() {
        return finEmprunt;
    }

    public void setFinEmprunt(Date finEmprunt) {
        this.finEmprunt = finEmprunt;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public int getNombreLivre() {
        return nombreLivre;
    }

    public void setNombreLivre(int nombreLivre) {
        this.nombreLivre = nombreLivre;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
}
