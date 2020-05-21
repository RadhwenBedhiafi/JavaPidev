/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.util.Date;
import javafx.scene.control.Button;


/**
 *
 * @author COMPUTER
 */
public class Reclamation {
    
    int id;
    String destinataire,description;
    int idUser;
    String username;
    String email;
    Date date;
    int etat;
    Button edit;
    Button accepter;
    
    
    

    public Reclamation(int id, String destinataire, String description, int idUser,String username,String email, Date date, int etat, Button edit, Button accepter) {
        this.id = id;
        this.destinataire = destinataire;
        this.description = description;
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.date = date;
        this.etat = etat;
        this.edit = edit;
        this.accepter = accepter;
        
      /* edit.setOnAction(e -> {
            for (Reclamation reclamation : controllers.ReclamationController.oc ){
                if (reclamation.getEdit() == edit) {
                    System.out.println("id: "+reclamation.getId());
                    System.out.println("destinataire: "+reclamation.getDestinataire());
                    System.out.println("description: "+reclamation.getDescription());
                    System.out.println("date: "+reclamation.getDate());
                    System.out.println("etat: "+reclamation.getEtat());

                    }
            }
        
        });*/

       
        
    }

    public Reclamation(String destinataire,String description, Date date) {
        this.description = description;
        this.destinataire = destinataire;
        this.date = date;
    }

    public Reclamation(String destinataire, String description) {
        this.destinataire = destinataire;
        this.description = description;
        
    }

    public Reclamation(int id, String destinataire, String description, int etat) {
        this.id = id;
        this.destinataire = destinataire;
        this.description = description;
        this.etat = etat;
    }

    public Reclamation(String destinataire, String description, int idUser) {
        this.destinataire = destinataire;
        this.description = description;
        this.idUser = idUser;
    }
    
    
    
    

    public Reclamation() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Button getEdit() {
        return edit;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }

    public Button getAccepter() {
        return accepter;
    }

    public void setAccepter(Button accepter) {
        this.accepter = accepter;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", destinataire=" + destinataire + ", description=" + description + ", idUser=" + idUser + ", username=" + username + ", email=" + email + ", date=" + date + ", etat=" + etat + ", edit=" + edit + ", accepter=" + accepter + '}';
    }
    
    

  
    

    

 
    
    

   
    
    

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        return hash;
    }

   
    
    
}
