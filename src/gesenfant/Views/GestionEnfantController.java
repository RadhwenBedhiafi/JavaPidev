/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesenfant.Views;
import com.itextpdf.text.BaseColor;
import static com.itextpdf.text.BaseColor.GRAY;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import gesenfant.Entities.Enfant;
import gesenfant.Service.EnfantService;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author elbaz
 */
public class GestionEnfantController implements Initializable {
    
    @FXML
    private TableView<Enfant> table;
    @FXML
    private TableColumn<Enfant, String> nom;
    @FXML
    private TableColumn<Enfant, String> prenom;
    @FXML
    private TableColumn<Enfant, String> sexe;
    @FXML
    private TableColumn<Enfant, Integer> age;
    @FXML
    private TableColumn<Enfant, String> nationalite;
    @FXML
    private TableColumn<Enfant, String> smedical;
    @FXML
    private Button afficher;
    @FXML
    private Button supprimer;
    
    private static Enfant en;
    @FXML
    private TableColumn<Enfant, String> classe;
    @FXML
    private Button retour;
    @FXML
    private Button affecter;
    @FXML
    private TextField rechercher;
    @FXML
    private Button rechercherbtn;
    @FXML
    private RadioButton iden;
    @FXML
    private RadioButton ag;
    @FXML
    private RadioButton iden1;
    @FXML
    private RadioButton ag1;
    @FXML
    private ToggleGroup tri;
    @FXML
    private Button chercher;
    @FXML
    private Button etat;
    @FXML
    private Button gnrtpdf;
        private static ArrayList<Enfant> enf11;
    @FXML
    private Button stat;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EnfantService es= new EnfantService(); 
        ArrayList<Enfant> enf= new ArrayList<Enfant>();
        try {
            enf = (ArrayList<Enfant>) es.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Enfant> obs = FXCollections.observableArrayList(enf);
        table.setItems(obs);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
        classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
        
        supprimer.setOnAction(e -> {
            //Local(String nom, String adresse, float prix,float surface,int capacite)
            en = table.getSelectionModel().getSelectedItem();
                if(en != null)
                {
                en = table.getSelectionModel().getSelectedItem();
                    try {
                        es.deleteEnfant(en);
                         Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                         alert1.setContentText("Suppression effectuée avec succès!");
                           alert1.show();

                    } catch (SQLException ex) {
                        Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    EnfantService es6= new EnfantService(); 
        ArrayList<Enfant> enf6= new ArrayList<Enfant>();
        try {
            enf6 = (ArrayList<Enfant>) es.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Enfant> obs6 = FXCollections.observableArrayList(enf6);
        table.setItems(obs6);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
        classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
                
                }

        });
        affecter.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)

            en = table.getSelectionModel().getSelectedItem();
            if (!(en == null)) {
                try {
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("ModifierEnfant.fxml"));
                    affecter.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
 afficher.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)
             EnfantService es5= new EnfantService(); 
        ArrayList<Enfant> enf5= new ArrayList<Enfant>();
        try {
            enf5 = (ArrayList<Enfant>) es5.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Enfant> obs5 = FXCollections.observableArrayList(enf5);
        table.setItems(obs5);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
        classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
        rechercher.setText("");
        });
        
        
       rechercherbtn.setOnAction(e-> {
                if (rechercher.getText().equalsIgnoreCase("") ){
                    
                    
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please , Search for something!");
                    alert.show();}
                
                EnfantService es1= new EnfantService();
                ArrayList<Enfant> enf1= new ArrayList<Enfant>();
                try {
                    String r=rechercher.getText();
                    enf1 = (ArrayList<Enfant>) es1.getByNom(r);
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Enfant> obs1 = FXCollections.observableArrayList(enf1);
                table.setItems(obs1);
                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                age.setCellValueFactory(new PropertyValueFactory<>("age"));
                nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
                smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
                classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
                enf11= (ArrayList<Enfant>) enf1;
                
            
        });  
       iden.setOnAction(e-> {
               
                EnfantService es2= new EnfantService();
                ArrayList<Enfant> enf2= new ArrayList<Enfant>();
                try {
                    enf2 = (ArrayList<Enfant>) es2.readAllID();
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Enfant> obs2 = FXCollections.observableArrayList(enf2);
                table.setItems(obs2);
                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                age.setCellValueFactory(new PropertyValueFactory<>("age"));
                nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
                smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
                classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            
        });  
        iden1.setOnAction(e-> {
               
                EnfantService es2= new EnfantService();
                ArrayList<Enfant> enf2= new ArrayList<Enfant>();
                try {
                    enf2 = (ArrayList<Enfant>) es2.readAllIDD();
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Enfant> obs2 = FXCollections.observableArrayList(enf2);
                table.setItems(obs2);
                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                age.setCellValueFactory(new PropertyValueFactory<>("age"));
                nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
                smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
                classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            
        });  ag.setOnAction(e-> {
               
                EnfantService es3= new EnfantService();
                ArrayList<Enfant> enf3= new ArrayList<Enfant>();
                try {
                    enf3 = (ArrayList<Enfant>) es3.readAllAge();
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Enfant> obs3 = FXCollections.observableArrayList(enf3);
                table.setItems(obs3);
                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                age.setCellValueFactory(new PropertyValueFactory<>("age"));
                nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
                smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
                classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            
        });  ag1.setOnAction(e-> {
               
                EnfantService es4= new EnfantService();
                ArrayList<Enfant> enf4= new ArrayList<Enfant>();
                try {
                    enf4 = (ArrayList<Enfant>) es4.readAllAgeD();
                } catch (SQLException ex) {
                    Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObservableList<Enfant> obs4 = FXCollections.observableArrayList(enf4);
                table.setItems(obs4);
                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                age.setCellValueFactory(new PropertyValueFactory<>("age"));
                nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
                smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
                classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            
        });  
       retour.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
                retour.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }); 
       chercher.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("ListeClasse.fxml"));
                chercher.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }); 
       etat.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("EtatMedical.fxml"));
                etat.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
       stat.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
                stat.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
         EnfantService es11= new EnfantService(); 
        enf11= new ArrayList<Enfant>();
        try {
            enf11 = (ArrayList<Enfant>) es11.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(GestionEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Enfant> obs21 = FXCollections.observableArrayList(enf11);
        table.setItems(obs21);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        smedical.setCellValueFactory(new PropertyValueFactory<>("smedical"));
        classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
    
    gnrtpdf.setOnAction(e->{
            
           GeneratePdf();
           Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                         alert1.setContentText("Fichier generé avec succès!");
                           alert1.show();
        }); 
    }
   public static void GeneratePdf() {
       
       
        Document document = new Document();

        PdfWriter writer = null;    //C:\Users\malek.heni\Desktop
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\elbaz\\Desktop\\Liste.pdf"));
        } catch (FileNotFoundException ex) {
        } catch (DocumentException ex) {
        }
        document.open();
        document.addTitle("Liste des classes");
        document.addSubject("Jardin d'enfant");
        document.addAuthor("ELITE");
        document.addCreator("Bedhiafi Radhwen");
        Paragraph preface = new Paragraph();
        preface.add(new Paragraph("Liste des classes:"));
        preface.add(new Paragraph("Rapport genéré par: Bedhiafi Radhwen" + ", le " + new Date()));
        try {
            document.add(preface);
        } catch (DocumentException ex) {
        }
        PdfPTable table = new PdfPTable(7); // 3 columns.
        table.setWidthPercentage(100); //Width 100%
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table

        //Set Column widths
        float[] columnWidths = {0.75f, 2f, 2.5f, 1f, 1f, 1f, 1f};
        try {
            table.setWidths(columnWidths);
        } catch (DocumentException ex) {
        }
        /*PdfPCell cell1 = new PdfPCell(new Paragraph("Id"));
        cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell1.setPaddingLeft(10);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);*/

        PdfPCell cell2 = new PdfPCell(new Paragraph("NOM"));
        cell2.setBackgroundColor(BaseColor.GRAY);
        cell2.setPaddingLeft(10);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell3 = new PdfPCell(new Paragraph("PRENOM"));
        cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell4 = new PdfPCell(new Paragraph("SEXE"));
        cell4.setBackgroundColor(BaseColor.GRAY);
        cell4.setPaddingLeft(10);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell5 = new PdfPCell(new Paragraph("AGE"));
        cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell5.setPaddingLeft(10);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell6 = new PdfPCell(new Paragraph("NATIONALITE"));
        cell6.setBackgroundColor(BaseColor.GRAY);
        cell6.setPaddingLeft(10);
        cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
        PdfPCell cell7 = new PdfPCell(new Paragraph("STATUS MEDICAL"));
        cell7.setBackgroundColor(BaseColor.GRAY);
        cell7.setPaddingLeft(10);
        cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
        PdfPCell cell8 = new PdfPCell(new Paragraph("CLASSE"));
        cell8.setBackgroundColor(BaseColor.GRAY);
        cell8.setPaddingLeft(10);
        cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
        //To avoid having the cell border and the content overlap, if you are having thick cell borders
        //cell1.setUserBorderPadding(true);
        //cell2.setUserBorderPadding(true);
        //cell3.setUserBorderPadding(true);

        //table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);
        table.addCell(cell7);
        table.addCell(cell8);


        enf11.forEach((Enfant en) -> {
            /*PdfPCell cell11 = new PdfPCell(new Paragraph(String.valueOf(loc.getUser().getId())));
            cell11.setPaddingLeft(10);
            cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);*/

            PdfPCell cell21 = new PdfPCell(new Paragraph(en.getNom()));
            cell21.setPaddingLeft(10);
            cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell21.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell31 = new PdfPCell(new Paragraph(en.getPrenom()));
            cell31.setPaddingLeft(10);
            cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell41 = new PdfPCell(new Paragraph(en.getSexe()));
            cell41.setPaddingLeft(10);
            cell41.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell41.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell51 = new PdfPCell(new Paragraph(String.valueOf(en.getAge())));
            cell51.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell51.setPaddingLeft(10);
            cell51.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell61 = new PdfPCell(new Paragraph(en.getNationalite()));
            cell61.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell61.setPaddingLeft(10);
            cell61.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell71 = new PdfPCell(new Paragraph(en.getSmedical()));
            cell71.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell71.setPaddingLeft(10);
            cell71.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell81 = new PdfPCell(new Paragraph(en.getClasse()));
            cell81.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell81.setPaddingLeft(10);
            cell81.setHorizontalAlignment(Element.ALIGN_CENTER);

            //cell11.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell21.setBackgroundColor(BaseColor.GRAY);
            cell31.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell41.setBackgroundColor(BaseColor.GRAY);
            cell51.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell61.setBackgroundColor(BaseColor.GRAY);
            cell71.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell81.setBackgroundColor(BaseColor.LIGHT_GRAY);

            //table.addCell(cell11);
            table.addCell(cell21);
            table.addCell(cell31);
            table.addCell(cell41);
            table.addCell(cell51);
            table.addCell(cell61);
            table.addCell(cell71);
            table.addCell(cell81);
        });

        try {
            document.add(table);
        } catch (DocumentException ex) {
           
        }

        document.close();
        writer.close();
        File pdffile = new File("C:\\Users\\elbaz\\Desktop\\Liste.pdf");
           try {
               Desktop.getDesktop().open(pdffile);
           } catch (IOException ex) {
               Logger.getLogger(EnfantService.class.getName()).log(Level.SEVERE, null, ex);
           }

    
  

    }    
    public Enfant getE() {
        return en;
    }
}

