/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elite.club.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.elite.club.entities.Club;
import com.elite.club.entities.demandeadhesion;
import com.elite.club.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author benour
 */
public class Clubservice {

    public ArrayList<Club> clubs;
    public ArrayList<demandeadhesion> demandes;

    public static Clubservice instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
private Clubservice() {
         req = new ConnectionRequest();
    }

    public static Clubservice getInstance() {
        if (instance == null) {
            instance = new Clubservice();
        }
        return instance;
    }
    
    public ArrayList<Club> parseClubs(String jsonText) {
        try {
            clubs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> clubsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) clubsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Club t = new Club();
                float id = Float.parseFloat(obj.get("id").toString());
               t.setId((int) id);
                t.setNomclub(obj.get("nomclub").toString());
                t.setDescription(obj.get("description").toString());
                t.setHorraire(obj.get("horraire").toString());
                t.setTarif(((int) Float.parseFloat(obj.get("tarif").toString())));
                t.setImage(obj.get("image").toString());
                t.setCapacite(((int) Float.parseFloat(obj.get("capacite").toString())));

                clubs.add(t);
            }

        } catch (IOException ex) {

        }
        return clubs;
    }
 public ArrayList<Club> getAllClubs(){
        String url = Statics.BASE_URL+"/clubs/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                clubs = parseClubs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return clubs;
    }
     public boolean adddemande(demandeadhesion d) {
        String url = Statics.BASE_URL + "/demandedads/new?dureInsc="+d.getDureInsc()+"&modePayment="+d.getModePayment()+"&ancienClub="+d.getAncienClub()+"&objectif="+d.getObjectif();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     public ArrayList<demandeadhesion> parseDemandes(String jsonText) {
        try {
            demandes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> demandesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) demandesListJson.get("root");
            for (Map<String, Object> obj : list) {
                demandeadhesion t = new demandeadhesion();
                float id = Float.parseFloat(obj.get("id").toString());
               t.setId((int) id);
                t.setDureInsc(obj.get("dureInsc").toString());
                t.setModePayment(obj.get("modePayment").toString());
                t.setAncienClub(obj.get("ancienClub").toString());
               // t.setHorraire(obj.get("numParent").toString());
                t.setObjectif(obj.get("objectif").toString());
            //    t.setClubId(((int) Float.parseFloat(obj.get("clubId").toString())));
                t.setEtatDemande(((int) Float.parseFloat(obj.get("etatDemande").toString())));

                demandes.add(t);
            }

        } catch (IOException ex) {

        }
        return demandes;
    }
     public ArrayList<demandeadhesion> getAllDemandes(){
        String url = Statics.BASE_URL+"/demandedads/find/1";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                demandes = parseDemandes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return demandes;
    }
}
