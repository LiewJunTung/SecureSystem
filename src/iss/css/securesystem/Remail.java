package iss.css.securesystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Remail {
 
    private String tac;
    private String email;
    private String filename;
    private String username;
    private String password; 
    public String getTAC(){
        return tac;
    }
    
    public String sendMail(String mail) {
        email = mail;
        tac = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(0, 8).toUpperCase();
        System.out.println(tac);
        filename = "/Email.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename)));
            try{
               username = br.readLine();
               password = br.readLine(); 
            }catch(IOException ex){
                System.out.println("No such file!");
            }  
	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
 
	Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
                @Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	});
 
	try {
 
	Message message = new MimeMessage(session);
	message.setFrom(new InternetAddress(username));
	message.setRecipients(Message.RecipientType.TO,
	InternetAddress.parse(email));
	message.setSubject("Your TAC Number - DO NOT REPLY");
	message.setText("Dear User,"
		+ "\n\n Here is your TAC number: " + tac);
 
        Transport.send(message);
 
	System.out.println("Email sent.");
        return tac;
 
	} catch (MessagingException e) {
            throw new RuntimeException(e);
	}
    }
}
