/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enfant.gui;

import com.codename1.components.MultiButton;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.animations.FlipTransition;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;

/**
 *
 * @author elbaz
 */
public class ChartDemosForm extends Form {    
    private boolean drawOnMutableImages;
    private List formMenu;
    private Component currentChart;
    private Component currentEditor;
    private boolean showEditor;
    
    class ListOption {
        Class<AbstractDemoChart> chartClass;
        String name;
        
        ListOption(Class cls, String name){
            this.chartClass = cls;
            this.name = name;
        }
        
        public String toString(){
            return this.name;
        }
    }
    
    ListOption[] options = new ListOption[]{
       
        new ListOption(BarChartEnfant.class, "Statistique"),
       
    };

    protected Form wrap(String title, Component c){
        c.getStyle().setBgColor(0xff0000);
        Form f = new Form(title);
        f.setLayout(new BorderLayout());
        if (drawOnMutableImages) {
            int dispW = Display.getInstance().getDisplayWidth();
            int dispH = Display.getInstance().getDisplayHeight();
            Image img = Image.createImage((int)(dispW * 0.8), (int)(dispH * 0.8), 0x0);
            Graphics g = img.getGraphics();
            c.setWidth((int)(dispW * 0.8));
            c.setHeight((int)(dispH * 0.8));
            c.paint(g);
            f.addComponent(BorderLayout.CENTER, new Label(img));
        } else {
          f.addComponent(BorderLayout.CENTER, c);
        }
        return f;
    }
      
    
    private void showChart(ListOption opt) {
        Class cls = opt.chartClass;
        try {
            AbstractDemoChart demo = (AbstractDemoChart)cls.newInstance();
            demo.setDrawOnMutableImage(drawOnMutableImages);
            Form intent = wrap(demo.getChartTitle(), demo.execute());
            if ( "".equals(intent.getTitle())){
                intent.setTitle(demo.getName());
            }
            intent.getToolbar().setBackCommand("Menu", e -> ChartDemosForm.this.showBack());
            intent.getStyle().setBgColor(0x0);
            intent.getStyle().setBgTransparency(0xff);
            int numComponents = intent.getComponentCount();
            for (int i=0; i<numComponents; i++) {
                intent.getComponentAt(i).getStyle().setBgColor(0x0);
                intent.getComponentAt(i).getStyle().setBgTransparency(0xff);
            }
            intent.show();
        } catch (InstantiationException ex) {
            Log.e(ex);
        } catch (IllegalAccessException ex) {
            Log.e(ex);
        }        
    }

    private void showChartInTablet(ListOption opt) {
        Class cls = opt.chartClass;
        try {
            AbstractDemoChart demo = (AbstractDemoChart)cls.newInstance();
            demo.setDrawOnMutableImage(drawOnMutableImages);
            Component chart = demo.execute();
            
            // editor showing is currently disabled by default
            if(showEditor) {
                Component editor = demo.getChartModelEditor();

                if(editor == null) {
                    editor = new Label("Editing to this chart isn't supported at the moment...");
                }
                if(currentChart != null) {
                    replaceAndWait(currentChart, chart, new FlipTransition(-1, 200));
                    replaceAndWait(currentEditor, editor, CommonTransitions.createCover(CommonTransitions.SLIDE_VERTICAL, true, 200));
                } else {
                    add(chart);
                    add(editor);
                }
                currentEditor = editor;
            } else {
                if(currentChart != null) {
                    replaceAndWait(currentChart, chart, CommonTransitions.createCover(CommonTransitions.SLIDE_HORIZONTAL, true, 200));
                    //replace(currentChart, chart, null);
                } else {
                    add(chart);
                }
            }
            currentChart = chart;
        } catch (InstantiationException ex) {
            Log.e(ex);
        } catch (IllegalAccessException ex) {
            Log.e(ex);
        }        
    }
    
    private Command selectedCommand;
    public ChartDemosForm(Form previous){
        super("Bar Chart", BoxLayout.y());
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        FontImage chart = FontImage.createMaterial(FontImage.MATERIAL_INSERT_CHART, "Label", 4f);
        if(Display.getInstance().isTablet()) {
            setLayout(new GridLayout(1, 1));
            setScrollable(false);
            Toolbar tb = getToolbar();   
             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            for(ListOption o : options) {
                Command current = tb.addCommandToSideMenu(o.name, chart, e -> {
                    Button b = tb.findCommandComponent(selectedCommand);
                    b.setUIID("SideCommand");
                    Command cmd = e.getCommand();
                    selectedCommand = cmd;
                    showChartInTablet(o);
                    tb.findCommandComponent(cmd).setUIID("SelectedSideCommand");
                    b.getParent().repaint();
                });
                if(o == options[0]) {
                    selectedCommand = current;
                    /*Button cmp = tb.findCommandComponent(current);
                    Container sidemenu = cmp.getParent();
                    cmp.setUIID("SelectedSideCommand");
                    tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_MENU, e -> {
                        boolean b = sidemenu.isVisible();
                        sidemenu.setVisible(true);
                        sidemenu.setHidden(b);
                        Form f = sidemenu.getComponentForm();
                        f.animateLayoutAndWait(200);
                        //sidemenu.getParent().setShouldCalcPreferredSize(true);
                        //f.getContentPane().setShouldCalcPreferredSize(true);
                        //f.revalidate();
                        if(b) {
                            sidemenu.setVisible(false);
                        }
                    });*/
                }
            }
            showChartInTablet(options[0]);
        } else {
            for(ListOption o : options) {
                MultiButton btn = new MultiButton(o.name);
                btn.setIcon(chart);
                add(btn);
                btn.addActionListener(e -> showChart(o));
            }
        }

        getToolbar().addMaterialCommandToOverflowMenu("Render on Mutable Image", 
                FontImage.MATERIAL_PHOTO, 
                e -> drawOnMutableImages =  !drawOnMutableImages);        
    }
}

