/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enfant.gui;

import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.BarChart;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.mycompany.enfant.entities.Enfant;
import com.mycompany.enfant.service.EnfantService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elbaz
 */
public class BarChartEnfant extends AbstractDemoChart {
    Form current;
    /**
     * Returns the chart name.
     *
     * @return the chart name
     */
    public String getName() {
        return "Sales horizontal bar chart";
    }

    /**
     * Returns the chart description.
     *
     * @return the chart description
     */
    public String getDesc() {
        return "The monthly sales for the last 2 years (horizontal bar chart)";
    }

    public Component getChartModelEditor() {
        return null;
    }

    public String getChartTitle() {
        return "";
    }

    public Component execute() {
        String[] titles = new String[]{"Effectif de notre jardin d'enfant"};
        List<double[]> values = new ArrayList<double[]>();
        
         ArrayList<Enfant> ListEnf = EnfantService.getInstance().getListEnfant();
 double x=0;
 double b=0;
        for (Enfant e : ListEnf) {
            
             String a=e.getSexe();
            
            if (a.equals("Garçon")){
                x++;
              
            }
            else {
                b++;
               
            }
            
            
        }
        System.out.println(x+" et "+b);
        values.add(new double[]{x,0,0,0,b});
         values.add(new double[]{0,0,0});
      //  values.add(new double[]{5230, 7300, 9240, 10540, 7900, 9200, 12030, 11200, 9500, 10500,
          //  11600, 13500});
      //  values.add(new double[]{14230, 12300, 14240, 15244, 15900, 19200, 22030, 21200, 19500, 15500,
          //  12600, 14000});
        int[] colors = new int[]{ColorUtil.CYAN};
        XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
        renderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        setChartSettings(renderer, "Notre effectif selon le sexe", "Le sexe", "Nombre des enfants", 0.5,
                12.5, 0, 24, ColorUtil.GRAY, ColorUtil.LTGRAY);
        renderer.setXLabels(2);
        renderer.setYLabels(12);
        renderer.addXTextLabel(1, "Garçon");
        renderer.addXTextLabel(5, "Fille");
        
        initRendererer(renderer);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
            seriesRenderer.setDisplayChartValues(true);
        }

        BarChart chart = new BarChart(buildBarDataset(titles, values), renderer,
                BarChart.Type.STACKED);
        return newChart(chart);
                  
        

    }

}
