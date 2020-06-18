/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Event;
import Entities.Participation;
import Services.Services;
import Utils.WebService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Dialog;
import com.teknikindustries.bulksms.SMS;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author 
 */
public class ListPart extends BaseForm{
    public static int id ; 
    public ListPart(){
        setName("Liste des participants");
        setTitle("liste des participants");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
           ListEvent le = new ListEvent();
           le.show();
        });
           getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
          AddParticiper.id = id ; 
          AddParticiper ac = new AddParticiper();
          ac.show();
        });
          
        getToolbar().addCommandToRightSideMenu("Ajouter évenements", icon, (e) -> {
          AddEvent ae = new AddEvent();
           
        });
         getToolbar().addCommandToRightSideMenu("Liste des évenements", icon, (e) -> {
           
           ListEvent le = new ListEvent();
        });
           
             
             
            
             WebService ws = new WebService();
    Services ds = new Services();
    Map c = ws.getResponse("ListP/"+id);
    ArrayList<Participation> listevents = ds.getListsPart(c);
             for (Participation e : listevents) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            Label a = new Label("Participant : "+e.getNom());
            
            
              
            
            
            
            
            
            
             
            photos.add(a);
          
            
            
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                
                
                photos.add(sep);
            } catch (IOException ex) {
            }
            
            add(photos);
            
            
            
            
             
            
        }
             
        show();
    }
    
}
