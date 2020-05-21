/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author COMPUTER
 */
public class HomeReclamationController implements Initializable {

    @FXML
    private Button reclama;
    @FXML
    private Button rendez;
    @FXML
    private Button deconnecter;
    @FXML
    private AnchorPane parentContainer;
   
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void reclama(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/views/reclamation.fxml"));
        
        Scene scene = reclama.getScene();
        root.translateXProperty().set(scene.getWidth());
        
        parentContainer.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(),0,Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1->{
            parentContainer.getChildren().remove(parentContainer);
        });
        timeline.play();
    }

    @FXML
    private void rendez(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/views/rendezvous.fxml"));
        
        Scene scene = rendez.getScene();
        root.translateXProperty().set(scene.getWidth());
        
        parentContainer.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(),0,Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1->{
            parentContainer.getChildren().remove(parentContainer);
        });
        timeline.play();
    }

    @FXML
    private void ajoutReclamation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/AjoutReclamation.fxml"));
        
        Scene scene = deconnecter.getScene();
        root.translateXProperty().set(-scene.getWidth());
        
        parentContainer.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(),0,Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1->{
            parentContainer.getChildren().remove(parentContainer);
        });
        timeline.play();
        
        
    }
    
}
