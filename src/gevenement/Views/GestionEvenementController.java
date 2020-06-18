/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gevenement.Views;

import com.itextpdf.text.BaseColor;
import static com.itextpdf.text.BaseColor.GRAY;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gevenement.Entities.Evenement;
import gevenement.Service.EvenementService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Date;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class GestionEvenementController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button r;
    @FXML
    private Button afficher;
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
        private static Evenement ev;
                private static ArrayList<Evenement> env11;

    @FXML
    private Button affecter;
    @FXML
    private Button gnrtpdf;
        
        


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        EvenementService es= new EvenementService(); 
        ArrayList<Evenement> even= new ArrayList<Evenement>();
        try {
            even = (ArrayList<Evenement>) es.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Evenement> obs = FXCollections.observableArrayList(even);
        table.setItems(obs);
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        
        
        
         ArrayList<Evenement> env11= new ArrayList<Evenement>();
        try {
            even = (ArrayList<Evenement>) es.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Evenement> obs33 = FXCollections.observableArrayList(env11);
        table.setItems(obs33);
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        
        r.setOnAction(e->{  
            Parent root ;
         try {
             root=FXMLLoader.load(getClass().getResource("AcceuilGE.fxml"));
             r.getScene().setRoot(root);
         } catch (IOException ex) {
             Logger.getLogger(ModifierPlanController.class.getName()).log(Level.SEVERE, null, ex);
         }
            
             });  
        
        supprimer.setOnAction((ActionEvent e) -> {
            //Local(String nom, String adresse, float prix,float surface,int capacite)
            ev = table.getSelectionModel().getSelectedItem();
                if(ev != null)
                {
                ev = table.getSelectionModel().getSelectedItem();
                    try {
                        es.deleteEvenement(ev);
                        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                         alert1.setContentText("Suppression effectuée avec succès!");
                           alert1.show();
                           
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                EvenementService es5= new EvenementService(); 
        ArrayList<Evenement> even5= new ArrayList<Evenement>();
        try {
            even5 = (ArrayList<Evenement>) es5.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Evenement> obs5 = FXCollections.observableArrayList(even5);
        table.setItems(obs5);
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
                }

        });
        modifier.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)

            ev = table.getSelectionModel().getSelectedItem();
            if (!(ev == null)) {
                try {
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("ModifierEvenement.fxml"));
                    modifier.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(GestionEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        ajouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //(String id,String nom, String adresse, String prix, String surface,String capacite)
                try {
                    Parent root;
                    root = FXMLLoader.load(GestionEvenementController.this.getClass().getResource("AjoutEvenement.fxml"));
                    ajouter.getScene().setRoot(root);
                }catch (IOException ex) {
                    Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        afficher.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                EvenementService es5= new EvenementService();
                ArrayList<Evenement> even5= new ArrayList<Evenement>();
                try {
                    even5 = (ArrayList<Evenement>) es5.readAll();
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Evenement> obs5 = FXCollections.observableArrayList(even5);
                table.setItems(obs5);
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            }
        });
        r.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Parent root ;
                try {
                    root = FXMLLoader.load(GestionEvenementController.this.getClass().getResource("AcceuilGE.fxml"));
                    r.getScene().setRoot(root);
                }catch (IOException ex) {
                    Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                }  }
        });     gnrtpdf.setOnAction(e->{
            
          GeneratePdf();
           Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                         alert1.setContentText("Fichier generé avec succès!");
                           alert1.show();
        }); 
        
        
    }
          public static void GeneratePdf() {
       
       
        Document document = new Document();

        PdfWriter writer = null;    
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\Liste.pdf"));
        } catch (FileNotFoundException ex) {
        } catch (DocumentException ex) {
        }
        document.open();
        document.addTitle("Liste des evenements");
        document.addSubject("Jardin d'enfant");
        document.addAuthor("ELITE");
        document.addCreator("Ben nasr Houssem");
        Paragraph preface = new Paragraph();
        preface.add(new Paragraph("Liste des evenements:"));
        preface.add(new Paragraph("Rapport genéré par: Ben nasr Houssem"));
        try {
            document.add(preface);
        } catch (DocumentException ex) {
        }
        PdfPTable table = new PdfPTable(4); // 3 columns.
        table.setWidthPercentage(100); //Width 100%
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table

        //Set Column widths
        float[] columnWidths = {0.75f, 2f, 2.5f, 2.5f};
        try {
            table.setWidths(columnWidths);
        } catch (DocumentException ex) {
        }
        /*PdfPCell cell1 = new PdfPCell(new Paragraph("Id"));
        cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell1.setPaddingLeft(10);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);*/

        PdfPCell cell2 = new PdfPCell(new Paragraph("Sujet"));
        cell2.setBackgroundColor(BaseColor.GRAY);
        cell2.setPaddingLeft(10);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        
      

        PdfPCell cell3 = new PdfPCell(new Paragraph("Lieu"));
        cell3.setBackgroundColor(BaseColor.GRAY);
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell4 = new PdfPCell(new Paragraph("Description"));
        cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell4.setPaddingLeft(10);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell5 = new PdfPCell(new Paragraph("Titre"));
        cell5.setBackgroundColor(BaseColor.GRAY);
        cell5.setPaddingLeft(10);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
        //To avoid having the cell border and the content overlap, if you are having thick cell borders
        //cell1.setUserBorderPadding(true);
        //cell2.setUserBorderPadding(true);
        //cell3.setUserBorderPadding(true);

        //table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
       
    

        env11.forEach((Evenement ev) -> {
            /*PdfPCell cell11 = new PdfPCell(new Paragraph(String.valueOf(loc.getUser().getId())));
            cell11.setPaddingLeft(10);
            cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);*/

            PdfPCell cell21 = new PdfPCell(new Paragraph(ev.getSujet()));
            cell21.setPaddingLeft(10);
            cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell21.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
 
            PdfPCell cell31 = new PdfPCell(new Paragraph(ev.getLieu()));
            cell31.setPaddingLeft(10);
            cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell41 = new PdfPCell(new Paragraph(ev.getDescription()));
            cell41.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell41.setPaddingLeft(10);
            cell41.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell51 = new PdfPCell(new Paragraph(ev.getTitre()));
            cell51.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell51.setPaddingLeft(10);
            cell51.setHorizontalAlignment(Element.ALIGN_CENTER);

         
            //cell11.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell21.setBackgroundColor(BaseColor.GRAY);
            cell31.setBackgroundColor(BaseColor.GRAY);
            cell41.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell51.setBackgroundColor(BaseColor.GRAY);
            
            //table.addCell(cell11);
            table.addCell(cell21);
            table.addCell(cell31);
            table.addCell(cell41);
            table.addCell(cell51);
     
           
        });

        try {
            document.add(table);
        } catch (DocumentException ex) {
           
        }

        document.close();
        writer.close();
    
  

    } 
     public Evenement getE() {
        return ev;
    }
}
