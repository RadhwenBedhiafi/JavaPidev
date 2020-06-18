/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gevenement.Views;

import gevenement.Entities.Evenement;
import gevenement.Service.EvenementService;
import gevenement.Service.PlanService;
import gevenement.Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class PlanFrontController implements Initializable {

    @FXML
    private Button r;
    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<Evenement, String> sujet;
    @FXML
    private TableColumn<Evenement, Date> date;
    @FXML
    private TableColumn<Evenement, String> lieu;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, String> titre;
    @FXML
    private ComboBox<String> plans;
    @FXML
    private Button chercher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection connexion = DataBase.getInstance().getConnexion();
         try{
            String sql="select titre from plan_event";
            Statement ste = connexion.createStatement();
            ResultSet rs=ste.executeQuery(sql);
            while(rs.next())
        {
            plans.getItems().addAll(rs.getString("titre")); 
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(PlanService.class.getName()).log(Level.SEVERE, null, ex);
        }
         EvenementService es= new EvenementService(); 
        ArrayList<Evenement> env= new ArrayList<Evenement>();
        try {
            env = (ArrayList<Evenement>) es.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Evenement> obs = FXCollections.observableArrayList(env);
        table.setItems(obs);
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
    chercher.setOnAction(e-> {
        
                if (plans.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("") ){
                    
                    
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please , Search for something!");
                    alert.show();}
                
                EvenementService es2= new EvenementService();
                ArrayList<Evenement> env2= new ArrayList<Evenement>();
                try {
                    String rr=plans.getSelectionModel().getSelectedItem().toString();
                    env2 = (ArrayList<Evenement>) es2.getByTitre(rr);
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Evenement> obs2 = FXCollections.observableArrayList(env2);
                table.setItems(obs2);
              sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
            
        });  
    r.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("GestionEvenement.fxml"));
                r.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(PlanFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }); 

    }    
    
}
