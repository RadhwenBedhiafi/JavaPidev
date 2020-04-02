/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benour
 */
public class dbconnectioninfo {
    Connection cnx;
        String url="jdbc:mysql://localhost/jardinenfant";
        String login="root";
        String pwd="";
        static dbconnectioninfo ds;

    private dbconnectioninfo() {
            try {
                cnx= DriverManager.getConnection(url,login, pwd);
                System.out.println("Connection avec succes");
            } catch (SQLException ex) {
                Logger.getLogger(dbconnectioninfo.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public static dbconnectioninfo getInstance(){
        if(ds==null)
        {
            ds= new dbconnectioninfo();
        }
        
        return ds;
    }

    public Connection getCnx() {
        return cnx;
    }
    
        
    
    
}
