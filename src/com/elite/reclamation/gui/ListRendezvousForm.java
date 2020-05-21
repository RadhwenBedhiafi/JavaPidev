/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elite.reclamation.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.elite.reclamation.entities.Reclamation;
import com.elite.reclamation.entities.Rendezvous;
import com.elite.reclamation.services.ServiceReclamation;
import com.elite.reclamation.services.ServiceRendezvous;
import java.io.IOException;
import java.util.ArrayList;



/**
 *
 * @author COMPUTER
 */
public class ListRendezvousForm extends Form {

    public ListRendezvousForm(Form previous) {
        setTitle("Liste Des Rendezvous");
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
        ArrayList<Rendezvous> ListRendezvous = ServiceRendezvous.getInstance().getAllRendezvous();
        for (Rendezvous p : ListRendezvous) {

            SpanLabel sp3 = new SpanLabel();
            SpanLabel s = new SpanLabel("****Rendezvous****");
            SpanLabel sp = new SpanLabel("Lieu: " + p.getLieu());
            
            SpanLabel sp1 = new SpanLabel("Date: " + p.getDate());
            SpanLabel sp2 = new SpanLabel("Heure: " + p.getHeure());
        try {
                sp.setIcon(Image.createImage("/nature.png"));
                sp1.setIcon(Image.createImage("/date.png"));
                 sp2.setIcon(Image.createImage("/tool.png"));
            } catch (IOException ex) {
            }
            
            c2.addAll(s, sp, sp1, sp2);

        }
        try {
            Label i = new Label(Image.createImage("/interface.png"));
            i.getAllStyles().setMarginTop(100);
            c.addAll(i,c2);
             add(c);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        } catch (IOException ex) {
        }
        //sp.setText(ServiceReclamation.getInstance().getAllReclamations().toString());
       
    }

}
