/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;




import Entities.Event;
import Entities.Participation;
import Entities.commentaire;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.teknikindustries.bulksms.SMS;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.MyApplication;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Justpro
 */
public class WebService {
    static Map h;
    static String status ="";
    static int c ;
    static int v ;
    static String lg ;
    
    public static Map<String, Object> getResponse(String url){
        url = "http://127.0.0.1:8000/"+url;
        System.out.println("url---------------"+url);
        ConnectionRequest r = new ConnectionRequest();
        System.out.println("url ::::::: "+url);
        r.setUrl(url);
        r.setPost(false);
        System.out.println("url   :   "+r);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        r.setDisposeOnCompletion(dlg);
        r.addResponseListener((evt) -> {
            try {
                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                System.out.println(targetReader);
                h= p.parseJSON(targetReader);
                
            } catch (IOException ex) {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return h; 
    }
    public void addCommentaire(commentaire e){
        
        
        String url = "http://127.0.0.1:8000/AddC";
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("commentaire", e.getCommentaire());
     con.addArgument("iduser", MyApplication.id+"");
     con.addArgument("idevent", e.getInevent()+"");
    
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void addParticiper(Participation e){
        
        
        String url = "http://127.0.0.1:8000/AddP";
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
    con.addArgument("nom", e.getNom());
     con.addArgument("iduser", MyApplication.id+"");
     con.addArgument("idevent", e.getInevent()+"");
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                         if (Dialog.show("CONFIRMATION !!", "Voulez vous participer à l'évenement ", "OUI", "NON")) {
                       
                       /*
                        //API SMS
                        SMS sms = new SMS();
                        String nt = "+21652764555";
                        sms.SendSMS("28599636", "Houssem1995", "Nous confirmons votre participation à l'évenement" + e.getNom(), nt, "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
                       */
                     

                     }
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   
     
   
    public int count( int a){
        
        
        String url = "http://127.0.0.1:8000/countCo/"+a;
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        c = Integer.parseInt(s);
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
        return c ;
    }
     public int Count( int b){
        
        
        String url = "http://127.0.0.1:8000/countPart/"+b;
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        v = Integer.parseInt(s);
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
        return v ;
    }
    public void addEvent(Event e){
        
        
        String url = "http://127.0.0.1:8000/addE";
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     con.addArgument("sujet", e.getSujet()+"");
     con.addArgument("date", e.getDate()+"");
     con.addArgument("lieu", e.getLieu()+"");
     con.addArgument("image", e.getImage()+"");
     con.addArgument("description", e.getDescription()+"");
     con.addArgument("titre", e.getTitre()+"");
     
    
     
     
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        } else {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void EditEvent(Event e){
        
        
        String url = "http://127.0.0.1:8000/editE";
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("sujet", e.getSujet());
     con.addArgument("date", e.getDate()+"");
    con.addArgument("lieu", e.getLieu()+"");
    con.addArgument("image", e.getImage()+"");
     con.addArgument("description", e.getDescription()+"");
     
     con.addArgument("titre", e.getTitre()+"");
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void deleteEvent(int e){
        
        
        String url = "http://127.0.0.1:8000/deleteE/"+e;
        ConnectionRequest con = new ConnectionRequest();
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
  
}
