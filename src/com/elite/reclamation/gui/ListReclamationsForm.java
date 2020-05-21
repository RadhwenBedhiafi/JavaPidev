/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elite.reclamation.gui;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Stroke;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.RoundBorder;
import com.elite.reclamation.entities.Reclamation;
import com.elite.reclamation.services.ServiceReclamation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author COMPUTER
 */
public class ListReclamationsForm extends Form {
    private Map<String, Object> createListEntry(String dest, String desc,Image icon, String etat) {
  Map<String, Object> entry = new HashMap<>();
  entry.put("Line1", dest);
  entry.put("Line2", desc);
  entry.put("icon", icon);
  entry.put("Line3", etat);
 // entry.put("Line4", entry);
  return entry;
}

    public ListReclamationsForm(Form previous) {
        setTitle("Liste Des Reclamations");
       // this.getAllStyles().setBgColor(0x09224a);
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        //c2.getStyle().setBgColor(0x09224a);
        //c2.getStyle().setBgTransparency(255);
         ArrayList<Map<String, Object>> data = new ArrayList<>();

        
        ArrayList<Reclamation> ListReclamations = ServiceReclamation.getInstance().getAllReclamations();
        for (Reclamation p : ListReclamations) {
            String a= String.valueOf(p.getEtat());
            String result ="";
             SpanLabel sp3 = new SpanLabel();
             Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
             
            SpanLabel s = new SpanLabel("****Reclamation****");
             c.getStyle().setBgColor(0xffffff);
             c.getStyle().setBgTransparency(255);
             //c.getAllStyles().setBorder(RoundBorder.create().rectangle(true).strokeColor(0).strokeOpacity(120));
             c.getStyle().setMarginLeft(70);
             
            SpanLabel sp = new SpanLabel("Destinataire: " + p.getDestinataire());
            SpanLabel sp1 = new SpanLabel("Description: " + p.getDescription());
            SpanLabel sp2 = new SpanLabel("Date: " + p.getDate());
            SpanLabel s4 = new SpanLabel("");
            if (p.getEtat() == 0) {
                result = "Etat: non traitée";
                sp3.setText(result);
                
                
                
            }
            else if (p.getEtat() == 1) {
                result ="Etat: traitée";
                sp3.setText(result);
        
                                
            }
            try {
                sp3.setIcon(Image.createImage("/status.png"));
                 sp.setIcon(Image.createImage("/dest.png"));
                  sp1.setIcon(Image.createImage("/document.png"));
            } catch (IOException ex) {
            }
            try {
                data.add(createListEntry(p.getDestinataire(),"Description: "+ p.getDescription(),Image.createImage("/Communication.png"),result));
                
                // c.addAll(s, sp, sp1,sp2, sp3);
                // c2.addAll(c,s4);
            } catch (IOException ex) {
                
            }

        }
         DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
  MultiList ml = new MultiList(model);
  ml.getBaseline(100, 100);
  ml.setWidth(1500);
  c2.add( ml);
  
        //sp.setText(ServiceReclamation.getInstance().getAllReclamations().toString());
        add(c2);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
