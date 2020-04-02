/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DataBase.dbconnectioninfo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.club;

/**
 *
 * @author benour
 */
public class crudclub {
    
    
    ObservableList<club> data = FXCollections.observableArrayList();
    public ObservableList afficherclub(){
         try {
            Connection ds=dbconnectioninfo.getInstance().getCnx();
  String sql= " select * from club ";
   PreparedStatement stat = ds.prepareStatement(sql);
   ResultSet rs = stat.executeQuery();
            while(rs.next())
            {    
                club e=new club();
               e.setId(rs.getInt(1));
                       e.setNomclub(rs.getString(2));
                       e.setDescription(rs.getString(3));
                       e.setHorraire(rs.getString(4));
                    e.setTarif(rs.getInt(5));
                    e.setImage(rs.getString(6));
                    e.setCapacite(rs.getInt(7));
                    data.add(e);
                    }   
        } catch (SQLException ex) {
            Logger.getLogger(crudclub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public static int save(club cl){
        int st = 0;
        try{
        String sql = "INSERT INTO club(nomclub,description,horraire,tarif,image,capacite) VALUES(?,?,?,?,?,?)";
        Connection ds=dbconnectioninfo.getInstance().getCnx();
        PreparedStatement stat;
        stat = ds.prepareStatement(sql);
        stat.setString(1, cl.getNomclub());
        stat.setString(2, cl.getDescription());
        stat.setString(3, cl.getHorraire());
        stat.setInt(4, cl.getTarif());
        stat.setString(5, cl.getImage());
        stat.setInt(6, cl.getCapacite());
        st = stat.executeUpdate();
        //ds.close();
        }catch(Exception e){
        e.printStackTrace();
        }
        
        
        
        
        return st;
    
    }
    
    public static int update(club cl){
        int st = 0;
        try{
        String sql = " UPDATE club SET nomclub=?, description=?, horraire=?, tarif=?, image=?, capacite=? WHERE id=?";
        Connection ds=dbconnectioninfo.getInstance().getCnx();
        PreparedStatement stat;
        stat = ds.prepareStatement(sql);
        stat.setString(1, cl.getNomclub());
        stat.setString(2, cl.getDescription());
        stat.setString(3, cl.getHorraire());
        stat.setInt(4, cl.getTarif());
        stat.setString(5, cl.getImage());
        stat.setInt(6, cl.getCapacite());
        stat.setInt(7, cl.getId());
        st = stat.executeUpdate();
        //ds.close();
        }catch(SQLException e){
        e.printStackTrace();
        }
        
        return st;
         
}
    public static int delete(int id){
        int st = 0;
        try{
        String sql = " DELETE FROM club WHERE id=?";
        Connection ds=dbconnectioninfo.getInstance().getCnx();
        PreparedStatement stat;
        stat = ds.prepareStatement(sql);
        stat.setInt(1, id);
        st = stat.executeUpdate();
        //ds.close();
        }
        catch(SQLException e){
        e.printStackTrace();
        }
        return 0;
        
    
    }
    public static club getclubId(int id){
        club cl = new club();
        try{
        String sql = "SELECT * FROM club WHERE id=?";  
        Connection ds=dbconnectioninfo.getInstance().getCnx();
        PreparedStatement stat;
        stat = ds.prepareStatement(sql);
        stat.setInt(1, id);
        ResultSet rst = stat.executeQuery();
        if(rst.next()){
        cl.setId(rst.getInt(1));
        cl.setNomclub(rst.getString(2));
        cl.setDescription(rst.getString(3));
        cl.setHorraire(rst.getString(4));
        cl.setTarif(rst.getInt(5));
        cl.setImage(rst.getString(6));
        cl.setCapacite(rst.getInt(7));
        }
        }catch(SQLException e){
        e.printStackTrace();
        }
        return cl;
}
    public club findById(Integer id) {
         club e= null;
        try {
            Connection ds=dbconnectioninfo.getInstance().getCnx();
            String sql = "select * from club where Id =?";
             
   PreparedStatement stat = ds.prepareStatement(sql);
   stat.setInt(1, id);
   ResultSet rs = stat.executeQuery();
            
            
            while (rs.next()) {
               e = new club(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getInt(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(crudclub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;  
    }

    public ArrayList<club> findByNom(String nom) {
              ArrayList<club> psr = new ArrayList<>();
        try {
            Connection ds=dbconnectioninfo.getInstance().getCnx();
            String sql = "select * from club where nom =?";
             PreparedStatement stat = ds.prepareStatement(sql);
            stat.setString(1, nom);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                       club p = new club();
                p.setNomclub(rs.getString(1));
                p.setDescription(rs.getString(2));
        p.setHorraire(rs.getString(3));
        p.setTarif(rs.getInt(4));
        p.setImage(rs.getString(5));
        p.setCapacite(rs.getInt(6));
                
                psr.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(crudclub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return psr;  
    }
    
}
