/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Event;
import Services.Services;
import Utils.WebService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import com.teknikindustries.bulksms.SMS;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author 
 */
public class ListEvent extends BaseForm{
    
    public ListEvent(){
        setName("liste des évenements");
        setTitle("liste des évenements");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           
           getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
             if (Dialog.show("DANGEREUX","si vous etes ADMIN introduire le code de confirmation " ,"OK"," Sortir")){
              
              if (Dialog.show(null, null, "66","22")){
              AddEvent ae = new AddEvent();
              ae.show();
              
              
             /*!
                        //API SMS
                        SMS sms = new SMS();
                        String nt = "+21652764555";
                        sms.SendSMS("28599636", "Houssem1995", "Code de confirmation : 66" , nt, "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
                       */
             }
               
           
             }
               
            // ListEvent le = new ListEvent();
                   });
          
         getToolbar().addCommandToRightSideMenu("Ajouter évenements", icon, (e) -> {
          AddEvent ae = new AddEvent();
           
        });
         getToolbar().addCommandToRightSideMenu("Liste des évenements", icon, (e) -> {
           
           ListEvent le = new ListEvent();
        });
           
             
             
            
             WebService ws = new WebService();
    Services ds = new Services();
    Map x = ws.getResponse("ListE");
    ArrayList<Event> listevents = ds.getListsProduits(x);
             for (Event e : listevents) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            Label a = new Label("Titre : "+e.getTitre());
            Label l = new Label("Lieu : "+e.getLieu());
            Label d = new Label("De : "+e.getDate());
            
            FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
           
            Container part = new Container(BoxLayout.y());
            Button b = new Button("Voir");
           // Button xx = new Button("participer");
            
            
           
                       
            
            try {
                encoded = EncodedImage.create("/like.png");
            } catch (IOException ex) {
            }
            img = URLImage.createToStorage(encoded, e.getImage(), "http://127.0.0.1:8000/images/" + e.getImage());
            imv = new ImageViewer(img);
            photos.add(imv);
             
            photos.add(a);
            photos.add(l);
            photos.add(d);
            photos.add(b);
          //  photos.add(xx);
            
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                
                
                photos.add(sep);
            } catch (IOException ex) {
            }
            add(photos);
            
            
            
             b.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                   EventDetails.e = e ; 
                   EventDetails ed = new EventDetails();
                   ed.show();
                }
            });
             
            
        }
        show();
    }
    
}
