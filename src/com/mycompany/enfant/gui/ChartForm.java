/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enfant.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.enfant.service.EnfantService;

/**
 *
 * @author elbaz
 */
public class ChartForm extends Form {

    public ChartForm (Form previous) {
         setTitle("Statistique");
       add(EnfantService.getInstance().createPieChartForm());
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }

    
}
