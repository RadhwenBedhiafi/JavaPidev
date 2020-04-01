/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesenfant.Service;
import gesenfant.Entities.Enfant;
import gesenfant.Util.DataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author elbaz
 */
public class EnfantService {
    private final Connection connexion;
    private Statement ste;

       
       public EnfantService() {
       connexion=DataBase.getInstance().getConnexion();
    }
       
       
        public void ajouterEnfant(Enfant e) throws SQLException {
        String req = "INSERT INTO `enfant` (`nom`, `prenom`, `sexe`, `age`, `nationalite`, `smedical`, `classe`) VALUES ( ?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement pstm = connexion.prepareStatement(req);
        pstm.setString(1, e.getNom());
        pstm.setString(2, e.getPrenom());
        pstm.setString(3, e.getSexe());
        pstm.setInt(4, e.getAge());
        pstm.setString(5, e.getNationalite());
        pstm.setString(6, e.getSmedical());
        pstm.setString(7, e.getClasse());
        pstm.executeUpdate();
    }
    
    public List<Enfant> getAllEnfants() throws SQLException {
        List<Enfant> enfants = new ArrayList<>();
        String req = "select * from enfant";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);    
        while(result.next()){
            Enfant e = new Enfant(result.getString("nom"), result.getString("prenom"),result.getString("sexe"),result.getInt("age"),result.getString("nationalite"),result.getString("smedical"),result.getString("classe"));
            enfants.add(e);
        }
        
        return enfants;
    }
    
    public boolean updateEnfant(Enfant e) throws SQLException {
        String sql = "UPDATE enfant SET nom=?, prenom=?, sexe=?, age=?, nationalite=?, smedical=?, classe=? WHERE id=?";

        PreparedStatement statement = connexion.prepareStatement(sql);
        statement.setString(1, e.getNom());
        statement.setString(2, e.getPrenom());
        statement.setString(3, e.getSexe());
        statement.setInt(4, e.getAge());
        statement.setString(5, e.getNationalite());
        statement.setString(6, e.getSmedical());
        statement.setString(7, e.getClasse());
        statement.setInt(8, e.getId());
        System.out.println(e.getId());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing product was updated successfully!");
        }
        return true;
    }
    public boolean deleteEnfant(Enfant e) throws SQLException {
        PreparedStatement pre = connexion.prepareStatement("DELETE FROM enfant where id =?");
        pre.setInt(1, e.getId());
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A Child was deleted successfully!");
        }
        return true;
    }
    public List<Enfant> readAll() throws SQLException {
        List<Enfant> arr = new ArrayList<>();
        ste = connexion.createStatement();
        ResultSet rs = ste.executeQuery("select * from enfant");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String sexe = rs.getString("sexe");
            int age = rs.getInt("age");
            String nationalite = rs.getString("nationalite");
            String smedical = rs.getString("smedical");
            String classe = rs.getString("classe");

            Enfant e = new Enfant(id, nom, prenom, sexe, age, nationalite,smedical,classe);
            arr.add(e);
        }
        return arr;
    }
    
    public Enfant getById(int id) throws SQLException{
     String req1="SELECT * FROM enfant WHERE id="+id;
       // PreparedStatement pre=con.prepareStatement(req1);
        ste=connexion.createStatement();
        ResultSet rs=ste.executeQuery(req1);
        Enfant e = new Enfant();
        while(rs.next())
        {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String sexe = rs.getString("sexe");
            int age = rs.getInt("age");
            String nationalite = rs.getString("nationalite");
            String smedical = rs.getString("smedical");
            String classe = rs.getString("classe");
            e = new Enfant(id, nom, prenom, sexe, age, nationalite, smedical,classe);   
        }
        return e;
         
    }
    public Enfant getByClasse(String classe) throws SQLException{
     String req1="SELECT * FROM enfant WHERE classe="+classe;
       // PreparedStatement pre=con.prepareStatement(req1);
        ste=connexion.createStatement();
        ResultSet rs=ste.executeQuery(req1);
        Enfant e = new Enfant();
        while(rs.next())
        {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String sexe = rs.getString("sexe");
            int age = rs.getInt("age");
            String nationalite = rs.getString("nationalite");
            String smedical = rs.getString("smedical");
            e = new Enfant(id, nom, prenom, sexe, age, nationalite, smedical,classe);   
        }
        return e;
         
    }
}
