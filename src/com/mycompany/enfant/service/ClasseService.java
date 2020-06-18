/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enfant.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.enfant.entities.Enfant;
import com.mycompany.enfant.gui.entities.Classe;
import com.mycompany.enfant.util.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
   public ArrayList<Classe> parseListClassesJson(String json) {

        ArrayList<Classe> listClasses = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> classes = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) classes.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Classe e = new Classe();

                float id = Float.parseFloat(obj.get("id").toString());
                float effectif = Float.parseFloat(obj.get("effectif").toString());
                

                e.setId((int) id);
                e.setBloc(obj.get("bloc").toString());
                 e.setLibelle(obj.get("libelle").toString());
                 
                 e.setEffectif((int) effectif);
                 

                 
//              

//                e.setDescription(obj.get("description").toString());
//                
//               
//                e.setNbrParticipant((int) nbrParticipant);
//              
                System.out.println(e);
                
                listClasses.add(e);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(listClasses);
        return listClasses;

    }
    
    
    ArrayList<Classe> listClasses = new ArrayList<>();
    public ArrayList<Classe> getListClasse(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/GesEnfantWebDev/web/app_dev.php/cla/list");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ClasseService ser= new ClasseService();
                listClasses = ser.parseListClassesJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listClasses;
    }
    //**********************************************************************//
    ArrayList<Classe> Classe = new ArrayList<>();
}
