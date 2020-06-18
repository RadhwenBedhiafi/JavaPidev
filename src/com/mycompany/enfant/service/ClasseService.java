/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enfant.service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.enfant.gui.entities.Classe;
import com.mycompany.enfant.util.Statics;
import java.util.ArrayList;

/**
 *
 * @author elbaz
 */
public class ClasseService {
    public ArrayList<Classe> tasks;
    
    public static ClasseService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ClasseService() {
         req = new ConnectionRequest();
    }

    public static ClasseService getInstance() {
        if (instance == null) {
            instance = new ClasseService();
        }
        return instance;
    }
   public boolean addClasse(Classe c) {
        String url = Statics.BASE_URL + "/cla/addclasse" + c.getBloc() + "/" + c.getLibelle()+"/" + c.getEffectif(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
