/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elite.reclamation.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.elite.reclamation.services.ServiceReclamation;
import com.elite.reclamation.services.ServiceRendezvous;

/**
 *
 * @author COMPUTER
 */
public class ChartForm extends Form {

    public ChartForm (Form previous) {
         setTitle("Statistique");
       add(ServiceReclamation.getInstance().createPieChartForm());
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }

    
}

