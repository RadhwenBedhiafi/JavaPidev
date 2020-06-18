/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enfant.gui;
import com.codename1.capture.Capture;
import static com.mycompany.enfant.entities.SMS.SMS_DON;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.enfant.entities.Enfant;
import com.mycompany.enfant.service.EnfantService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author elbaz
 */
public class InscriptionForm extends Form{
    private String im;


    InscriptionForm(Form previous) {
         setTitle("Espace d'inscription");
         Container c=new Container(BoxLayout.x());
         Container c1=new Container(BoxLayout.x());
         Container c2=new Container(BoxLayout.x());
         Container c3=new Container(BoxLayout.x());
         Container c4=new Container(BoxLayout.x());
         Container c5=new Container(BoxLayout.x());
         Container c6=new Container(BoxLayout.x());
         Container c7=new Container(BoxLayout.y());
         Container c8=new Container(BoxLayout.y());
         Button retour=new Button("Retour au menu précedent");
         Button ajouter=new Button("Validez!");
          SpanLabel nom = new SpanLabel("Nom");
      SpanLabel prenom = new SpanLabel("Prénom:");
      SpanLabel sexe = new SpanLabel("Sexe:");
      SpanLabel age = new SpanLabel("Age:");
      SpanLabel nationalite = new SpanLabel("Nationalité");
      SpanLabel smedical = new SpanLabel("Status médical:");
      Button image1=new Button("Inserer votre image");
      


         TextField nom1=new TextField();
         TextField prenom1=new TextField();
        ComboBox sexe1 = new ComboBox();
      sexe1.addItem("Garçon");
      sexe1.addItem("Fille");         
      TextField age1= new TextField();
         TextField nationalite1= new TextField();
         TextField smedical1= new TextField();
         c.add(retour);
         c1.add(smedical).add(smedical1);
         c2.add(nom).add(nom1);
         c3.add(prenom).add(prenom1);
         c4.add(sexe).add(sexe1);
         c5.add(age).add(age1);
         c6.add(nationalite).add(nationalite1);
         c8.add(image1);
         
         c7.add(c).add(c2).add(c3).add(c4).add(c5).add(c6).add(c1).add(c8).add(ajouter)   ;
         add(c7);
         retour.addActionListener(e -> previous.showBack());
         image1.addActionListener(e->{
            try {
                String fileNameInServer = "";
                MultipartRequest cr = new MultipartRequest();
                String filepath = Capture.capturePhoto(-1, -1);
                cr.setUrl("http://localhost/uploadimage.php");
                cr.setPost(true);
                String mime = "image/jpeg";
                cr.addData("file", filepath, mime);
                String out = new com.codename1.l10n.SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                cr.setFilename("file", out + ".jpg");//any unique name you want
                fileNameInServer += out + ".jpg";
                System.err.println("path2 =" + fileNameInServer);
                im=fileNameInServer ;
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                NetworkManager.getInstance().addToQueueAndWait(cr);
                
                } catch (IOException ex) {}  
        });
         ajouter.addActionListener(new ActionListener()
        { 
             
            
             
            @Override
            public void actionPerformed(ActionEvent evt) {
              
                ArrayList<Enfant> listEnfants = new ArrayList<>();
              EnfantService ser=new EnfantService();
              if((nom1.getText().length()==0)||(prenom1.getText().length()==0)||
                                (sexe1.getSelectedItem().toString().length()==0)
                                ||(age1.getText()).length()==0||(nationalite1.getText().toString().length()==0)
                                ||(smedical1.getText().toString().length()==0))
                             Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
              int x=Integer.parseInt(age1.getText());
              String n=nom1.getText();
              if((x>5)||(x<0))
                  Dialog.show("Alert", "Veuillez introduire un age inferieure a 5 ans!", new Command("OK"));
              
                else
                {
                    
                        
                        Enfant t = new Enfant(nom1.getText(), prenom1.getText(), sexe1.getSelectedItem().toString()
                                , Integer.parseInt(age1.getText()), nationalite1.getText(), smedical1.getText(),im);
                        
                    try {
                        ser.addTask(t);
                        System.out.println(t.getImage());
                         if( EnfantService.getInstance().addTask(t)){
                            Dialog.show("Success","Inscription effectuée",new Command("OK"));
                            String id1=nom1.getText();
                            String id2=prenom1.getText();
                            SMS_DON(id1,id2);
                         }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));

                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                
                
                
            }
            

        
            }
            });
    }
}
    
                 



