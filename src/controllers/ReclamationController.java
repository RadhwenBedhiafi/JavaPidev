/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DataBase.DataSource;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entities.Mailing;
import entities.Reclamation;
import entities.User;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.ReclamationService;


/**
 * FXML Controller class
 *
 * @author COMPUTER
 */
public class ReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> table;
    @FXML
    private TableColumn<Reclamation, Integer> col_id;
    @FXML
    private TableColumn<Reclamation, String> col_destinataire;
    @FXML
    private TableColumn<Reclamation, String> col_description;
    @FXML
    private TableColumn<Reclamation, Date> col_date;
    @FXML
    private TableColumn<Reclamation, Integer> col_etat;
    public static  ObservableList<Reclamation> oc = FXCollections.observableArrayList();
    ObservableList<Reclamation> oblist = FXCollections.observableArrayList();
    ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Tout", "destinataire", "description", "etat", "date", "username", "email");
    @FXML
    private ComboBox<String> typeRecherche;
    @FXML
    private TextField RechercheTextField;
    private TextField destinataireR;
    private TextField etatR;
    private TextArea descriptionR;
    private Button modifierR;
    private static ComboBox<String> typeEtat;
    Reclamation Aem;
    @FXML
    private TableColumn<Reclamation, Button> col_edit;
    @FXML
    private TableColumn<Reclamation, Button> col_accepter;
    @FXML
    private TableColumn<Reclamation, Integer> col_idUser;
    @FXML
    private TableColumn<Reclamation, String> col_username;
    @FXML
    private TableColumn<User, String> col_email;
    @FXML
    private Button stat;
    @FXML
    private Button pdf;
    @FXML
    private Button statdest;
    @FXML
    private Button menu;
    @FXML
    private AnchorPane parentContainer;
    @FXML
    private HBox hbox;
    
   
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        col_id.setVisible(false);
        col_idUser.setVisible(false);
        refreshTable();
        TableColumn actionCol = new TableColumn("Action");
            table.getColumns().add(actionCol);
            
            typeRecherche.setItems(listeTypeRecherche);
            typeRecherche.setValue("Tout");
            
             ReclamationService cs = new ReclamationService();
             oc = cs.afficherReclamation();
        

            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_destinataire.setCellValueFactory(new PropertyValueFactory<>("destinataire"));
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
                //final Button btn = new Button("Supprimer");
            actionCol.setCellValueFactory(new PropertyValueFactory<>("button"));
            col_edit.setCellValueFactory(new PropertyValueFactory<>("edit"));
            col_accepter.setCellValueFactory(new PropertyValueFactory<>("accepter"));
            col_idUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
            col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
            col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            


            table.setItems(oc);
            editableCols();
           
            
            
            //actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFactory
                = //
                new Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>>() {
            @Override
            public TableCell call(final TableColumn<Reclamation, String> param) {
                final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {

                    final Button btn = new Button("Supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {

//                                Reclamation person = getTableView().getItems().get(getIndex());
////                                System.out.println(person.getId()
//                                        + "   " + person.getNom());
                                if (getTableView().getItems().get(getIndex()) == null) {
                                    Notifications n = Notifications.create()
                                            .title("Erreur")
                                            .text("Choix invalide")
                                            .graphic(null)
                                            .position(Pos.TOP_CENTER)
                                            .hideAfter(Duration.seconds(5));
                                    n.showWarning();
                                } else {
                                    //List<Reclamation> listReclamation = TableViewReclamation.getSelectionModel().getSelectedItems();

                                    Reclamation reclamationSelectione = getTableView().getItems().get(getIndex());
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Vous voulez vraiment supprimer cette reclamation");
                                    Optional<ButtonType> action = alert.showAndWait();
                                    if (action.get() == ButtonType.OK) {
                                        new ReclamationService().supprimerReclamation(reclamationSelectione.getId());
                                        if (reclamationSelectione.getEtat() == 0){
                                        Mailing.send(reclamationSelectione.getEmail(), "Reclamation Refusée", "Monsieur/Madame " 
                                                + reclamationSelectione.getUsername() +"\n Nous sommes désolés, votre reclamation a été refusée", "elitekids560@gmail.com", "ELITEJARDIN560");
//                                        System.out.println("Reclamation supprimée" + reclamationSelectione.getId());
                                        }
                                    }
                                }
                                
                                list();
                                             
                                

                            });
                             
//                            btn.getStyleClass().removeAll("addBobOk, focus"); 
//                            btn.getStyleClass().add("addBobOk");
//                             btn.setStyle("-fx-background-color: #00ff00");
                            setGraphic(btn);
                            setText(null);
//                            //  System.out.println(item);
                        }
                    }
                };
                return cell;
            }
        };

        actionCol.setCellFactory(cellFactory);
       


          Callback<TableColumn<Reclamation, Integer>, TableCell<Reclamation, Integer>> cellFactoryEtat
                = //
                new Callback<TableColumn<Reclamation, Integer>, TableCell<Reclamation, Integer>>() {
            @Override
            public TableCell call(final TableColumn<Reclamation, Integer> param) {
                final TableCell<Reclamation, Integer> cell = new TableCell<Reclamation, Integer>() {

                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {

                            ImageView imagev;
                            if (item == 0) {
                                imagev = new ImageView(new Image("Images/nontraite.png"));
                                imagev.setFitHeight(70);
                                imagev.setFitWidth(70);
                                setGraphic(imagev);

                            }
                            else  {
                                imagev = new ImageView(new Image("Images/traite.png"));
                                imagev.setFitHeight(70);
                                imagev.setFitWidth(70);
                                setGraphic(imagev);
                            }

                        }
                    }
                };
                return cell;
            }
        };

        col_etat.setCellFactory(cellFactoryEtat);


        
    }    

    @FXML
        public void list() {
        ArrayList arrayList = null;
        ReclamationService produitService = new ReclamationService();
        if (typeEtat == null) {
            typeEtat = new ComboBox<String>();
             typeEtat.getItems().setAll("Non traitée", "Traitée");
            typeEtat.setValue("Traitée");
        }
        typeEtat.setOnAction(e -> list());
         if (typeRecherche.getValue().toString().equals("etat") && hbox.getChildren().contains(RechercheTextField) ) {
           
            hbox.getChildren().remove(RechercheTextField);
            hbox.getChildren().add(typeEtat);
        }  else if (!typeRecherche.getValue().toString().equals("etat") && !hbox.getChildren().contains(RechercheTextField)) {
            hbox.getChildren().remove(typeEtat);
            hbox.getChildren().add(RechercheTextField);
        }
         if (typeRecherche.getValue().toString().equals("etat"))
         {
        arrayList = (ArrayList) produitService.rechercheReclamations(typeRecherche.getValue().toString(), typeEtat.getValue().toString());
        refreshTable();

         }
         else{
         arrayList = (ArrayList) produitService.rechercheReclamations(typeRecherche.getValue().toString(), RechercheTextField.getText());
        refreshTable();

         }
        
     
            //arrayList = (ArrayList) produitService.afficherReclamation();
        
 
        ObservableList oc = FXCollections.observableArrayList(arrayList);
        table.setItems(oc);
        

    }


    private void getSelected(MouseEvent event) {
        
            Aem = table.getSelectionModel().getSelectedItem();

        if (Aem == null) {
            JOptionPane.showMessageDialog(null, "Nothing selected");
            modifierR.setDisable(true);
            //delete.setDisable(true);
        } else {

            modifierR.setDisable(false);
            //delete.setDisable(false);
            destinataireR.setText(Aem.getDestinataire());
            descriptionR.setText(Aem.getDescription());
            etatR.setText(String.valueOf(Aem.getEtat()));
            //dateR.setDa(Aem.getDate());
            //d.setText(Aem.getType());
        }
    }

    private void  editableCols()
 {
    
     
     col_destinataire.setCellFactory(TextFieldTableCell.forTableColumn());
     col_destinataire.setOnEditCommit(e->{
         e.getTableView().getItems().get(e.getTablePosition().getRow()).setDestinataire(e.getNewValue());
     });
     
     col_description.setCellFactory(TextFieldTableCell.forTableColumn());
     col_description.setOnEditCommit(e->{
         e.getTableView().getItems().get(e.getTablePosition().getRow()).setDescription(e.getNewValue());
     });
     
     /*  col_date.setCellFactory(TextFieldTableCell.forTableColumn());
     col_date.setOnEditCommit(e->{
         e.getTableView().getItems().get(e.getTablePosition().getRow()).setDate(e.getNewValue());
     });*/
     
      col_etat.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
     
     {col_etat.setOnEditCommit(e->{
        
         e.getTableView().getItems().get(e.getTablePosition().getRow()).setEtat(e.getNewValue());
          
              
     });}
     
     
     
    
     
     table.setEditable(true);
 
 }
    
       public void refreshTable () 
    {
        
            oc.clear();
            Connection connexion;
                   connexion=DataSource.getInstance().getCnx();
            String query = "select * from reclamation";
            PreparedStatement pst;
        try {
            pst = connexion.prepareStatement(query);
               ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                  Reclamation e=new Reclamation();   
               e.setId(rs.getInt(1));
                    e.setDestinataire(rs.getString(2));
                    e.setDescription(rs.getString(3));
                    e.setIdUser(rs.getInt(4));
                    e.setUsername(rs.getString(5));
                    e.setEmail(rs.getString(6));
                    e.setDate(rs.getDate(7));
                    e.setEtat(rs.getInt(8));
                          Button edit = new Button("edit");
                    Button accepter = new Button ("accepter");
                    e.setEdit(edit);
                    
                    e.setAccepter(accepter);
                    if (e.getEtat() == 1)
                    {accepter.setDisable(true); 
                    edit.setDisable(true);
                    }
                    
                    
                      edit.setOnAction(s -> {
            
                if (e.getEdit() == edit) {
                    System.out.println("id: "+e.getId());
                    Integer a = e.getId();
                    System.out.println("destinataire: "+e.getDestinataire());
                    String b = e.getDestinataire();
                    System.out.println("description: "+e.getDescription());
                    String c = e.getDescription();
                    System.out.println("date: "+e.getDate());
                    System.out.println("etat: "+e.getEtat());
                    Integer q = e.getEtat();
                    String reqe = "UPDATE reclamation set destinataire =? , description = ? ,etat =?  where id = ?  ";
                    try {
                        PreparedStatement pstm = connexion.prepareStatement(reqe);
                            pstm.setString(1, b);
                            pstm.setString(2,c);
                            pstm.setInt(3, q);
                            pstm.setInt(4, a);
                            pstm.executeUpdate();
                           Notifications h = Notifications.create()
                                            .title("Reclamation")
                                            .text("Reclamation Modifiée avec succés")
                                            .graphic(null)
                                            .position(Pos.TOP_CENTER)
                                            .hideAfter(Duration.seconds(3));
                             h.showInformation();

                        
                    } catch (SQLException ex) {
                        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    

                    }
                       
        
        });
                                   accepter.setOnAction(s -> {
            
                if (e.getAccepter() == accepter) {
                    System.out.println("id: "+e.getId());
                    Integer a = e.getId();
                    
                   
                   
                    
                  
                    try {
                         FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/views/ajoutRendezvous.fxml"));
                        Parent root1 = (Parent) fxmlloader.load();
                        controllers.AjoutRendezvousController ajout = fxmlloader.getController();
                        ajout.myFunction(a.toString());
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));
                        stage.show();
                     
                        
        
                    } catch (IOException ex) {
                        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
       
                    
                    
            
                  
                    

                    }
                      
        
        });
                
                oc.add(e);
                table.setItems(oc);
                
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    @FXML
    private void showStat(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/views/barChartReclamation.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void pdfReclamation(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException {
            ReclamationService r = new ReclamationService();
            r.pdf();
            Notifications n = Notifications.create()
                                            .title("Succés")
                                            .text("le tableau de reclamations est enregistré en PDF")
                                            .graphic(null)
                                            .position(Pos.TOP_CENTER)
                                            .hideAfter(Duration.seconds(5));
                                    n.showInformation();
            
    }

    @FXML
    private void showStatDest(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/barChartdestinataire.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/views/homeReclamation.fxml"));
        
        Scene scene = menu.getScene();
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
