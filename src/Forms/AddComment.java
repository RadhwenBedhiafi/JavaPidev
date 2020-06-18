/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Event;
import Entities.commentaire;
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
public class AddComment extends BaseForm{
    public static int id ; 
    
    public AddComment(){
        setName("Ajouter commentaire");
        setTitle("Ajouter commentaire");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
               Listcomments.id = id ; 
            Listcomments lc = new Listcomments();
        });
        getToolbar().addCommandToRightSideMenu("Ajouter évenements", icon, (e) -> {
          AddEvent ae = new AddEvent();
           
        });
         getToolbar().addCommandToRightSideMenu("Liste des évenements", icon, (e) -> {
           
           ListEvent le = new ListEvent();
        });
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            TextField contenu = new TextField();
            contenu.setHint("Commentaire");         
            Button b = new Button("Ajouter");
            WebService ws = new WebService();
           photos.add(contenu);
            photos.add(b);
            add(photos);
            b.addActionListener(ev->{
               
                commentaire c = new commentaire();
                c.setCommentaire(contenu.getText());
                c.setInevent(id);
                ws.addCommentaire(c);
                Listcomments.id = id;
                Listcomments lc = new Listcomments();
                lc.show();
                });
        show();
    }
    
}
