/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author benour
 */

public class HomeController implements Initializable {
    @FXML
    private AnchorPane rootp;
    
    @FXML
public void Liclub(ActionEvent event) throws IOException{
AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/clubD.fxml"));
rootp.getChildren().setAll(pane);
}
@FXML
public void Lidemande(ActionEvent event) throws IOException{
AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Demandelist.fxml"));
rootp.getChildren().setAll(pane);
}
@FXML
public void quitter(ActionEvent event){
System.exit(0);
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
