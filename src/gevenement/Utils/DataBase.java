/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gevenement.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author elbaz
 */
public class DataBase {
    private static DataBase instance;
    private Connection connexion;
    private String url = "JDBC:mysql://localhost/jardinenfant?useSSL=false";
    private String user = "root";
    private String password = "";
    
    private DataBase(){
          try {
            connexion = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Probleme de connexion");
        }
    }
    
    public static DataBase getInstance(){
         if (DataBase.instance == null) {
            DataBase.instance = new DataBase();
        }
        return DataBase.instance;  
    }
    
    public Connection getConnexion() {
        return connexion;
    }
}
