/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesenfant.Entities;

/**
 *
 * @author elbaz
 */
public class Classe {
    private int id;
    private String bloc;
    private String libelle;
    private int taillemax;

    public Classe(int id, String bloc, String libelle, int taillemax) {
        this.id = id;
        this.bloc = bloc;
        this.libelle = libelle;
        this.taillemax = taillemax;
    }

    public Classe() {
    }

    public Classe(String bloc, String libelle, int taillemax) {
        this.bloc = bloc;
        this.libelle = libelle;
        this.taillemax = taillemax;
    }

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

    public int getTaillemax() {
        return taillemax;
    }

    public void setTaillemax(int taillemax) {
        this.taillemax = taillemax;
    }

    @Override
    public String toString() {
        return "Classe{" + "id=" + id + ", bloc=" + bloc + ", libelle=" + libelle + ", taillemax=" + taillemax + '}';
    }
    
}
