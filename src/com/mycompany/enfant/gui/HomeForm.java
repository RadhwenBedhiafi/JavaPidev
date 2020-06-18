/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enfant.gui;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;

/**
 *
 * @author elbaz
 */
public class HomeForm extends Form {

    Form current;
    public HomeForm() throws IOException {
          current = this;

        current.getToolbar().getAllStyles().setBgImage(Image.createImage("/communication.png"));
        setTitle("Jardin d'enfant ELITE");
        setLayout(BoxLayout.y());
        this.getAllStyles().setBgColor(0x09224a);
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.setX(CENTER);
        Container c1=new Container(new BoxLayout(BoxLayout.X_AXIS));
        c1.setWidth(500);
        Container c2=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c3=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c4=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c5=new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container c6=new Container(new BoxLayout(BoxLayout.X_AXIS));




        Label s = new Label("                    Nos services:");
        s.getAllStyles().setFgColor(0xFA2C56);
        
        Button btnInscription=new Button("Inscrire votre enfant",Image.createImage("/boy.png").fill(50, 50));
        btnInscription.setTextPosition(Component.BOTTOM);
        btnInscription.setSize(c.getPreferredSize());
        Button btnListEnfant=new Button("Consulter la liste des enfants",Image.createImage("/classroom.png").fill(50, 50));
        btnListEnfant.setTextPosition(Component.BOTTOM);
        Button btnStat=new Button("Statistique",Image.createImage("/classroom.png").fill(50, 50));
        btnStat.setTextPosition(Component.BOTTOM);
               btnInscription.addActionListener(e -> new InscriptionForm(current).show());
               btnListEnfant.addActionListener(e -> new ListEnfantForm(current).show());
               btnStat.addActionListener(e -> new ChartForm(current).show());
        Button btnchart = new Button("Bar Chart",Image.createImage("/business.png").fill(50, 50));
                btnchart.setTextPosition(Component.BOTTOM);

        btnchart.addActionListener(e -> new ChartDemosForm(current).show());
        Button mail = new Button("Envoyer un e-mail",Image.createImage("/classroom.png"));
                mail.setTextPosition(Component.BOTTOM);

             mail.addActionListener(e -> new MessageForm(current).show());
 
        c1.add(s);
        c2.add(btnInscription);
        c3.add(btnListEnfant);
        c4.add(btnStat);
        c5.add(btnchart);
        c6.add(mail);

        c.addAll(c1,c2,c3,c4,c5,c6);
        add(c);

    }

}

