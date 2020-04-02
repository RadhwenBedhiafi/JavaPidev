/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionr;

import DataBase.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author COMPUTER
 */
public class FXMLDocumentController implements Initializable {
    
   private TableView<Reclamation> table;
    @FXML
    private TableColumn<Reclamation, Double> col_id;
    @FXML
    private TableColumn<Reclamation, String> col_destinataire;
    @FXML
    private TableColumn<Reclamation, String> col_description;
    @FXML
    private TableColumn<Reclamation, Date> col_date;
    @FXML
    private TableColumn<Reclamation, Double> col_etat;
    
       ObservableList<Reclamation> oblist = FXCollections.observableArrayList();
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Connection ds=DataSource.getInstance().getCnx();
            ResultSet rs = ds.createStatement().executeQuery("select * from reclamation");
            
            while(rs.next()){
                
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_destinataire.setCellValueFactory(new PropertyValueFactory<>("destinataire"));
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            
            table.setItems(oblist);

        
    }    
    
}
