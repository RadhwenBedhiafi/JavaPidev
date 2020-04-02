/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DataBase.dbconnectioninfo;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.club;
import services.crudclub;

/**
 * FXML Controller class
 *
 * @author benour
 */
public class UpdateDelController implements Initializable {
@FXML private TextField idy;
   @FXML private TextField nomcluby;
    @FXML private TextArea descriptiony;
    @FXML private TextField horrairey;
    @FXML private TextField tarify;
    @FXML private TextField imagey;
    @FXML private TextField capacitey;
    
    public void getclub(ActionEvent event) throws IOException, ParseException{
        String sid = idy.getText();
        int id = Integer.parseInt(sid);
        club cl = crudclub.getclubId(id);
        nomcluby.setText(cl.getNomclub());
        descriptiony.setText(cl.getDescription());
        horrairey.setText(cl.getHorraire());
        imagey.setText(cl.getImage());
        tarify.setText(String.valueOf(cl.getTarif()));
        capacitey.setText(String.valueOf(cl.getCapacite()));
        
    }
    public void updateclub(ActionEvent event)throws IOException{
    String sid = idy.getText();
        int id = Integer.parseInt(sid);
        String txtnomclub = nomcluby.getText();
        String txtdescriptiony = descriptiony.getText();
        String txthorraire = horrairey.getText();
        String txtimage = imagey.getText();
        String starif = tarify.getText();
        String scapacite = capacitey.getText();
        int tarif = Integer.parseInt(starif);
        int capacite = Integer.parseInt(scapacite);
        club cl = new club();
        cl.setId(id);
        cl.setNomclub(txtnomclub);
        cl.setDescription(txtdescriptiony);
        cl.setHorraire(txthorraire);
        cl.setImage(txtimage);
        cl.setTarif(tarif);
        cl.setCapacite(capacite);
        int status = crudclub.update(cl);
        if (status>0){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modifier un club!");
        alert.setHeaderText("information");
        alert.setContentText("Club bien Modifier! ");
        alert.showAndWait();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Modifier un club!");
        alert.setHeaderText("information");
        alert.setContentText("Club non modifier! ");
        alert.showAndWait();
        }
    }
    public void deleteclub(ActionEvent event)throws IOException{
    String sid = idy.getText();
        int id = Integer.parseInt(sid);
        int status = crudclub.delete(id);
        if (status>0){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Supprimer un club!");
        alert.setHeaderText("information");
        alert.setContentText("Club non supprimer! ");
        alert.showAndWait();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Supprimer un club!");
        alert.setHeaderText("information");
        alert.setContentText("Club est supprimer! ");
        alert.showAndWait();
        }
        
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
