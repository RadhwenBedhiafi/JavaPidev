/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Event;
import Utils.WebService;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Jasser
 */
public class EditEvent extends BaseForm{
    private String im ;
    public static Event ev ; 
    public EditEvent(){
       
        
        setName("Ajouter Evenement");
        setTitle("Ajouter Evenement");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
           EventDetails.e = ev ;
           EventDetails ed = new EventDetails();
           ed.show();
        });
        getToolbar().addCommandToRightSideMenu("Ajouter évenements", icon, (e) -> {
          AddEvent ae = new AddEvent();
           
        });
         getToolbar().addCommandToRightSideMenu("Liste des évenements", icon, (e) -> {
           
           ListEvent le = new ListEvent();
        });
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Picker datePicker = new Picker();
             DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                
               
           
            datePicker.setType(Display.PICKER_TYPE_DATE);
            
           
             
            TextField sujet = new TextField();
            sujet.setText(ev.getSujet());
            Label Date = new Label("Date : ");
            TextField lieu = new TextField();
            lieu.setText(ev.getLieu());
            Button img = new Button("Ajouter une image",icone);
            
            TextField description = new TextField();
            description.setText(ev.getDescription());
            TextField titre = new TextField();
            titre.setText(ev.getTitre());
            
            
          
            
           
            
          
            Button b = new Button("Ajouter");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            WebService ws = new WebService();
           photos.add(sujet);
           photos.add(Date);
           photos.add(datePicker);
           photos.add(lieu);
            photos.add(img);
           photos.add(description);
           photos.add(titre);
           
           
             
           
            
           
            
           
            photos.add(b);
            add(photos);
            img.addActionListener(e->{
                
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new com.codename1.l10n.SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want

                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    im =fileNameInServer ;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                }
            });
            b.addActionListener(e->{
                
                
                Date da = datePicker.getDate();
                
                String stt1 = df.format(da);
               Date dnnow = new Date(); 
                           
       try {
           dnnow = df.parse(stt1);
                    
       } catch (ParseException ex) {
           
       }                          
                 if(titre.getText().equals("") || description.getText().equals("") || lieu.getText().equals("") || im.equals("")){
                    Dialog.show("Erreur", "Vérifiez vos informations", "Ok", null);
                    
                }else{
                    ev.setSujet(sujet.getText());
                    ev.setDate(stt1);
                    ev.setLieu(lieu.getText());
                    if(im != null){
                    ev.setImage(im);
                    }
                    
                    ev.setDescription(description.getText());
                    
                    ev.setTitre(titre.getText());
                    
                    
                    ws.EditEvent(ev);
                    ListEvent le = new ListEvent();
                    le.show();
                    
               }
                });
            /**c.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    WebService ws = new WebService();
                    String status = ws.getStatus("check/"+6+"/"+e.getId());
                    if(status.equals("subscribed")){
                        MatiereVideos.ml = e ;
                        System.out.println(e.getId());
                        MatiereVideos m = new MatiereVideos();
                        m.f.show();
                    }else{
                        
                    }

                }
            });**/
           
      
        show();
    }
    
}
