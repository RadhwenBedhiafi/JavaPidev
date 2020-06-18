/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gevenement.Views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AcceuilGEController implements Initializable {

    @FXML
    private Button r;
    @FXML
    private Button ButtonEvenement;
    @FXML
    private Button ButtonPlan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
ButtonEvenement.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("GestionEvenement.fxml"));
                ButtonEvenement.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilGEController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
        ButtonPlan.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("GestionPlan.fxml"));
                ButtonPlan.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilGEController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });     }    
    
}
