/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesenfant.Views;

import gesenfant.Service.EnfantService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author elbaz
 */
public class StatistiqueController implements Initializable {

    @FXML
    private PieChart piechart;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            EnfantService es= new EnfantService();
            int x=es.getEffectifHomme();
            int y=es.getEffectifFemme();
            ObservableList<PieChart.Data> pieChartData=FXCollections.observableArrayList(
                    new PieChart.Data("Homme",x),new PieChart.Data("Femme", y));
            piechart.setData(pieChartData);
            piechart.setStartAngle(90);
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     retour.setOnAction(e->{  
            Parent root ;
         try {
             root=FXMLLoader.load(getClass().getResource("GestionEnfant.fxml"));
             retour.getScene().setRoot(root);
         } catch (IOException ex) {
             Logger.getLogger(AjouterEnfantController.class.getName()).log(Level.SEVERE, null, ex);
         }
            
             });   
    }
    
    
}
