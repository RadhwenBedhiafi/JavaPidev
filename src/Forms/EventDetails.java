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
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class EventDetails extends BaseForm{
    public static Event e ;
    
    public EventDetails(){
         WebService ws = new WebService();
        setName("Détails");
        setTitle("Détails");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
           ListEvent le = new ListEvent();
           le.show();
        });
           
            getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_TEXT_FIELDS, ev -> {
          Listcomments.id = e.getId();
          Listcomments lc = new Listcomments();
          lc.show();
        });
             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_HEALING, ev -> {
          ListPart.id = e.getId();
          ListPart lc = new ListPart();
          lc.show();
        });
          
        getToolbar().addCommandToRightSideMenu("Ajouter évenements", icon, (e) -> {
          AddEvent ae = new AddEvent();
           
        });
         getToolbar().addCommandToRightSideMenu("Liste des évenements", icon, (e) -> {
           
           ListEvent le = new ListEvent();
        });
             
             
            
            
    
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            Label a = new Label("Titre : "+e.getTitre());
            Label l = new Label("Lieu : "+e.getLieu());
            Label d = new Label("Date : "+e.getDate());
          
            
            Label f = new Label("Description : "+ e.getDescription());
            int c = ws.count(e.getId());
            Label co = new Label("Commentaires : "+ c);
            int v = ws.Count(e.getId());
            Label po = new Label("Participation : "+ v);
            FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
           
         //   Button b = new Button("Modifier");
           // Button bo = new Button("Supprimer");
            
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
            photos.add(f);
            photos.add(co);
            photos.add(po);
       //     photos.add(b);
         //   photos.add(bo);
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                
                
                photos.add(sep);
            } catch (IOException ex) {
            }
            add(photos);
            
            
            
      /*       b.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                   EditEvent.ev = e ; 
                   EditEvent ee = new EditEvent();
                   ee.show();
                }
            });
             bo.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                   ws.deleteEvent(e.getId());
                   ListEvent le = new ListEvent();
                   le.show();
                }
            });
             */
            
        
        show();
        
    }
    
}
