/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gevenement.Service;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import gevenement.Entities.Evenement;
import gevenement.Entities.Plan;
import gevenement.Entities.SendMail;
import gevenement.Utils.DataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class EvenementService {
    private final Connection connexion;
    private Statement ste;

       
       public EvenementService() {
       connexion=DataBase.getInstance().getConnexion();
       }
        public void ajouterEvenement(Evenement e) throws SQLException {
        String req = "INSERT INTO `evennements` (`sujet`, `date`, `lieu`, `description`, `titre`) VALUES ( ?, ?, ?, ?, ?) ";
        PreparedStatement pstm = connexion.prepareStatement(req);
        pstm.setString(1, e.getSujet());
        pstm.setDate(2, e.getDate());
        pstm.setString(3, e.getLieu());
        pstm.setString(4, e.getDescription());
        pstm.setString(5, e.getTitre());
        pstm.executeUpdate();
    }
    
    List<Evenement> getAllEvenements() throws SQLException {
        List<Evenement> evenements = new ArrayList<>();
        String req = "select * from evennements";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);    
        while(result.next()){
            Evenement e = new Evenement(result.getString("sujet"), result.getDate("date"),result.getString("lieu"),result.getString("description"),result.getString("titre"));
            evenements.add(e);
        }
        
        return evenements;
    }
    
    public Evenement updateEvenement(Evenement e) throws SQLException {
        String sql = "UPDATE evennements SET sujet=?, date=?, lieu=?, description=?, titre=? WHERE id=?";

        PreparedStatement statement = connexion.prepareStatement(sql);
        statement.setString(1, e.getSujet());
        statement.setDate(2, e.getDate());
        statement.setString(3, e.getLieu());
        statement.setString(4, e.getDescription());
        statement.setString(5, e.getTitre());
        statement.setInt(6, e.getId());
        System.out.println(e.getId());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing product was updated successfully!");
        }
        return e;
    }
    public boolean deleteEvenement(Evenement e) throws SQLException {
        PreparedStatement pre = connexion.prepareStatement("DELETE FROM evennements where id =?");
        pre.setInt(1, e.getId());
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A Event was deleted successfully!");
        }
        return true;
    }
    public List<Evenement> readAll() throws SQLException {
        List<Evenement> arr = new ArrayList<>();
        ste = connexion.createStatement();
        ResultSet rs = ste.executeQuery("select * from evennements");
        while (rs.next()) {
            int id = rs.getInt(1);
            String sujet = rs.getString("sujet");
            Date date = rs.getDate("date");
            String lieu = rs.getString("lieu");
            String description = rs.getString("description");
            String titre = rs.getString("titre");
            Evenement e = new Evenement(id, sujet, date, lieu, description, titre);
            arr.add(e);
        }
        return arr;
    }
     public ArrayList<Evenement> getByTitre(String titre) throws SQLException{
               List<Evenement> arr = new ArrayList<>();
     String req1="SELECT * FROM evennements WHERE titre like '"+titre+"%'";
       // PreparedStatement pre=con.prepareStatement(req1);
        ste=connexion.createStatement();
        ResultSet rs=ste.executeQuery(req1);
        Evenement e= new Evenement();
        while(rs.next())
        {
            int id = rs.getInt("id");
            String sujet = rs.getString("sujet");
            Date date = rs.getDate("date");
            String lieu = rs.getString("lieu");
            String description = rs.getString("description");
            e = new Evenement(id, sujet, date, lieu, description, titre);
            arr.add(e);

        }
        return (ArrayList<Evenement>) arr;
         
    }
     public void sendmail()
    {
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "houssem.bennasr@esprit.tn";
        String password = "H159753258456@";
        String mailTo = "housssembnsr@gmail.com";
        String mailTo1 = "wadii.chamakhi@esprit.tn";
        String subject = "INVITATION À UN ÉVÉNEMENT ";
        String message ="Madame, Monsieur, \n" +
"\n" +
"Je vous invite avec un immense plaisir de rejoindre notre nouveau evenement .\n" +
"\n" +
"merci et cordialement."; 
        SendMail mailer = new SendMail();
           try {
               /*User u = new User();
               fi service methode (public User findUserById(id){ return user}
               u = findUserById(id);
               String mailTo1 = u.email;
               */
               mailer.SendMail(host, port, mailFrom, password, mailTo, subject, message);
               mailer.SendMail(host, port, mailFrom, password, mailTo1, subject, message);

                    System.out.println("Email sent.");
                } catch (Exception ex) {
                    System.out.println("Failed to sent email.");
                    ex.printStackTrace();
                }
    }
    
}
