/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DataBase.DataSource;
import com.jfoenix.controls.JFXTimePicker;
import entities.Mailing;
import entities.Reclamation;
import entities.Rendezvous;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import services.ReclamationService;
import services.RendezvousService;

/**
 * FXML Controller class
 *
 * @author COMPUTER
 */
public class AjoutRendezvousController implements Initializable {

    private TextField idR;
    @FXML
    private TextField lieu;
    @FXML
    private DatePicker date;
    @FXML
    private Button envoyerRV;
   
    
    public static  ObservableList<Reclamation> oc = FXCollections.observableArrayList();
    @FXML
    private Label checkLieu;
    @FXML
    private JFXTimePicker heure;
    @FXML
    private Label checkdate;
    @FXML
    private Label checkHeure;
    @FXML
    private ImageView img;
    @FXML
    private ImageView imgDate;
    @FXML
    private ImageView imgHeure;
    @FXML
    private Label checkID;
    @FXML
    private ImageView imgID;
    @FXML
    private Label idjeya;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       heure.set24HourView(true);
        // TODO
        String [] choix ={"Bloc A salle ","Bloc B salle ","Bloc C salle "};
      TextFields.bindAutoCompletion(lieu, choix);
      final Tooltip tooltiplieu = new Tooltip();
      tooltiplieu.setText("Lieu");
      lieu.setTooltip(tooltiplieu);
       tooltiplieu.setStyle("-fx-background-color:  #FA2C56");
       
       final Tooltip tooltipdate = new Tooltip();
      tooltipdate.setText("Date");
      date.setTooltip(tooltipdate);
       tooltipdate.setStyle("-fx-background-color:  #FA2C56");
       
       final Tooltip tooltipheure = new Tooltip();
      tooltipheure.setText("Heure");
      heure.setTooltip(tooltipheure);
       tooltipheure.setStyle("-fx-background-color:  #FA2C56");
       
         date.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
    }
    public void myFunction (String text){
    idjeya.setText(text);
    idjeya.setVisible(false);
}

 

    @FXML
    private void ajouterR(ActionEvent event) throws SQLException, IOException {
        RendezvousService r = new RendezvousService();
        Rendezvous as = new Rendezvous();
        //LocalDate d = date.getValue();
        
      if (testSaisie() && !lieu.getText().equals("") && !date.getEditor().getText().equals("") && !heure.getEditor().getText().equals("") ){
        //if (testSaisie() && !txtdestinataire.getText().equals("") && !txtdescription.getText().equals(""))
       Rendezvous a = new Rendezvous(Integer.parseInt(idjeya.getText()),date.getEditor().getText(),lieu.getText(),heure.getEditor().getText());
      
        r.ajouterRendezvous(a);
          System.out.println(a.getIdR());
          int z = a.getIdR();
       Connection connexion = DataSource.getInstance().getCnx();
        String requete="select * from reclamation    ";
                  Statement pst = connexion.prepareStatement(requete);
                   
                     ResultSet rs = pst.executeQuery(requete);
                     while (rs.next() ) {
                        Reclamation d = new Reclamation();
                        
                        d.setId(rs.getInt(1));
                        if (d.getId() == z) {
                        d.setDestinataire(rs.getString(2));
                        d.setDescription(rs.getString(3));
                        d.setIdUser(rs.getInt(4));
                        d.setUsername(rs.getString(5));
                        d.setEmail(rs.getString(6));
                        d.setDate(rs.getDate(7));
                        d.setEtat(rs.getInt(8));
        
                        
                        System.out.println(d.getEmail());
                        Mailing.send(d.getEmail(), "Reclamation Traitée", "Monsieur/Madame " + d.getUsername() +"\n votre reclamation a été traitée \n vous aurez un rendez vous le: "
               + a.getDate()+ " à "+ a.getHeure() + "\n Lieu: "+ a.getLieu() , "elitekids560@gmail.com", "ELITEJARDIN560");
                        }
                        
                     }
          FXMLLoader loader = new  FXMLLoader(getClass().getResource("/views/reclamation.fxml"));
                Parent root = (Parent) loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                     ReclamationController rec = loader.getController();                   
                     rec.refreshTable();
                     
                   
        //a.setEmail("najibaamri23@gmail.com");
        //System.out.print(a.getEmail());
        Notifications n = Notifications.create()
                          .title("Succes")
                          .text("Rendez vous ajouté avec succés")
                          .position(Pos.TOP_CENTER)
                          .hideAfter(Duration.seconds(5));
                           n.showInformation();
       // ReclamationController FXMLLoader;
      
 // Mailing.send(a.getEmail(), "Reclamation Traitée", "Monsieur X votre reclamation a été traitée vous aurez un rendez vous le:"
               // + a.getDate() + "/n Lieu: "+ a.getLieu() , "najibaamri23@gmail.com", "GUESSWHATISITT");
       
                     
     
      }
                
     
        
      
    }
    @FXML
    private void viderR(ActionEvent event) {
        
        
        lieu.clear();
        date.setValue(LocalDate.MAX);
    }

    private void consulterR(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/rendezvous.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
        
       
    }
        private Boolean testSaisie() {
       String erreur = "";
        
        
        if (!testLieu()) {
            erreur = erreur + ("Veuillez verifier le champs du lieu \n");
        }
        if (!testDate()) {
            erreur = erreur + ("Veuillez verifier le champs de la date \n");
        }
        if (!testHeure()) {
            erreur = erreur + ("Veuillez verifier le champs d'heure \n");
        }
        
        
        

        if (!testLieu() || !testDate() || !testHeure() ) {
          Notifications  n = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
            n.showInformation();
        }
       

        return testDate() ;
    }

    @FXML
    private Boolean testLieu() {
           int nbNonChar = 0;
      

        if (nbNonChar == 0 && lieu.getText().trim().length() >= 1) {
            img.setImage(new Image("Images/tick.png"));
            checkLieu.setVisible(false);
            img.setVisible(true);
            return true;
        } else {
            //nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            checkLieu.setText("veuillez remplir ce champs");
            img.setVisible(false);
            checkLieu.setVisible(true);

            return false;

        }
     
    }
    @FXML
      private Boolean testDate(){
          if (!date.getEditor().getText().equals("")){
            //checkdate.setText("vrai");
             imgDate.setImage(new Image("Images/tick.png"));
            checkdate.setVisible(false);
            imgDate.setVisible(true);
                    return true;
          }
          else {
                    
                    checkdate.setText("Veuillez entrer une date");
                    checkdate.setVisible(true);
                    imgDate.setVisible(false);
            return false;
                    }
            
        
         
           
       }

    @FXML
    private Boolean testHeure() {
        if (!heure.getEditor().getText().equals("")){
            //checkHeure.setText("vrai");
            imgHeure.setImage(new Image("Images/tick.png"));
            checkHeure.setVisible(false);
            imgHeure.setVisible(true);
            return true;}
            else {
                    checkHeure.setText("Veuillez entrer l'heure");
                    checkHeure.setVisible(true);
                    imgHeure.setVisible(false);
                    return false;
                    }
        
    }


    
    
    
        

    
}
