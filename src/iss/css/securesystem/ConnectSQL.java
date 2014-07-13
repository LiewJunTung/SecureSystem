/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.css.securesystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Liew
 */
public class ConnectSQL {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pst = null;
                
        final String cs = "jdbc:mysql://127.0.0.1:3306/css";
        final String user = "root";
        final String password = "root";
        
        public String chatHistory(String firstplayer, String secondplayer){
        String returnvalue = "";
        String statement = "SELECT timestamp, text FROM Chatlog WHERE user_id1 = ? AND user_id2 = ?";
            
            try {            
                con = DriverManager.getConnection(cs, user, password);
//                System.out.println("Connected");

                pst = con.prepareStatement(statement);
                
                  pst.setString(1, firstplayer);
                  pst.setString(2, secondplayer);

                rs = pst.executeQuery();
 
                
                while(rs.next()){
                returnvalue = returnvalue + rs.getString("timestamp") + ":" + rs.getString("text") + "\n" ;
                }      
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }   
            return returnvalue;
        }       
        
        public void updateChatLog(String user1, String user2, String sender, String text){
            String longStatement = "INSERT INTO Chatlog(user_id1, user_id2, sender, text) VALUES (?, ?, ?, ?)";
            
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement(longStatement);
                
                pst.setString(1, user1);   
                pst.setString(2, user2);
                pst.setString(3, sender);
                pst.setString(4, text);
//                System.out.println(longStatement);
//                status = pst.executeUpdate();
                pst.executeUpdate();
            } catch (SQLException ex) {                
                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
        
        public void updateLog(String userlog, int onandoff){
            String action;   
            
            if(onandoff == 1) action = "Login";
            else action = "Logout";            
            String longStatement = "INSERT INTO log(log_user, log_action, log_datetime) VALUES (?, ?, (SELECT NOW()))";
            
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement(longStatement);
                
                pst.setString(1, userlog);   
                pst.setString(2, action);
//                System.out.println(longStatement);
//                status = pst.executeUpdate();
                pst.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Log problem");
//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }   
//            return status;            
        }
       
        public String getSignUpImage(int value1){
        String returnvalue = null;
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement("SELECT s_path FROM Secure_Image WHERE s_id = ?");
                
                  pst.setInt(1, value1);

                
                rs = pst.executeQuery();
 
                
                rs.next();
                returnvalue = rs.getString(1);
                            
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }          
            return returnvalue;
        }       
        
        public String getImage(String value1){
        String returnvalue = null;
        String statement = "SELECT Secure_Image.s_path FROM User "
                + "JOIN Secure_Image "
                + "ON User.us_id = Secure_Image.s_id "
                + "WHERE User.u_name = ?";
            try {            
                con = DriverManager.getConnection(cs, user, password);
//                System.out.println("Connected");

                pst = con.prepareStatement(statement);
                
                  pst.setString(1, value1);

                
                rs = pst.executeQuery();
 
                
                rs.next();
                returnvalue = rs.getString(1);
                            
            } catch (SQLException ex) {
//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }          
            return returnvalue;
        }
        
        public void updateAdmin(String email, String priv, String status){
            int privilege = 2;

            String longStatement = "UPDATE User SET upr_id = ? , u_status = ? WHERE u_email = ?";
            
            if(priv.equals("Admin"))
                    privilege = 1;
            else privilege = 2;
            
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement(longStatement);
                
                pst.setInt(1, privilege);   
                pst.setString(2, status);
                pst.setString(3, email);
//                System.out.println(longStatement);
                pst.executeUpdate();
//                pst.executeUpdate();
            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        }
        
        public String getUser(String value1){
        String returnvalue = null;
            try {            
                con = DriverManager.getConnection(cs, user, password);
//                System.out.println("Connected");

                pst = con.prepareStatement("SELECT u_name FROM User WHERE u_name = ?");
                
                  pst.setString(1, value1);                 
                
                rs = pst.executeQuery();
                
                rs.next();
 
                returnvalue = rs.getString(1);
                
            } catch (SQLException ex) {
                returnvalue = "User NOT FOUND";
//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }          
            return returnvalue;
        }
        
        
        public String getSecureWord(String value1){
        String returnvalue = null;
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement("SELECT u_secure FROM User WHERE u_name = ?");
                
                pst.setString(1, value1);                                 
                rs = pst.executeQuery();
                
                rs.next(); 
                returnvalue = rs.getString(1);
                
            } catch (SQLException ex) {
                returnvalue = "SecureWord NOT FOUND";
//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }          
            return returnvalue;
        }
        
        public String getEmail(String value1, int i){
        String returnvalue = null;
        if(i == 1){    
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement("SELECT u_email FROM User WHERE u_email = ?");
                
                pst.setString(1, value1);                                 
                rs = pst.executeQuery();
                
                rs.next(); 
                returnvalue = rs.getString(1);
                
            } catch (SQLException ex) {
                returnvalue = "Email Not Found";
//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
            
            finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
          }                     
        }else returnvalue = "NOT FOUND";
        return returnvalue;
    }
        
    public String getAdmin(String value, int value2){
        String returnvalue = null;
        String longstatement = null;
        if(value2 == 1){    
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement("SELECT u_email FROM User WHERE u_name = ?");
                
                pst.setString(1, value);                                 
                rs = pst.executeQuery();
                
                rs.next(); 
                returnvalue = rs.getString(1);
                
            } catch (SQLException ex) {
                returnvalue = "Email Not Found";
//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
            
            finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
          }                     
        }else if (value2 == 2){
                try {            
                con = DriverManager.getConnection(cs, user, password);
                longstatement = "SELECT Privilege.p_role FROM Privilege JOIN User ON "
                        + "Privilege.p_id = User.upr_id WHERE User.u_name = ?";
                pst = con.prepareStatement(longstatement);
                
                pst.setString(1, value);                                 
                rs = pst.executeQuery();
                
                rs.next(); 
                returnvalue = rs.getString(1);
                
            } catch (SQLException ex) {
                returnvalue = "User Not Found";
//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
            
            finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
          } 
        }else if (value2 == 3){
                try {            
                con = DriverManager.getConnection(cs, user, password);
                longstatement = "SELECT u_status FROM User WHERE u_name = ?";
                pst = con.prepareStatement(longstatement);
                
                pst.setString(1, value);                                 
                rs = pst.executeQuery();
                
                rs.next(); 
                returnvalue = rs.getString(1);
                
            } catch (SQLException ex) {
                returnvalue = "Email Not Found";
//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
            
            finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
          } 
        }else returnvalue = "NOT FOUND";
    return returnvalue;
    }
        
    
    public void updateOldPass(String email){
        
//            int status = 0;  
            
            String longStatement = "INSERT INTO OldPass VALUES ((SELECT u_id FROM User WHERE u_email = ?), "
                    + " NOW(), (SELECT p_hash from Password WHERE u_id = (SELECT u_id FROM User WHERE u_email = ?)))";
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement(longStatement);
                
                pst.setString(1, email);   
                pst.setString(2, email);
//                System.out.println(longStatement);
//                status = pst.executeUpdate();
                pst.executeUpdate();
            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }   
    }
    
        public void updatePass(String hashpass, String email){
//            int status = 0;  
            
            String longStatement = "UPDATE Password JOIN User ON Password.u_id "
                    + "= User.u_id SET Password.p_hash = ?, Password.p_date = NOW() WHERE User.u_email = ?";
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement(longStatement);
                
                pst.setString(1, hashpass);   
                pst.setString(2, email);
//                System.out.println(longStatement);
//                status = pst.executeUpdate();
                pst.executeUpdate();
            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }   
//            return status;
        }
        
        
        public void updateSettings(String value1, String value2, int i){
            
            if(i == 1){
            String longStatement = "UPDATE User SET u_email = ? WHERE u_name = ?";
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement(longStatement);
                
                pst.setString(1, value1);   
                pst.setString(2, value2);
//                System.out.println(longStatement);
//                status = pst.executeUpdate();
                pst.executeUpdate();
            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
           }   
        }else if(i == 2){
            String longStatement = "UPDATE Password JOIN User ON Password.u_id "
                    + "= User.u_id SET Password.p_hash = ? WHERE User.u_name = ?";
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement(longStatement);
                
                pst.setString(1, value1);   
                pst.setString(2, value2);
//                System.out.println(longStatement);
//                status = pst.executeUpdate();
                pst.executeUpdate();
            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
           }   
        }else if(i == 3){
            String longStatement = "UPDATE User SET u_fname = ? WHERE u_name = ?";
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement(longStatement);
                
                pst.setString(1, value1);   
                pst.setString(2, value2);
//                System.out.println(longStatement);
//                status = pst.executeUpdate();
                pst.executeUpdate();
            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
           }   
        }else if(i == 4){
            String longStatement = "UPDATE User SET u_lname = ? WHERE u_name = ?";
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement(longStatement);
                
                pst.setString(1, value1);   
                pst.setString(2, value2);
//                System.out.println(longStatement);
//                status = pst.executeUpdate();
                pst.executeUpdate();
            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
           }   
        }else if(i == 5){
            String longStatement = "INSERT INTO OldPass VALUES ((SELECT u_id FROM User WHERE u_name = ?), "
                    + "NOW(), (SELECT p_hash from Password WHERE p_hash = ? and u_id = (SELECT u_id FROM User WHERE u_name = ?)))";
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement(longStatement);
                
                pst.setString(1, value1);   
                pst.setString(2, value2);
                pst.setString(3, value1);
//                System.out.println(longStatement);
//                status = pst.executeUpdate();
                pst.executeUpdate();
            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        }
//            return status;
        }
        
        public int setLogin (String name, String pass){
            int flag = 0;
            String loguser = null;
            String logrole = null;
            String logpass =  null;
            String logstatus = null;
            String longStatement = "SELECT User.u_name, Privilege.p_role, Password.p_hash, User.u_status "
                    + "FROM User JOIN Privilege JOIN Password "
                    + "ON User.upr_id = Privilege.p_id AND User.u_id = Password.u_id "
                    + "WHERE User.upr_id = (SELECT upr_id FROM User "
                    + "WHERE u_name = ?) AND User.u_name = ? AND Password.p_hash = ?";
            try {            
                con = DriverManager.getConnection(cs, user, password);
                pst = con.prepareStatement(longStatement);
                
                pst.setString(1, name);   
                pst.setString(2, name);
                pst.setString(3, pass);
               
                rs = pst.executeQuery();
//                System.out.println(longStatement);
                rs.next();
                loguser = rs.getString(1);
                logrole = rs.getString(2);
                logpass = rs.getString(3);
                logstatus = rs.getString(4);
                
 
                if (loguser.equals(name) && logstatus.equals("Disable"))
                    flag = 3;
                else{
                    if (loguser.equals(name) && logpass.equals(pass) && logrole.equals("Admin"))
                        flag = 1;
                    else if(loguser.equals(name) && logpass.equals(pass) && logrole.equals("User"))
                        flag = 2;
                    else 
                        flag = 4;
                }
                    
                
            } catch (SQLException ex) {
                    flag = 0;
//                    System.out.println("Wrong Password");
//                    Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                    lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                flag = 0;
//                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return flag;
    }
        
        public int oldPassDate (String id, String pass){
            String longStatement = "SELECT DATEDIFF(op_date, NOW()) FROM OldPass WHERE "
                    + "u_id = (SELECT u_id FROM User WHERE u_name = ?) AND op_hash = ?";
            int value = 0;
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement(longStatement);
                
                pst.setString(1, id);
                pst.setString(2, pass);
//                System.out.println(longStatement);
                rs = pst.executeQuery();
                
                rs.next();
                value = rs.getInt(1);
              
            } catch (SQLException ex) {
                value = -9999;
//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
            return value;
        }
        
        public int oldPassEmailDate (String email, String pass){
            String longStatement = "SELECT DATEDIFF(op_date, NOW()) FROM OldPass WHERE "
                    + "u_id = (SELECT u_id FROM User WHERE u_email = ?) AND op_hash = ?";
            int value = 0;
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement(longStatement);
                
                pst.setString(1, email);
                pst.setString(2, pass);
//                System.out.println(longStatement);
                rs = pst.executeQuery();
                
                rs.next();
                value = rs.getInt(1);
              
            } catch (SQLException ex) {
                value = -9999;
                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
            return value;
        }
        
        public int passWorDate (String name){
            String longStatement = "SELECT DATEDIFF(NOW(), p_date) FROM "
                    + "css.Password WHERE u_id = (SELECT u_id FROM css.User WHERE u_name = ?)";
            int value = 0;
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement(longStatement);
                
                pst.setString(1, name);   
//                System.out.println(longStatement);
                rs = pst.executeQuery();
                
                rs.next();
                value = rs.getInt(1);
              
            } catch (SQLException ex) {
                
                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
            return value;
        }
        
        public boolean checkFirstUser(){
            boolean returnvalue = true;
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement("SELECT * FROM User");
 
//                System.out.println(longStatement);
                rs = pst.executeQuery();
                rs.next();
                if(rs.getString(1).equals("1")) returnvalue = true;
                else returnvalue = false;
                
            } catch (SQLException ex) {
                  returnvalue = false;
//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
            System.out.println(returnvalue);
            return returnvalue;
    }
        
        public void createUser (String name, String email, int priv, int simage, String sword){

            String longStatement = "INSERT INTO User(u_name, u_email, upr_id, us_id, u_status, u_secure) "
                    + "VALUES(?,?,?,?,'Enable', ?)";
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement(longStatement);
                
                pst.setString(1, name);   
                pst.setString(2, email);
                pst.setInt(3, priv);
                pst.setInt(4, simage);
                pst.setString(5, sword);
//                System.out.println(longStatement);
                pst.executeUpdate();
              
            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
        public void createPass (String email, String pass){

            String longStatement = "INSERT INTO Password(u_id, p_date, p_hash) "
                    + "VALUES((SELECT u_id FROM User WHERE u_email = ?),"
                    + "CURDATE(),?)";
            try {            
                con = DriverManager.getConnection(cs, user, password);

                pst = con.prepareStatement(longStatement);
                
                pst.setString(1, email);
                pst.setString(2, pass);
                
//                System.out.println(longStatement);
                pst.executeUpdate();
              
            } catch (SQLException ex) {

//                Logger lgr = Logger.getLogger(iss.css.securesystem.ConnectSQL.class.getName());
//                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(ConnectSQL.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }

    }
      
}
