/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DataBase.dbconnectioninfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.club;
import model.demandeadhesion;

/**
 *
 * @author benour
 */
public class cruddemande {
    
            public ObservableList<demandeadhesion> data2 = FXCollections.observableArrayList();

    public ObservableList afficherdemande(){
         try {
            Connection ds=dbconnectioninfo.getInstance().getCnx();
  String sql= " select * from demandeadhesion ";
   PreparedStatement stat = ds.prepareStatement(sql);
   ResultSet rs = stat.executeQuery();
            while(rs.next())
            {    
                demandeadhesion d=new demandeadhesion();
              
               d.setId(rs.getInt(1));
               d.setDureInsc(rs.getString(2));
               d.setModePayment(rs.getString(3));
               d.setEtatDemande(rs.getInt(4));
               d.setNumParent(rs.getInt(5));
               d.setAncienClub(rs.getString(6));
               d.setObjectif(rs.getString(7));
               d.setClubId(rs.getInt(8));
                       
                    data2.add(d);
                    }   
        } catch (SQLException ex) {
            Logger.getLogger(crudclub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data2;
    }
    
}
