/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enfant.gui;

import com.codename1.components.SpanLabel;
import com.codename1.notifications.LocalNotification;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.enfant.service.EnfantService;
import com.mycompany.enfant.entities.Enfant;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author elbaz
 */
public class ListEnfantForm extends Form {
     Form current;
    public ListEnfantForm(Form previous) {
        setTitle("Liste des enfants");
        
        
          
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");


        Display.getInstance().scheduleLocalNotification(n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
        ArrayList<Enfant> ListEnfants = EnfantService.getInstance().getListEnfant();
        for (Enfant el : ListEnfants) {
            
             
            Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           // SpanLabel s = new SpanLabel("***Club***");
         
            SpanLabel sp = new SpanLabel("Nom : " + el.getNom());
            SpanLabel sp1 = new SpanLabel("Prenom: " + el.getPrenom());
            SpanLabel sp2 = new SpanLabel("Sexe: " + el.getSexe());
            SpanLabel sp3 = new SpanLabel("Age: " + el.getAge()+" ans");
            SpanLabel sp4 = new SpanLabel("Nationalite: " + el.getNationalite());
            SpanLabel sp5 = new SpanLabel("Status médical: " + el.getSmedical());


           
                    
            SpanLabel s4 = new SpanLabel("-------------------------------------------------------------");
        
        c.addAll(sp,sp1,sp2,sp3,sp4,sp5,s4);
        Button btnAjouterDemande = new Button("Inscrire votre enfant maintenant");
        
        btnAjouterDemande.addActionListener(e-> new InscriptionForm(current).show());
        add(c);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
    }
    
    
}
}
