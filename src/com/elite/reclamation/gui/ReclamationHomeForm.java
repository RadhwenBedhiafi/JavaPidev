/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elite.reclamation.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import java.io.IOException;

/**
 *
 * @author COMPUTER
 */
public class ReclamationHomeForm extends Form {

    Form current;

    public ReclamationHomeForm() throws IOException {
        current = this;

        current.getToolbar().getAllStyles().setBgImage(Image.createImage("/communication.png"));
        setTitle("Home Reclamation");
        setLayout(BoxLayout.y());
        this.getAllStyles().setBgColor(0x09224a);
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Label s = new Label("                     Choose an option");
        s.getAllStyles().setFgColor(0xFA2C56);
        c.add(s);
        Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Button btnAddReclamation = new Button("Envoyer Reclamation", Image.createImage("/communication.png"));
        btnAddReclamation.setTextPosition(Component.TOP);
        btnAddReclamation.getAllStyles().setBgColor(0xd3d8e0);
        //btnAddReclamation.getAllStyles().setBgImage(Image.createImage("/hey.png"));
        btnAddReclamation.getStyle().setBgTransparency(255);
        btnAddReclamation.getAllStyles().setMarginTop(400);
        btnAddReclamation.getAllStyles().setMarginLeft(20);

        Button btnListReclamations = new Button("Vos Reclamations", Image.createImage("/archive1.png"));
        btnListReclamations.setTextPosition(Component.TOP);
        btnListReclamations.getStyle().setBgTransparency(255);
        btnListReclamations.getAllStyles().setBgColor(0xd3d8e0);
        btnListReclamations.getAllStyles().setMarginLeft(20);
        
        Button btnchart = new Button("Bar Chart", Image.createImage("/bars.png"));
        btnchart.setTextPosition(Component.TOP);
        btnchart.getStyle().setBgTransparency(255);
        btnchart.getAllStyles().setBgColor(0xd3d8e0);
        btnchart.getAllStyles().setMarginLeft(20);

        c1.addAll(btnAddReclamation, btnListReclamations,btnchart);

        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        //c2.getAllStyles().setMargin(10, 10, 10, 10);
        Button btnListRendezvouss = new Button("Vos Rendezvous", Image.createImage("/interface.png"));
        //btnListRendezvouss.getAllStyles().setBgImage(Image.createImage("/hey.png"));
        btnListRendezvouss.setTextPosition(Component.TOP);
        btnListRendezvouss.getAllStyles().setBgColor(0xd3d8e0);
        btnListRendezvouss.getStyle().setBgTransparency(255);
        btnListRendezvouss.getAllStyles().setMarginTop(400);
        Media m = MediaManager.createMedia((Display.getInstance().getResourceAsStream(getClass(), "/music.mp3")), "audio/mpeg");
        Button PlayMusic = new Button("", Image.createImage("/arrows.png"));
        Button PauseMusic = new Button("", Image.createImage("/signs.png"));

        Button pie = new Button("Consulter pie", Image.createImage("/business1.png"));
        ///pie.getAllStyles().setBgImage(Image.createImage("/hey.png"));
        pie.setTextPosition(Component.TOP);
        pie.getAllStyles().setBgColor(0xd3d8e0);
        pie.getStyle().setBgTransparency(255);
        
        Button Mail = new Button("Envoyer Email", Image.createImage("/send.png"));
        Mail.setTextPosition(Component.TOP);
        Mail.getAllStyles().setBgColor(0xd3d8e0);
        Mail.getStyle().setBgTransparency(255);

        c2.addAll(btnListRendezvouss, pie,Mail);
        
        Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        btnAddReclamation.addActionListener(e -> {
            try {
                new AddReclamationForm(current).show();
            } catch (IOException ex) {

            }
        });
        btnchart.addActionListener(e -> new ChartDemosForm(current).show());
        Mail.addActionListener(e -> {
             Message mz = new Message("Body of message");
Display.getInstance().sendMessage(new String[] {"elitekids560@gmail.com"}, "Subject of message", mz);
        });
        btnListReclamations.addActionListener(e -> new ListReclamationsForm(current).show());
        btnListRendezvouss.addActionListener(e -> new ListRendezvousForm(current).show());
        pie.addActionListener(e -> new ChartForm(current).show());
        PlayMusic.addActionListener(e -> m.play());
        PauseMusic.addActionListener(e -> m.pause());
        c4.getAllStyles().setMarginLeft(700);
        c4.addAll(PlayMusic,PauseMusic);
        addAll(c,c4);
        c3.addAll(c1, c2);
        addAll(c3);
        

    }

}
