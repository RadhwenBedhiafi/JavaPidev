/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elite.club.gui;

/**
 *
 * @author benour
 */
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.elite.club.entities.Club;
import com.elite.club.services.Clubservice;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author bhk
 */
public class ListClubsForm extends Form{
    Form current;
    public ListClubsForm(Form previous) {
        setTitle("List clubs");
        
        
        
        ArrayList<Club> ListClubs = Clubservice.getInstance().getAllClubs();
        for (Club cl : ListClubs) {
            
             
            Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           // SpanLabel s = new SpanLabel("***Club***");
          Image i;
            try {
                i = Image.createImage("/"+cl.getImage());
                          Label spimage = new Label("",i);
                          c.add(spimage);

            } catch (IOException ex) {
            }
         
            SpanLabel sp = new SpanLabel("Nom club: " + cl.getNomclub());
            SpanLabel sp1 = new SpanLabel("description: " + cl.getDescription());
            SpanLabel sp2 = new SpanLabel("horraire: " + cl.getHorraire());
            SpanLabel sp3 = new SpanLabel("tarif: " + cl.getTarif());
            SpanLabel sp4 = new SpanLabel("capacite: " + cl.getCapacite());
           
                    
            SpanLabel s4 = new SpanLabel("           ");
        
        c.addAll(sp,sp1,sp2,sp3,sp4,s4);
        Button btnAjouterDemande = new Button("ajouter demande");
        
        btnAjouterDemande.addActionListener(e-> new AddDemandeForm(current).show());
        add(c);
        addAll(btnAjouterDemande);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new HomeClubForm().show());
        
    }
    
    
}
}