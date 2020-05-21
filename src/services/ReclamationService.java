/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DataBase.DataSource;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import controllers.ReclamationController;
import entities.Reclamation;
import entities.Rendezvous;
import entities.User;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author COMPUTER
 */
public class ReclamationService {
    
       Connection connexion;
       
        
    public ReclamationService() {
       connexion=DataSource.getInstance().getCnx();
    }
    
    public void ajouterReclamation(Reclamation r) throws SQLException {
        String req = "INSERT INTO reclamation (destinataire, description, idUser) VALUES (?, ?, ?) ";
        String req1="UPDATE reclamation set username= (select username from user where id = ? ) where idUser=?";
        String req2="UPDATE reclamation set email= (select email from user where id = ? ) where idUser=?";
        //java.sql.Date sqlDate = new java.sql.Date(r.getDate().getTime());
        PreparedStatement pstm = connexion.prepareStatement(req);
        PreparedStatement pstm1 = connexion.prepareStatement(req1);
        PreparedStatement pstm2 = connexion.prepareStatement(req2);
        User u = new User();
        pstm.setString(1, r.getDestinataire());
        pstm.setString(2, r.getDescription());
        pstm.setInt(3, r.getIdUser());
        pstm1.setInt(1, r.getIdUser());
        pstm1.setInt(2, r.getIdUser());
        pstm2.setInt(1, r.getIdUser());
        pstm2.setInt(2, r.getIdUser());
       //pstm1.setInt(2, u.getId());
        
       // pstm.setDate(3, sqlDate);
       //r.setEdit(button);
        
        pstm.executeUpdate();
        pstm1.executeUpdate();
        pstm2.executeUpdate();

    }
    
        public List<Reclamation> rechercheReclamations(String type, String valeur) {
        List<Reclamation> myList = new ArrayList<Reclamation>();
        String requete = null;
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            if (type.equals("destinataire")) {
                requete = "SELECT * from reclamation where destinataire like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("description")) {
                requete = "SELECT * from reclamation where description like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("etat") && valeur.equals("Traitée")) {
                requete = "SELECT * from reclamation where etat=1 "; //MAJUSCULE NON OBLIGATOIRE 
            }  else if (type.equals("etat") && valeur.equals("Non traitée")) {
                requete = "SELECT * from reclamation where etat=0 "; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("date")) {
                requete = "SELECT * from reclamation where date like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            }  else if (type.equals("username")) {
                requete = "SELECT * from reclamation where username like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("email")) {
                requete = "SELECT * from reclamation where email like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Tout")) {
                requete = "SELECT * from reclamation where etat like '%" + valeur + "%' or destinataire like '%" + valeur + "%' or description like '%" + valeur + "%' or date like '%" + valeur + "%'  or username like '%" + valeur + "%' or email like '%" + valeur + "%' "; //MAJUSCULE NON OBLIGATOIRE 
            }

            Statement pst = DataSource.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Reclamation R = new Reclamation();
                R.setId(rs.getInt(1));
                R.setDestinataire(rs.getString(2));
                R.setDescription(rs.getString(3));
                R.setIdUser(rs.getInt(4));
                R.setUsername(rs.getString(5));
                R.setEmail(rs.getString(6));
                R.setDate(rs.getDate(7));
                R.setEtat(rs.getInt(8));
                Button edit = new Button("edit");
                Button accepter = new Button("accepter");
                
                R.setEdit(edit);
                R.setAccepter(accepter);
                 if (R.getEtat() == 1)
                    {accepter.setDisable(true);
                    edit.setDisable(true);
                    }
                
                
                
                       edit.setOnAction(s -> {
                           
            
                if (R.getEdit() == edit) {
                    System.out.println("id: "+R.getId());
                    Integer a = R.getId();
                    System.out.println("destinataire: "+R.getDestinataire());
                    String b = R.getDestinataire();
                    System.out.println("description: "+R.getDescription());
                    String c = R.getDescription();
                    System.out.println("date: "+R.getDate());
                    System.out.println("etat: "+R.getEtat());
                    Integer q = R.getEtat();
                    
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
            
                if (R.getAccepter() == accepter) {
                    System.out.println("id: "+R.getId());
                    Integer a = R.getId();
                    
                   
                   
                    
                  
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
              
    
                myList.add(R);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }
        
         
                      ObservableList<Reclamation> oc = FXCollections.observableArrayList();
        public ObservableList afficherReclamation()  {
            Date d=new Date(0);    
            //Button edit = new Button("edit");
            String req= " select * from reclamation  ";
            
            Statement st;
            
            
         try {
            st=connexion.createStatement();
            ResultSet rs=st.executeQuery(req);
            //ResultSet rs1 =st.executeQuery(req2);
            while(rs.next() )
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
                    
                    //e.setEtat(rs.getInt(6));
                   
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
                    }   
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oc;
    }
        
            public void supprimerReclamation(Integer r) {
        try {
            String requete = "delete from `reclamation` where id=?";
            PreparedStatement ps;
            ps = DataSource.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, r);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
             public void modifierReclamation(Reclamation r, int id) throws SQLException {
        String req = "UPDATE reclamation set (destinataire, description,etat) where id =? ";
        //java.sql.Date sqlDate = new java.sql.Date(r.getDate().getTime());
        PreparedStatement pstm = connexion.prepareStatement(req);
        pstm.setString(1, r.getDestinataire());
        pstm.setString(2, r.getDescription());
        pstm.setInt(3, r.getEtat());
       // pstm.setDate(3, sqlDate);

        pstm.executeUpdate();
    }
             
 public void pdf () throws SQLException, FileNotFoundException, DocumentException
 {
         String reclamationTable = "D:\\reclamationTable.pdf";
        Document document = new Document();
         PdfWriter.getInstance(document, new FileOutputStream(reclamationTable));
            document.open();
            
        PreparedStatement pst ;
        String query = "select * from reclamation";
        pst = connexion.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        Paragraph p = new Paragraph("Les Reclamations");
        p.setAlignment(Paragraph.ALIGN_CENTER);
        
       
        Paragraph pa = new Paragraph("  ");
        document.add(p);
        document.add(pa);
       
        PdfPTable table = new PdfPTable(6);
       
       PdfPCell c8 =new PdfPCell(new Phrase("Destinataire"));
        table.addCell(c8);

        c8 =new PdfPCell(new Phrase("Description"));
        table.addCell(c8);

        c8 =new PdfPCell(new Phrase("Parent"));
        table.addCell(c8);

        c8 =new PdfPCell(new Phrase("Email"));
        table.addCell(c8);

        c8 =new PdfPCell(new Phrase("Date"));
        table.addCell(c8);

        c8 =new PdfPCell(new Phrase("Etat"));

                        
        table.addCell(c8);
                     table.setHeaderRows(1);
                    table.setWidthPercentage(110);
                     table.setWidths(new float[] {80,75,75,150,100,40 });


                        
                       //document.add(table);

        while (rs.next()) {
            //Paragraph p = new Paragraph("Destinataire: "+rs.getString("destinataire")+" Description: "+rs.getString("description"));
            //document.add(p);
            //document.add(new Paragraph(" "));
            
            table.addCell(new Phrase(rs.getString("destinataire")));
            table.addCell((new Phrase(rs.getString("description"))));
            table.addCell(new Phrase(rs.getString("username")));
            table.addCell(new Phrase(rs.getString("email")));
            table.addCell(new Phrase(rs.getString("date")));
            if (rs.getInt("etat")==1)
            {table.addCell(new Phrase("Traitée"));}
            else{
                table.addCell(new Phrase("Non traitée"));
            }
                       
            

            
           
            
        }
                    document.add(table);

        
           
            //add table
            document.close();
            File pdffile = new File("D:\\reclamationTable.pdf");
           try {
               Desktop.getDesktop().open(pdffile);
           } catch (IOException ex) {
               Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
           }
            
            
            //document.close();
            System.out.println("finished");
        
 }
            



    
}
