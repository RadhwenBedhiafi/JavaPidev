/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elite.club.gui;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author bhk
 */
public class HomeClubForm extends Form {

    Form current;

    public HomeClubForm() throws IOException {
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Media music = MediaManager.createMedia((Display.getInstance().getResourceAsStream(getClass(), "/music.mp3")), "audio/mpeg");
        Button Play = new Button("", Image.createImage("/arrows.png"));
        Button Pause = new Button("", Image.createImage("/signs.png"));
        c.getAllStyles().setMarginLeft(400);
        c.addAll(Play,Pause);
        add(new Label("Choose an option"));
        Button btnListclubs;
        try {
            
            btnListclubs = new Button("List club", Image.createImage("/c.png"));
            Button btnListdemandes = new Button("List Demandes", Image.createImage("/d.png"));
            btnListclubs.addActionListener(e -> new ListClubsForm(current).show());
            btnListdemandes.addActionListener(e -> new ListDemandeForm(current).show());
            Button suggestion = new Button("Suggestions", Image.createImage("/t.png"));
             suggestion.addActionListener(e -> new SuggestionForm(current).show());
            Play.addActionListener(e -> {music.play();});
            Pause.addActionListener(e -> {music.pause();});
            
            addAll(c,btnListclubs, btnListdemandes, suggestion);
        } catch (IOException ex) {
        }

    }

}
