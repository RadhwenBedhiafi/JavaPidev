/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elite.reclamation.services;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.elite.reclamation.entities.Reclamation;
import com.elite.reclamation.entities.Rendezvous;
import static com.elite.reclamation.services.ServiceReclamation.instance;
import com.elite.reclamation.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author COMPUTER
 */
public class ServiceRendezvous {
    
     public ArrayList<Rendezvous> rendezvouss;

    public static ServiceRendezvous instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private ServiceRendezvous() {
        req = new ConnectionRequest();
    }

    public static ServiceRendezvous getInstance() {
        if (instance == null) {
            instance = new ServiceRendezvous();
        }
        return instance;
    }
    
        public ArrayList<Rendezvous> parseRendezvous(String jsonText) {
        try {
            rendezvouss = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> rendezvousListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) rendezvousListJson.get("root");
            for (Map<String, Object> obj : list) {
                Rendezvous t = new Rendezvous();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setLieu(obj.get("lieu").toString());
                t.setDate(obj.get("date").toString());
                t.setHeure((obj.get("heure").toString()));
                rendezvouss.add(t);
                

            }

        } catch (IOException ex) {

        }
        return rendezvouss;
    }



  public ArrayList<Rendezvous> getAllRendezvous() {
      // int a = com.elite.reclamation.MyApplication.class.
        String url = Statics.BASE_URL + "/rendezvous/find/adel";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                rendezvouss = parseRendezvous(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return rendezvouss;
    }
  
  
 
}
