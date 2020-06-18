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
public class commentaire {
    private int id ; 
    private int ID_USERS ;
    private String commentaire ;
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

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getInevent() {
        return inevent;
    }

    public void setInevent(int inevent) {
        this.inevent = inevent;
    }

    public commentaire() {
    }

    @Override
    public String toString() {
        return "commentaire{" + "id=" + id + ", ID_USERS=" + ID_USERS + ", commentaire=" + commentaire + ", inevent=" + inevent + '}';
    }
    

    
    
    
    
}
