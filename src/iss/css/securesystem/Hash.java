/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package iss.css.securesystem;

import java.security.*;
 
public class Hash {
    private String hashtext;
    private String out;
    
    public String getHash(){
        return hashtext;        
    }
    
    
    public void setHash(String message) {
        hashtext = null;
        MessageDigest md;
        try {
            md= MessageDigest.getInstance("SHA-1");
            message = message + "SALT";
            md.update(message.getBytes());
            byte[] mb = md.digest();
            out = "";
            for (int i = 0; i < mb.length; i++) {
                byte temp = mb[i];
                String s = Integer.toHexString(new Byte(temp));
                while (s.length() < 2) {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                out += s;
            }
//              System.out.println(out.length());
//              System.out.println("CRYPTO: " + out);
                hashtext = out;
        } catch (NoSuchAlgorithmException e) {
//            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
