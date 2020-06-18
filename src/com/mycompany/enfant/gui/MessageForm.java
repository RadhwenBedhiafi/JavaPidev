/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enfant.gui;

import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

/**
 *
 * @author elbaz
 */
public class MessageForm extends Form {
    public MessageForm(Form previous) {
         Message mes = new Message("Body of message");
        Display.getInstance().sendMessage(new String[] {"radhouan.bedhiafi@esprit.tn"}, "Subject of message", mes);
         
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

         
     }
}
