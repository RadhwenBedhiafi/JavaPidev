/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;


import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author elgho
 */
public class BaseForm extends Form {
    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
//        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
//                sl,
//                FlowLayout.encloseCenterBottom(
//                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
//        ));
        
        // Logout
       tb.addCommandToLeftSideMenu("Logout", res.getImage("back-command.png"), new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent evt) {
               //User.setTheUserToNullAfterLogout();
               //SignIn signIn = new SignIn(res);
               //signIn.show();
           }
       });
       
       // Feed
       tb.addCommandToLeftSideMenu("Feed", res.getImage("back-command.png"), new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent evt) {
               // put the redirection to the corresponding page here
               return ;
           }
       });
       
       // Host
       tb.addCommandToLeftSideMenu("Host Me", res.getImage("back-command.png"), new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent evt) {
               // put the redirection to the corresponding page here
               return ;
           }
       });
       
        // Event
       tb.addCommandToLeftSideMenu("Events", res.getImage("back-command.png"), new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent evt) {
               // put the redirection to the corresponding page here
               return ;
           }
       });
       
       // BonPlan
       tb.addCommandToLeftSideMenu("Bonplan", res.getImage("back-command.png"), new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent evt) {
               // put the redirection to the corresponding page here
               return ;
           }
       });
       
       // Topics
       tb.addCommandToLeftSideMenu("Topics", res.getImage("back-command.png"), new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent evt) {
               // put the redirection to the corresponding page here
               return ;
           }
       });
        
        
    }
}
