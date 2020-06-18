/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enfant.gui.entities;

import java.util.Objects;

/**
 *
 * @author elbaz
 */
public class Classe {
    private int id ; 
    private String bloc ;
    private String libelle ;
    private int effectif ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBloc() {
        return bloc;
    }

    public void setBloc(String bloc) {
        this.bloc = bloc;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getEffectif() {
        return effectif;
    }

    public void setEffectif(int effectif) {
        this.effectif = effectif;
    }

    public Classe() {
    }

    public Classe(int id, String bloc, String libelle, int effectif) {
        this.id = id;
        this.bloc = bloc;
        this.libelle = libelle;
        this.effectif = effectif;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.bloc);
        hash = 79 * hash + Objects.hashCode(this.libelle);
        hash = 79 * hash + this.effectif;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Classe other = (Classe) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.effectif != other.effectif) {
            return false;
        }
        if (!Objects.equals(this.bloc, other.bloc)) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        return true;
    }

    public Classe(String bloc, String libelle, int effectif) {
        this.bloc = bloc;
        this.libelle = libelle;
        this.effectif = effectif;
    }
    
}
