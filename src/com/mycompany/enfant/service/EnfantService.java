/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enfant.service;

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
import com.mycompany.enfant.entities.Enfant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author elbaz
 */
public class EnfantService {

    public static EnfantService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    int idConnected = 3;
        public ArrayList<Enfant> enfants;



    public EnfantService() {
         req = new ConnectionRequest();
    }

public static EnfantService getInstance() {
        if (instance == null) {
            instance = new EnfantService();
        }
        return instance;
    }
/*public ArrayList<Enfant> parseEnfants(String jsonText){
        try {
            enfants=new ArrayList<>();
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
            /*Map<String,Object> enfantsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
          /*  List<Map<String,Object>> list = (List<Map<String,Object>>)enfantsListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Enfant t = new Enfant();
                float id = Float.parseFloat(obj.get("id").toString());
                float age = Float.parseFloat(obj.get("age").toString());

                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                 t.setPrenom(obj.get("prenom").toString());
                 t.setSexe(obj.get("sexe").toString());
                 t.setAge((int) age);
                 t.setNationalite(obj.get("nationalite").toString());
                 t.setSmedical(obj.get("smedical").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                enfants.add(t);
            }
            
            
        /*} catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
       /* return enfants;
    }*/

    public ArrayList<Enfant> parseListEnfantsJson(String json) {

        ArrayList<Enfant> listEnfants = new ArrayList<>();

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
            Map<String, Object> enfants = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) enfants.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Enfant e = new Enfant();

                float id = Float.parseFloat(obj.get("id").toString());
                float age = Float.parseFloat(obj.get("age").toString());
                

                e.setId((int) id);
                e.setNom(obj.get("nom").toString());
                 e.setPrenom(obj.get("prenom").toString());
                 e.setSexe(obj.get("sexe").toString());
                 e.setAge((int) age);
                 e.setNationalite(obj.get("nationalite").toString());
                 e.setSmedical(obj.get("smedical").toString());


                 
//              

//                e.setDescription(obj.get("description").toString());
//                
//               
//                e.setNbrParticipant((int) nbrParticipant);
//              
                System.out.println(e);
                
                listEnfants.add(e);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(listEnfants);
        return listEnfants;

    }
    
    
    ArrayList<Enfant> listEnfants = new ArrayList<>();
    public ArrayList<Enfant> parseEnfants(String jsonText) {
        try {
            enfants = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> enfantsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) enfantsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Enfant t = new Enfant();
                float id = Float.parseFloat(obj.get("id").toString());
                                float age = Float.parseFloat(obj.get("age").toString());

                t.setId((int) id);
               t.setNom(obj.get("nom").toString());
                 t.setPrenom(obj.get("prenom").toString());
                 t.setSexe(obj.get("sexe").toString());
                 t.setAge((int) age);
                 t.setNationalite(obj.get("nationalite").toString());
                 t.setSmedical(obj.get("smedical").toString());
                enfants.add(t);
            }

        } catch (IOException ex) {

        }
        return enfants;
    }
    public ArrayList<Enfant> getListEnfant(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/GesEnfantWebDev/web/app_dev.php/enfant/listenfant");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EnfantService ser= new EnfantService();
                listEnfants = ser.parseListEnfantsJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEnfants;
    }
    //**********************************************************************//
    ArrayList<Enfant> Enfant = new ArrayList<>();
    
    public ArrayList<Enfant> getenfant1(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SMF/PIDEV/web/app_dev.php/tasks/find/1");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EnfantService ser = new EnfantService();
                Enfant = ser.parseListEnfantsJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Enfant;
    }
    public ArrayList<Enfant> getAllEnfants() {
        String url = "http://localhost/GesEnfantWebDev/web/app_dev.php/enfant/findenfant/3";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            public void actionPerformed(NetworkEvent evt) {
                enfants = parseEnfants(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return enfants;
    }
     public boolean addTask(Enfant p) {
         String url = "http://localhost/GesEnfantWebDev/web/app_dev.php/enfant/addenfant?nom="+p.getNom()+"&prenom="+p.getPrenom()+"&sexe="+p.getSexe()+"&age="+p.getAge()+"&nationalite="+p.getNationalite()+"&smedical="+p.getSmedical();//création de l'URL
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
       private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(50);
    renderer.setLegendTextSize(50);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}
protected CategorySeries buildCategoryDataset(String title, int[] values) {
    CategorySeries series = new CategorySeries(title);
    int k = 0;
    
    for (int value : values) {
        series.add("" , value);
    }

    return series;
}

public Form createPieChartForm() {   
 ArrayList<Enfant> ListEnf = EnfantService.getInstance().getListEnfant();
 int x=0, b=0;
        for (Enfant p : ListEnf) {
            String a=p.getSexe();
            
            if (a.equals("Garçon")){
                x++;
              
            }
            else {
                b++;
               
            }
            
        }
        //System.out.println(x+' '+b);
         // Generate the values
     
     int values[] = new int[]{x,b};
  
    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.MAGENTA};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("", new BorderLayout());
    f.add(BorderLayout.CENTER, c);
    return f;

}
}