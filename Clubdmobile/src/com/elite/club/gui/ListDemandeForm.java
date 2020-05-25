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
import com.elite.club.entities.demandeadhesion;
import com.elite.club.services.Clubservice;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListDemandeForm extends Form{
    Form current;
    public ListDemandeForm(Form previous) {
        setTitle("List Demande user connecte");
        ArrayList<demandeadhesion> ListDemandes = Clubservice.getInstance().getAllDemandes();
        for (demandeadhesion d : ListDemandes) {
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            SpanLabel s = new SpanLabel("+++votre demande+++");
          
            SpanLabel sp = new SpanLabel("dureInsc: " + d.getDureInsc());
            SpanLabel sp1 = new SpanLabel("modePayment: " + d.getModePayment());
            SpanLabel sp2 = new SpanLabel("ancienClub: " + d.getAncienClub());
            SpanLabel sp3 = new SpanLabel("objectif: " + d.getObjectif());
            SpanLabel sp4 = new SpanLabel("etatDemande: " + d.getEtatDemande());
           
                    
            SpanLabel s4 = new SpanLabel("           ");
        
        c.addAll(s,sp,sp1,sp2,sp3,sp4,s4);
        add(c);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
    }
    
    
}
}
