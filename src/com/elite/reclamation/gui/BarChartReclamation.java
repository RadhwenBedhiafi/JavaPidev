/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.elite.reclamation.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer.Orientation;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.BarChart.Type;
import com.codename1.ui.Form;
import java.util.ArrayList;
import java.util.List;

import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.BarChart;
import com.codename1.ui.Component;
import com.elite.reclamation.entities.Reclamation;
import com.elite.reclamation.services.ServiceReclamation;

/**
 * Sales demo bar chart.
 */
public class BarChartReclamation extends AbstractDemoChart {
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

    @Override
    public Component getChartModelEditor() {
        return null;
    }

    @Override
    public String getChartTitle() {
        return "";
    }

    @Override
    public Component execute() {
        String[] titles = new String[]{"Nombre de Reclamation"};
        List<double[]> values = new ArrayList<double[]>();
        
         ArrayList<Reclamation> ListRec = ServiceReclamation.getInstance().getAllReclamations();
 double x=0;
 double b=0;
        for (Reclamation p : ListRec) {
            
            if (p.getEtat()==1){
                x++;
              
            }
           else{
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
        renderer.setOrientation(Orientation.HORIZONTAL);
        setChartSettings(renderer, "Nombre de reclamation selon l'état", "L'état", "Nombre de Reclamation", 0.5,
                12.5, 0, 24, ColorUtil.GRAY, ColorUtil.LTGRAY);
        renderer.setXLabels(2);
        renderer.setYLabels(12);
        renderer.addXTextLabel(1, "Traitée");
        renderer.addXTextLabel(5, "Non Traitée");
        
        initRendererer(renderer);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
            seriesRenderer.setDisplayChartValues(true);
        }

        BarChart chart = new BarChart(buildBarDataset(titles, values), renderer,
                Type.STACKED);
        return newChart(chart);
                  
        

    }

}
