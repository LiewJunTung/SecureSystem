/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package iss.css.securesystem;

/**
 *
 * @author Liew
 */

public class test {
    public static String chathistory1(String user1, String user2){
        ConnectSQL con = new ConnectSQL();
        return con.chatHistory(user1, user2);
    }
    
    public static ConnectSQL Connect(){
        ConnectSQL connect = new ConnectSQL();
        return connect;
    }
    
    public static String hashPass(String msg){
        Hash hash = new Hash();
        hash.setHash(msg);
        return hash.getHash();
    }
    
    public static int oldPassEmailDate(String email, String hash){
        return Connect().oldPassEmailDate(email, hash);
    }
    
    public static int oldPassDate(String id, String pass){
        return Connect().oldPassDate(id, pass);
    }
    
    public static void main(String args[]){
        System.out.println(oldPassEmailDate("pandawarrior91@gmail.com", hashPass("P@$$w0rd")));
        System.out.println(oldPassDate("jtliew", hashPass("P@$$w0rd")));
        
    }

}
