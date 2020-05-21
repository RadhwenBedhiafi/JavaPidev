/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.ReclamationService;
import org.controlsfx.control.Notifications;



/**
 * FXML Controller class
 *
 * @author COMPUTER
 */
public class AjoutReclamationController implements Initializable {

    private TextField txtdestinataire;
    @FXML
    private TextArea txtdescription;
  
    @FXML
    private Button envoyerR;
    @FXML
    private Label checkDestinataire;
    @FXML
    private Label checkDescription;
    @FXML
    private TextField txtidUser;
    @FXML
    private Label checkUser;
    @FXML
    private ImageView imgDest;
    @FXML
    private ImageView imgDesc;
    @FXML
    private ImageView imgID;
    @FXML
    private AnchorPane container;
    @FXML
    private ComboBox<String> destinataire;
    ObservableList<String> listeDestinataire = FXCollections.observableArrayList("Directeur général","Responsable technique", "Responsable resources humaines", "Responsable informatique", "Directeur financier");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        destinataire.setItems(listeDestinataire);
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        
        // LocalDate dater = txtdate.getValue();
        //Date date=java.sql.Date.valueOf(dater);

        
        ReclamationService r = new ReclamationService();
        if (testSaisie() && !destinataire.getSelectionModel().isEmpty() && !txtdescription.getText().equals(""))
        {Reclamation a = new Reclamation(destinataire.getValue().toString(),txtdescription.getText(), Integer.parseInt(txtidUser.getText()));
        r.ajouterReclamation(a);
        
        System.out.println("ajout reclamation avec succes");
        Notifications n = Notifications.create()
                    .title("Succes")
                    .text("Reclamation envoyée avec succes")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(3));
            n.showInformation();
            
        }
       
    }
    
    @FXML
    private void vider() 
    {
        txtdestinataire.clear();
        txtdescription.clear();
    
    }
    
    
    @FXML
    private Boolean testDestinataire() {
        if (!destinataire.getSelectionModel().isEmpty()){
            //checkID.setText("vrai");
            imgDest.setImage(new Image("Images/tick.png"));
            checkDestinataire.setVisible(false);
            imgDest.setVisible(true);
            return true;
        }
        else {
            checkDestinataire.setText("Veuillez selectionner un destinataire");
                    checkDestinataire.setVisible(true);
                    imgDest.setVisible(false);
            return false;
        }
    }
    
        private Boolean testSaisie() {
       String erreur = "";
        
        if (!testDestinataire()) {
            erreur = erreur + ("Veuillez verifier le champs du destinataire \n");
        }
        if (!testDescription()) {
            erreur = erreur + ("Veuillez verifier le champs du description \n");
        }
        if (!testUser()) {
            erreur = erreur + ("Veuillez verifier le champs de l'ID \n");
        }
        

        if (!testDestinataire() || !testDescription() || !testUser()) {
          Notifications  n = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
            n.showInformation();
        }

        return testDestinataire() ;
    }

    @FXML
    private Boolean testDescription() {
        
          int nbNonChar = 0;
      

        if (nbNonChar == 0 && txtdescription.getText().trim().length() >= 1) {
            //nomCheckMark.setImage(new Image("Images/checkMark.png"));
            //checkDescription.setText("vrai");
             imgDesc.setImage(new Image("Images/tick.png"));
            checkDescription.setVisible(false);
            imgDesc.setVisible(true);
            return true;
        } else {
            //nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            checkDescription.setText("veuillez remplir ce champs");
            checkDescription.setVisible(true);
            imgDesc.setVisible(false);

            return false;

        }
    }
        @FXML
    private Boolean testUser() {
        int nbNonChar = 0;
        for (int i = 0; i < txtidUser.getText().trim().length(); i++) {
            char ch = txtidUser.getText().charAt(i);
            
            if (Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        
        
       if (nbNonChar == 0 && txtidUser.getText().trim().length() >= 1) {
            //nomCheckMark.setImage(new Image("Images/checkMark.png"));
            //checkUser.setText("vrai");
            imgID.setImage(new Image("Images/tick.png"));
            checkUser.setVisible(false);
            imgID.setVisible(true);
            return true;
        }
      
        
        else {
           if (nbNonChar != 0){
            //nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            checkUser.setText("Veuillez taper des numéros uniquement");
            checkUser.setVisible(true);
            imgID.setVisible(false);
            return false;
           }
           else if (txtidUser.getText().trim().length() >= 0)
           {
              checkUser.setText("Veuillez remplir ce champs");
              checkUser.setVisible(true);
            imgID.setVisible(false);
                       return false;
}


        }
       return false;
    }

    @FXML
    private void menu(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/views/homeReclamation.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
    }

    
}
