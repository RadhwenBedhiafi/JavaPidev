/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enfant.gui;

import com.codename1.components.SpanLabel;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.enfant.entities.Enfant;
import com.mycompany.enfant.gui.entities.Classe;
import com.mycompany.enfant.service.ClasseService;
import com.mycompany.enfant.service.EnfantService;
import java.util.ArrayList;

/**
 *
 * @author elbaz
 */
public class ListClasseForm extends Form{
    public ListClasseForm(Form previous)
    {
         setTitle("Liste des classes disponibles!");
        
        
          
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");


        Display.getInstance().scheduleLocalNotification(n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
        ArrayList<Classe> ListClasses = ClasseService.getInstance().getListClasse();
        for (Classe el : ListClasses) {
            
             
            Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           // SpanLabel s = new SpanLabel("***Club***");
         
            SpanLabel sp = new SpanLabel("Bloc : " + el.getBloc());
            SpanLabel sp1 = new SpanLabel("Libelle: " + el.getLibelle());
            SpanLabel sp2 = new SpanLabel("Effectif: " + el.getEffectif());        
            SpanLabel s4 = new SpanLabel("-------------------------------------------------------------");
        
        c.addAll(sp,sp1,sp2,s4);
       
        add(c);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

   

   

    
}
}
