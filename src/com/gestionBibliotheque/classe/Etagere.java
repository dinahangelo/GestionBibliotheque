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
public class Etagere {
    private String idEtagere;
    private String libelleEtagere;

    public Etagere(String idEtagere, String libelleEtagere) {
        this.idEtagere = idEtagere;
        this.libelleEtagere = libelleEtagere;
    }

    public String getIdEtagere() {
        return idEtagere;
    }

    public void setIdEtagere(String idEtagere) {
        this.idEtagere = idEtagere;
    }

    public String getLibelleEtagere() {
        return libelleEtagere;
    }

    public void setLibelleEtagere(String libelleEtagere) {
        this.libelleEtagere = libelleEtagere;
    }
    
    
}
