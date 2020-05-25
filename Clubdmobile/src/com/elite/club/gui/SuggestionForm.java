/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elite.club.gui;

import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

/**
 *
 * @author benour
 */
public class SuggestionForm extends Form{
    
     public SuggestionForm(Form previous) {
         Message mz = new Message("Body of message");
        Display.getInstance().sendMessage(new String[] {"benourtest@gmail.com"}, "Subject of message", mz);
         
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

         
     }
    
}
