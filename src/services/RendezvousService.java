/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DataBase.DataSource;
import entities.Mailing;
import entities.Rendezvous;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;




/**
 *
 * @author COMPUTER
 */
public class RendezvousService {
    
    Connection connexion;

    public RendezvousService() {
    connexion=DataSource.getInstance().getCnx();

    }
    
    ObservableList options = FXCollections.observableArrayList();
     
    
      public void ajouterRendezvous(Rendezvous r) throws SQLException {
        String req = "INSERT INTO rendezvous (idR,date, lieu,heure) VALUES (?, ?,?,?) ";
        String requete = "UPDATE reclamation set etat = 1 where id = ? ";
        String req1 = "UPDATE rendezvous set email = (select email from reclamation where id = ?) where idR = ?";
        String req2 ="UPDATE rendezvous set username = (select username from reclamation where id = ?) where idR = ?";
        
        //java.sql.Date sqlDate = new java.sql.Date(r.getDate().getTime());
        java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
       
        PreparedStatement pstm = connexion.prepareStatement(req);
        PreparedStatement pstme = connexion.prepareStatement(requete);
        PreparedStatement pstme1 = connexion.prepareStatement(req1);
                PreparedStatement pstme2 = connexion.prepareStatement(req2);
        pstm.setInt(1, r.getIdR());
        pstm.setString(2, r.getDate());
        pstm.setString(3, r.getLieu());
        pstm.setString(4, r.getHeure());
        pstme.setInt(1, r.getIdR());
        pstme1.setInt(1, r.getIdR());
        pstme1.setInt(2, r.getIdR());
        pstme2.setInt(1, r.getIdR());
        pstme2.setInt(2, r.getIdR());
    
       // pstm.setDate(3, sqlDate);
       //r.setEdit(button);
        pstm.executeUpdate();
        pstme1.executeUpdate();
        pstme2.executeUpdate();
        pstme.executeUpdate();
        
          
         
      
        
    }
      
      
                      ObservableList<Rendezvous> oc = FXCollections.observableArrayList();
        public ObservableList afficherRendezvous()  {
            Date d=new Date(0);    
            //Button edit = new Button("edit");
            String req= " select * from rendezvous ";
            Statement st;
            
         try {
            st=connexion.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next())
            {    
                Rendezvous e=new Rendezvous();   
               e.setId(rs.getInt(1));
                    e.setIdR(rs.getInt(2));
                    e.setDate(rs.getString(3));
                    e.setHeure(rs.getString(4));
                    e.setLieu(rs.getString(5));
                    e.setUsername(rs.getString(6));
                    e.setEmail(rs.getString(7));
                    Button edit = new Button("edit");
                    
                    e.setEdit(edit);
                      edit.setOnAction(s -> {
            
                if (e.getEdit() == edit) {
                    System.out.println("id: "+e.getId());
                    Integer a = e.getId();
                    System.out.println("idR: "+e.getIdR());
                    String b = e.getLieu();
                    System.out.println("Lieu: "+e.getLieu());
                    String date = e.getDate();
                    System.out.println("date: "+e.getDate());                   
                    System.out.println("Heure: "+e.getHeure());
                    String heure = e.getHeure();
                    System.out.println("username: "+e.getUsername());
                    System.out.println("email: "+e.getEmail());

                   
                    String reqe = "UPDATE rendezvous set lieu =? , date =?, heure =? where id = ?  ";
                    try {
                        PreparedStatement pstm = connexion.prepareStatement(reqe);
                            pstm.setString(1, b);
                            pstm.setString(2, date);
                            pstm.setString(3, heure);
                            pstm.setInt(4, a);
                            pstm.executeUpdate();
                             
        Mailing.send(e.getEmail(), "Rendezvous Modifié", "Monsieur ou Madame: "+ e.getUsername()+"\n On vous informe que votre rendezvous a été changé et "
        + "ça sera \n le: "+e.getDate()+" à: " +e.getHeure()+"\n Lieu: "+ e.getLieu() +"\n nous sommes désolé pour ce changement\n Cordialement," , "elitekids560@gmail.com", "ELITEJARDIN560");
Notifications h = Notifications.create()
                                            .title("Rendezvous")
                                            .text("Rendezvous Modifié avec succés")
                                            .graphic(null)
                                            .position(Pos.TOP_CENTER)
                                            .hideAfter(Duration.seconds(3));
                             h.showInformation();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    

                    }
                      
        
        });


                                       oc.add(e);
            }
  } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oc;
    
    
}
        
        
          public List<Rendezvous> rechercheRendezvous(String type, String valeur) {
        List<Rendezvous> myList = new ArrayList<Rendezvous>();
        String requete = null;
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            if (type.equals("id")) {
                requete = "SELECT * from rendezvous where id like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("idR")) {
                requete = "SELECT * from rendezvous where idR like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("date")) {
                requete = "SELECT * from rendezvous where date like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("lieu")) {
                requete = "SELECT * from rendezvous where lieu like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("username")) {
                requete = "SELECT * from rendezvous where username like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("heure")) {
                requete = "SELECT * from rendezvous where heure like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("email")) {
                requete = "SELECT * from rendezvous where email like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Tout")) {
                requete = "SELECT * from rendezvous where id like '%" + valeur + "%' or idR like '%" + valeur + "%' or date like '%" + valeur + "%' or lieu like '%" + valeur + "%'"
                       + "or username like '%" + valeur + "%' or email like '%" + valeur + "%' or heure like '%" + valeur + "%' "; //MAJUSCULE NON OBLIGATOIRE 
            }

            Statement pst = DataSource.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Rendezvous R = new Rendezvous();
                R.setId(rs.getInt(1));
                R.setIdR(rs.getInt(2));
                R.setDate(rs.getString(3));
                R.setHeure(rs.getString(4));
                R.setLieu(rs.getString(5));
                R.setUsername(rs.getString(6));
                R.setEmail(rs.getString(7));
                  Button edit = new Button("edit");
                    
                    R.setEdit(edit);
                      edit.setOnAction(s -> {
            
                if (R.getEdit() == edit) {
                    System.out.println("id: "+R.getId());
                    Integer a = R.getId();
                    System.out.println("idR: "+R.getIdR());
                    String b = R.getLieu();
                    System.out.println("Lieu: "+R.getLieu());
                    System.out.println("date: "+R.getDate());
                    String date = R.getDate();
                    System.out.println("heure: "+R.getHeure());
                    String heure = R.getHeure();
                    System.out.println("username: "+R.getUsername());
                    System.out.println("email: "+R.getEmail());

                    String reqe = "UPDATE rendezvous set lieu =?, date=?,heure=? where id = ?  ";
                    try {
                        PreparedStatement pstm = connexion.prepareStatement(reqe);
                            pstm.setString(1, b);
                            pstm.setString(2, date);
                            pstm.setString(3, heure);
                            pstm.setInt(4, a);
                            pstm.executeUpdate();
                            
            Mailing.send(R.getEmail(), "Rendezvous Modifié", "Monsieur ou Madame: "+ R.getUsername()+"\n On vous informe que votre rendezvous a été changé et "
                    + "ça sera \n le: "+R.getDate()+ " à: " +R.getHeure()+"\n Lieu: "+ R.getLieu() +"\n nous sommes désolé pour ce changement \n Cordialement" , "elitekids560@gmail.com", "ELITEJARDIN560");
            Notifications h = Notifications.create()
                                            .title("Rendezvous")
                                            .text("Rendezvous Modifié avec succés")
                                            .graphic(null)
                                            .position(Pos.TOP_CENTER)
                                            .hideAfter(Duration.seconds(3));
                             h.showInformation();
                    } catch (SQLException ex) {
                        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    

                    }
                      
        
        });

                  myList.add(R);
              

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }
          
                 public void supprimerRendezvous(Integer r) {
        try {
            String requete = "delete from `rendezvous` where id=?";
            PreparedStatement ps;
            ps = DataSource.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, r);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
                
                
}
