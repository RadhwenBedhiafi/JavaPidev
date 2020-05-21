/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elite.reclamation.gui;

import com.codename1.components.ImageViewer;
import com.codename1.notifications.LocalNotification;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.elite.reclamation.entities.Reclamation;
import com.elite.reclamation.services.ServiceReclamation;
import java.io.IOException;

/**
 *
 * @author COMPUTER
 */
public class AddReclamationForm extends Form implements LocalNotificationCallback{
    

        public AddReclamationForm(Form previous) throws IOException {
        setTitle("Envoyez Vos Reclamations");
        setLayout(BoxLayout.y());
        this.getAllStyles().setBgColor(0x09224a);
        
        ImageViewer img = null;
        img = new ImageViewer(Image.createImage("/problem.png"));

       
        
        //ComboBox<String> combo = new ComboBox<> ("Directeur général","Responsable technique","Responsable informatique");
       // TextField tDestinataire = new TextField("","Destinataire");
        TextField tDescription= new TextField("","Description");
        AutoCompleteTextField tDestinataire = new AutoCompleteTextField("Directeur général", "Responsable technique", "Responsable informatique");
        tDestinataire.setMinimumElementsShownInPopup(5);
        tDestinataire.setHint("Destinataire");
        
        //tDescription.setHint("Description");
        //Style s = tDescription.getAllStyles();
        //s.setPaddingUnit(Style.UNIT_TYPE_DIPS);
        //s.setPadding(6, 6, 6, 6);
        //s.setPaddingLeft(1);
        //s.setPaddingTop(1);
        //tDestinataire.getAllStyles().setBgColor(0x99CCCC);
        tDestinataire.getAllStyles().setBorder(RoundBorder.create().rectangle(true).color(0xffffff).stroke(new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1)).
        strokeColor(0).strokeOpacity(120));
        tDescription.getAllStyles().setBorder(RoundBorder.create().rectangle(true).color(0xffffff).stroke(new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 4)).
        strokeColor(0).strokeOpacity(120));
        Button btnValider = new Button("Envoyer Reclamation");
        btnValider.getAllStyles().setFgColor(0xFA2C56);
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tDestinataire.getText().length()==0)||(tDescription.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Reclamation t = new Reclamation(tDestinataire.getText(), tDescription.getText());
                        if( ServiceReclamation.getInstance().addReclamation(t))
                        {Dialog.show("Success","Reclamation Envoyée",new Command("OK"));
                         LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        //n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound

        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 *1000 , // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
        
        
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (Exception e) {
                        Dialog.show("ERROR", "exception", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        add(img);
        addAll(tDestinataire,tDescription,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
    }

    @Override
    public void localNotificationReceived(String notificationId) {
                System.out.println("Received local notification "+notificationId);
    }

    
}
