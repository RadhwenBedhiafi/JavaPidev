/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gevenement.Service;

import gevenement.Entities.Evenement;
import gevenement.Entities.Plan;
import gevenement.Utils.DataBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class PlanService {
    private final Connection connexion;
    private Statement ste;

       
       public PlanService() {
       connexion=DataBase.getInstance().getConnexion();
       }
        public void ajouterPlan(Plan p) throws SQLException {
        String req = "INSERT INTO `plan_event` (`datedebut`, `datefin`, `titre`) VALUES ( ?, ?, ?) ";
        PreparedStatement pstm = connexion.prepareStatement(req);
        pstm.setDate(1, p.getDatedebut());
        pstm.setDate(2, p.getDatefin());
        pstm.setString(3, p.getTitre());
        pstm.executeUpdate();
    }
    
    List<Plan> getAllPlans() throws SQLException {
        List<Plan> plans = new ArrayList<>();
        String req = "select * from plan_event";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);    
        while(result.next()){
            Plan p = new Plan(result.getDate("datedebut"), result.getDate("datefin"),result.getString("titre"));
            plans.add(p);
        }
        
        return plans;
    }
    
    public boolean updatePlan(Plan p) throws SQLException {
        String sql = "UPDATE plan_event SET datedebut=?, datefin=?, titre=? WHERE id=?";

        PreparedStatement statement = connexion.prepareStatement(sql);
        statement.setDate(1, p.getDatedebut());
        statement.setDate(2, p.getDatefin());
        statement.setString(3, p.getTitre());
        statement.setInt(4, p.getId());
        System.out.println(p.getId());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing plan was updated successfully!");
        }
        return true;
    }
    public boolean deletePlan(Plan p) throws SQLException {
        PreparedStatement pre = connexion.prepareStatement("DELETE FROM plan_event where id =?");
        pre.setInt(1, p.getId());
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A plan was deleted successfully!");
        }
        return true;
    }
    public List<Plan> readAll() throws SQLException {
        List<Plan> arr = new ArrayList<>();
        ste = connexion.createStatement();
        ResultSet rs = ste.executeQuery("select * from plan_event");
        while (rs.next()) {
            int id = rs.getInt(1);
            Date datedebut = rs.getDate("datedebut");
            Date datefin = rs.getDate("datefin");
            String titre = rs.getString("titre");
            Plan p = new Plan(id, datedebut, datefin,titre);
            arr.add(p);
        }
        return arr;
    }
    
    
}
