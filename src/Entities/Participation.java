/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Jasser
 */
public class Participation {
    private int id ; 
    private int ID_USERS ;
    private String nom ;
    private int inevent ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getID_USERS() {
        return ID_USERS;
    }

    public void setID_USERS(int ID_USERS) {
        this.ID_USERS = ID_USERS;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getInevent() {
        return inevent;
    }

    public void setInevent(int inevent) {
        this.inevent = inevent;
    }

    public Participation() {
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", ID_USERS=" + ID_USERS + ", nom=" + nom + ", inevent=" + inevent + '}';
    }
    

}