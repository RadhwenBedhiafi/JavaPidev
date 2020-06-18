/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Event;
import Entities.commentaire;
import Entities.Participation;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class Services {
    public  ArrayList<Event> getListsProduits(Map m){
        ArrayList<Event> listDisponibilite = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("event");
        
        //Map f =  (Map) d.get(0);
        

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Event p = new Event();
            Double ll = (Double) f.get("id");
            
           
            p.setId(ll.intValue());
            p.setSujet((String) f.get("sujet"));
             /* Map map1 = ((Map)f.get("date"));
            Date date1 = new Date((((Double)map1.get("timestamp")).longValue()*1000));  
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s1 = formatter.format(date1);
            p.setDate(s1);*/
            p.setDate((String) f.get("date"));
            p.setLieu((String) f.get("lieu"));
            p.setImage((String)f.get("image"));
            p.setDescription((String) f.get("description"));
            p.setTitre((String) f.get("titre"));
            
           
            
            listDisponibilite.add(p);  
        }        
        System.out.println(listDisponibilite);
        return listDisponibilite;
        
    }
    public  ArrayList<commentaire> getListsComments(Map m){
        ArrayList<commentaire> listDisponibilite = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("comment");
        
        //Map f =  (Map) d.get(0);
        

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            commentaire p = new commentaire();
            
            p.setCommentaire((String)f.get("commentaire"));
            
           
            listDisponibilite.add(p);  
        }        
        System.out.println(listDisponibilite);
        return listDisponibilite;
}
     public  ArrayList<Participation> getListsPart(Map m){
        ArrayList<Participation> listDisponibilite = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("part");
        
        //Map f =  (Map) d.get(0);
        

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Participation p = new Participation();
            
          p.setNom((String)f.get("nom"));
            
           
            listDisponibilite.add(p);  
        }        
        System.out.println(listDisponibilite);
        return listDisponibilite;
}
}
