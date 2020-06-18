/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Event;
import Entities.Participation;
import static Forms.AddComment.id;
import Utils.WebService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;

/**
 *
 * @author Jasser
 */
public class AddParticiper extends BaseForm{
    public static int id ; 
    
    public AddParticiper(){
        setName("Ajouter participation");
        setTitle("Ajouter participation");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
               ListPart.id = id ; 
            ListPart lc = new ListPart();
        });
        getToolbar().addCommandToRightSideMenu("Ajouter évenements", icon, (e) -> {
          AddEvent ae = new AddEvent();
           
        });
         getToolbar().addCommandToRightSideMenu("Liste des évenements", icon, (e) -> {
           
           ListEvent le = new ListEvent();
        });
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            TextField contenu = new TextField();
            contenu.setHint("indiquer votre nom et prenom");         
            Button b = new Button("Ajouter");
            
            WebService ws = new WebService();
           photos.add(contenu);
            photos.add(b);
            add(photos);
            b.addActionListener(ev->{
               
                Participation c = new Participation();
                c.setNom(contenu.getText());
                c.setInevent(id);

                ws.addParticiper(c);
                ListPart.id = id;
                ListPart lc = new ListPart();
                
                lc.show();
                });
        show();
    }
    
}
