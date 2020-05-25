/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elite.club.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ListModel;
import com.elite.club.entities.Club;
import com.elite.club.entities.demandeadhesion;
import com.elite.club.services.Clubservice;
import java.io.IOException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 * @author bhk
 */
public class AddDemandeForm extends Form{
Form current;
    public AddDemandeForm(Form clublist) {
        setTitle("Add a new demande");
        setLayout(BoxLayout.y());
                //    Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        TextField tfDure = new TextField("","Dure d'incscrit");
        TextField tfModepayment= new TextField("", "Mode payment");
        TextField tfAncienclub= new TextField("", "Ancien Club");
        TextField tfObjectif= new TextField("", "Objectif");
    
       
        Button btnValider = new Button("Add task");
          btnValider.addActionListener((e)->{
            String myURL = "https://rest.nexmo.com/sms/json?api_key=2e5ab688&api_secret=RcH6nUtVQahRGVwN&to=21658855918" + "&from=Elite Kids&text=Ajouté avec succès";
            ConnectionRequest cntRqst = new ConnectionRequest() {
                protected void readResponse(InputStream in) throws IOException {
                }
                @Override
                protected void postResponse() {
                    Dialog.show("SMS", "SMS Envoyé Avec Succès", "OK", null);
                }
            };
            cntRqst.setUrl(myURL);
            NetworkManager.getInstance().addToQueue(cntRqst);
        }
        );
        
      
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDure.getText().length()==0)||(tfModepayment.getText().length()==0)||(tfAncienclub.getText().length()==0)||(tfObjectif.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    
                        demandeadhesion d = new demandeadhesion(tfDure.getText(),tfModepayment.getText(),tfAncienclub.getText(),tfObjectif.getText());
                        if( Clubservice.getInstance().adddemande(d))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                    
                }
                
                
            }
        });
        addAll(tfDure,tfModepayment,tfAncienclub,tfObjectif,btnValider);
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ListClubsForm(current).show());       
    }
    
    
}

