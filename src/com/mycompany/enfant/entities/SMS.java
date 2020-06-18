/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enfant.entities;

/**
 *
 * @author elbaz
 */
import com.codename1.ui.TextField;
import static com.codename1.ui.TextField.create;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
public class SMS {
    public static final String ACCOUNT_SID = "ACd87d6179ceb3bf9a6590a37a919e7e2a";
    public static final String AUTH_TOKEN = "1c0feba09e80ed2b5e8f16539e7844fb";
    public static void SMS_DON(String id1,String id2) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(new PhoneNumber("+21628237848"),//to
                new PhoneNumber("++12057073301"),//from 
                "UNE NOUVELLE INSCRIPTION EST EFFECTUE AFEC SUCCES SOUS LE NOM : "+id1+" ET LE PRENOM: "+id2+"SERVICE TECHNIQUE JARDIN D'ENFANT ELITE ").create();
    }
}