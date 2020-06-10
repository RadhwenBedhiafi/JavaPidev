/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesenfant.Service;

import gesenfant.Entities.Classe;
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
public class ClasseService {
    private final Connection connexion;
    private Statement ste;

       
       public ClasseService() {
       connexion=DataBase.getInstance().getConnexion();
    }
       
       
        public void ajouterClasse(Classe c) throws SQLException {
        String req = "INSERT INTO `classe` (`bloc`, `libelle`, `taillemax`) VALUES ( ?, ?, ?) ";
        PreparedStatement pstm = connexion.prepareStatement(req);
        pstm.setString(1, c.getBloc());
        pstm.setString(2, c.getLibelle());
        pstm.setInt(3, c.getTaillemax());
        pstm.executeUpdate();
    }
    
    List<Classe> getAllEnfants() throws SQLException {
        List<Classe> classes = new ArrayList<>();
        String req = "select * from classe";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);    
        while(result.next()){
            Classe c = new Classe(result.getString("bloc"), result.getString("libelle"),result.getInt("taillemax"));
            classes.add(c);
        }
        
        return classes;
    }
    
    public boolean updateClasse(Classe c) throws SQLException {
        String sql = "UPDATE classe SET bloc=?, libelle=?, taillemax=? WHERE id=?";

        PreparedStatement statement = connexion.prepareStatement(sql);
        statement.setString(1, c.getBloc());
        statement.setString(2, c.getLibelle());
        statement.setInt(3, c.getTaillemax());
        statement.setInt(4, c.getId());
        System.out.println(c.getId());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing product was updated successfully!");
        }
        return true;
    }
    public boolean deleteClasse(Classe c) throws SQLException {
        PreparedStatement pre = connexion.prepareStatement("DELETE FROM classe where id =?");
        pre.setInt(1, c.getId());
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A Child was deleted successfully!");
        }
        return true;
    }
    public List<Classe> readAll() throws SQLException {
        List<Classe> arr = new ArrayList<>();
        ste = connexion.createStatement();
        ResultSet rs = ste.executeQuery("select * from classe");
        while (rs.next()) {
            int id = rs.getInt(1);
            String bloc = rs.getString("bloc");
            String libelle = rs.getString("libelle");
            int taillemax = rs.getInt("taillemax");
            Classe c = new Classe(id, bloc, libelle, taillemax);
            arr.add(c);
        }
        return arr;
    }
    
    public ArrayList<Classe> getById(int id) throws SQLException{
       List<Classe> arr = new ArrayList<>();

     String req1="SELECT * FROM classe WHERE id="+id;
       // PreparedStatement pre=con.prepareStatement(req1);
        ste=connexion.createStatement();
        ResultSet rs=ste.executeQuery(req1);
        Classe c = new Classe();
        while(rs.next())
        {
            String bloc = rs.getString("bloc");
            String libelle = rs.getString("libelle");
            int taillemax = rs.getInt("taillemax");
            c = new Classe(id, bloc, libelle, taillemax);   
           arr.add(c);
        }
        return (ArrayList<Classe>) arr;
         
    }
     public ArrayList<Classe> getByLib(String libelle) throws SQLException{
       List<Classe> arr = new ArrayList<>();

     String req1="SELECT * FROM classe WHERE libelle like '"+libelle+"%'";
       // PreparedStatement pre=con.prepareStatement(req1);
        ste=connexion.createStatement();
        ResultSet rs=ste.executeQuery(req1);
        Classe c = new Classe();
        while(rs.next())
        {
                        int id = rs.getInt("id");
            String bloc = rs.getString("bloc");
            int taillemax = rs.getInt("taillemax");
            c = new Classe(id, bloc, libelle, taillemax);   
           arr.add(c);
        }
        return (ArrayList<Classe>) arr;
         
    }
     public List<Classe> readAllA() throws SQLException {
        List<Classe> arr = new ArrayList<>();
        ste = connexion.createStatement();
        ResultSet rs = ste.executeQuery("select * from classe order by taillemax ASC");
        while (rs.next()) {
            int id = rs.getInt(1);
            String bloc = rs.getString("bloc");
            String libelle = rs.getString("libelle");
            int taillemax = rs.getInt("taillemax");
            Classe c = new Classe(id, bloc, libelle, taillemax);
            arr.add(c);
        }
        return arr;
    } 
     public List<Classe> readAllD() throws SQLException {
        List<Classe> arr = new ArrayList<>();
        ste = connexion.createStatement();
        ResultSet rs = ste.executeQuery("select * from classe order by taillemax DESC");
        while (rs.next()) {
            int id = rs.getInt(1);
            String bloc = rs.getString("bloc");
            String libelle = rs.getString("libelle");
            int taillemax = rs.getInt("taillemax");
            Classe c = new Classe(id, bloc, libelle, taillemax);
            arr.add(c);
        }
        return arr;
    }
      public List<Classe> readAllIDA() throws SQLException {
        List<Classe> arr = new ArrayList<>();
        ste = connexion.createStatement();
        ResultSet rs = ste.executeQuery("select * from classe order by bloc ASC");
        while (rs.next()) {
            int id = rs.getInt(1);
            String bloc = rs.getString("bloc");
            String libelle = rs.getString("libelle");
            int taillemax = rs.getInt("taillemax");
            Classe c = new Classe(id, bloc, libelle, taillemax);
            arr.add(c);
        }
        return arr;
    } public List<Classe> readAllIDD() throws SQLException {
        List<Classe> arr = new ArrayList<>();
        ste = connexion.createStatement();
        ResultSet rs = ste.executeQuery("select * from classe order by bloc DESC");
        while (rs.next()) {
            int id = rs.getInt(1);
            String bloc = rs.getString("bloc");
            String libelle = rs.getString("libelle");
            int taillemax = rs.getInt("taillemax");
            Classe c = new Classe(id, bloc, libelle, taillemax);
            arr.add(c);
        }
        return arr;
    }
    public int getEffectifTotal(String libelle) throws SQLException{
        String req = "select taillemax from classe where libelle like '"+libelle+"%'";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);    
        result.next();
        int count=result.getInt(1);
         return count;
    }
}
