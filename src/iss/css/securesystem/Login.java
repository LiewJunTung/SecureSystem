package iss.css.securesystem;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Liew
 */
public class Login extends JFrame {
    
    private String tac;
    private String session;
    private String sessionEmail;
    private String sender = null;
    private int clientserver;
    private int chatonoff = 0;
    private boolean secure;
    private Boolean [] signUpflag = new Boolean[6];
    private boolean changeEmailflag = false;
    private Boolean [] changePassflag = new Boolean [2];    
    private int st = 0;
    private int encmethod = 0;
    private int loginLimit = 0;
    private int sImage = 1;
    private int loginFlag = 0;
    private int tacFlag = 0;
    private int tacFlag2 = 0;
    private int loginDatePass = 0;
    private sendThread sendS = null;
    private sendThread sendC = null;
    private recvThread recvS = null;
    private recvThread recvC = null;
    private boolean chatswitch = true;
    final private String passReq = "<html>Password must contain at least 6"
                + "<br>characters, an uppercase letter, a lowercase letter, "
                + "<br>digit and a special character</html>";
    
    public static ConnectSQL Connect(){
        ConnectSQL connect = new ConnectSQL();
        return connect;
    }
    
    public static String hashPass(String msg){
        Hash hash = new Hash();
        hash.setHash(msg);
        return hash.getHash();
    }
    
    public static CaesarCipher Caesar(){
        CaesarCipher cipher = new CaesarCipher();
        return cipher;
    }
    
    public static String encCaesar(String msg, int key){
        return Caesar().encode(msg, key);
    }
    
    public static String decCaesar(String msg, int key){
        return Caesar().decode(msg, key);
    }
    
    
    
    public static String sendEmail(String email){
        Remail mail = new Remail();
        return mail.sendMail(email);        
    }
    
    public static String getSImage(int value){        
        return Connect().getSignUpImage(value);
    } 
       
    public static String valAdmin(String value, int value2){
        return Connect().getAdmin(value, value2);
    }
    public static String valEmail(String value, int value2){
        return Connect().getEmail(value, value2);
    }
   
    public static String valUser(String value){
        return Connect().getUser(value);
}
    public static String valSecureImage(String value){
        return Connect().getImage(value);
    }
    
    public static String valSecureWord(String value){
        return Connect().getSecureWord(value);
    }  

    public static boolean valSecurePass(String value){
        PassValidation pass = new PassValidation();   
        return pass.printPasswordStats(value);
    }
    
    public static int valPasswordDate(String name){
        return Connect().passWorDate(name);
    }
    
    public int oldPassDate(String id, String pass){
        return Connect().oldPassDate(id, pass);
    }
    
    public int oldPassEmailDate(String email, String pass){
        return Connect().oldPassEmailDate(email, pass);
    }
    
    public void changeNewPass(String hashvalue, String email){        
        Connect().updatePass(hashvalue, email);
    }
    
    public void upOldPass(String email){
        Connect().updateOldPass(email);
    }
    
    public void changeAdmin(String value1, String value2, String value3){
        Connect().updateAdmin(value1, value2, value3);
    }
    
    public void changeSettings(String value1, String value2, int value3){
        Connect().updateSettings(value1, value2, value3);
    }   
    
    public void newUser(String name, String email, int priv, int simage, String sword){
         Connect().createUser(name, email, priv, simage, sword);
    }
    
    public boolean checkFirstUser(){
        return Connect().checkFirstUser();
    }
    
    public void newPass(String email, String pass){
        Connect().createPass(email, hashPass(pass));;
    }
    
    public int newLogin(String name, String pass){
        return Connect().setLogin(name, hashPass(pass));
    }
    
    public void log(String name, int action){
        Connect().updateLog(name, action);
    }
    
    public void chatlog(String user1, String user2, String sender, String text){
        Connect().updateChatLog(user1, user2, sender, text);
    }
    
    public String chathistory(String user1, String user2){
        return Connect().chatHistory(user1, user2);
    }
    
    public Login() {
        initComponents();
        init();
    }
    
    private void init(){
        setTitle("Secure System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        jLogin2.setVisible(false);
        jRPass1.setVisible(false);
        jRPass2.setVisible(false);
        jCTextArea1.setEditable(false);
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                if(session != null){
                    log(session, 2);
                    System.exit(0);                   
                }else {
                    System.out.println("Exiting");
                    System.exit(0);
                }
            }
        });
        jSignup2.setVisible(false);        
        jRPok.setVisible(false);
        jRPLabel3.setVisible(false);
        jRPTACField.setVisible(false);
        Arrays.fill(signUpflag, Boolean.FALSE);
        Arrays.fill(changePassflag, Boolean.FALSE);
        jS2Lion.setSelected(true);
        jCRadioButton1.setSelected(true);
        jCRadioButton3.setSelected(true);      
        jUserInterface.setVisible(false);
        jS2SecureImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iss/css/images/lion.jpg")));
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLogin1 = new javax.swing.JPanel();
        jL1Next = new javax.swing.JButton();
        jL1UserField = new javax.swing.JTextField();
        jL1Title = new javax.swing.JLabel();
        jL1Text = new javax.swing.JLabel();
        jL1Signup = new javax.swing.JButton();
        jL1Warning = new javax.swing.JLabel();
        jL1FPass = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel4 = new javax.swing.JPanel();
        jLogin2 = new javax.swing.JPanel();
        jL2Login = new javax.swing.JButton();
        jL2Title = new javax.swing.JLabel();
        jL2Text1 = new javax.swing.JLabel();
        jL2Image = new javax.swing.JLabel();
        jL2Text2 = new javax.swing.JLabel();
        jL2PassField = new javax.swing.JPasswordField();
        jL2Text3 = new javax.swing.JLabel();
        jL2FPass = new javax.swing.JButton();
        jL2Back = new javax.swing.JButton();
        jL2Label = new javax.swing.JLabel();
        jL2Label2 = new javax.swing.JLabel();
        jL2Label3 = new javax.swing.JLabel();
        jL2Warning = new javax.swing.JLabel();
        jRPass1 = new javax.swing.JPanel();
        jRPLabel1 = new javax.swing.JLabel();
        jRPLabel2 = new javax.swing.JLabel();
        jRPok = new javax.swing.JButton();
        jRPEmailField = new javax.swing.JTextField();
        jRPLabel3 = new javax.swing.JLabel();
        jRPTACField = new javax.swing.JTextField();
        jRPRequest = new javax.swing.JButton();
        jRPBack = new javax.swing.JButton();
        jRPLabel4 = new javax.swing.JLabel();
        jRPass2 = new javax.swing.JPanel();
        jRP2Label1 = new javax.swing.JLabel();
        jRP2Label2 = new javax.swing.JLabel();
        jRP2Label3 = new javax.swing.JLabel();
        jRP2Reset = new javax.swing.JButton();
        jRP2Back = new javax.swing.JToggleButton();
        jRP2Label4 = new javax.swing.JLabel();
        jRP2NewPassField = new javax.swing.JPasswordField();
        jRP2ConfirmPassField = new javax.swing.JPasswordField();
        jRP2Label5 = new javax.swing.JLabel();
        jSignup2 = new javax.swing.JPanel();
        jS2Label1 = new javax.swing.JLabel();
        jS2Label2 = new javax.swing.JLabel();
        jS2Label3 = new javax.swing.JLabel();
        jS2Label4 = new javax.swing.JLabel();
        jS2Label5 = new javax.swing.JLabel();
        jS2Label6 = new javax.swing.JLabel();
        jS2OK = new javax.swing.JButton();
        jS2Back = new javax.swing.JButton();
        jS2UserField = new javax.swing.JTextField();
        jS2EmailField = new javax.swing.JTextField();
        jS2SecureImage = new javax.swing.JLabel();
        jS2Label7 = new javax.swing.JLabel();
        jS2SecureField = new javax.swing.JTextField();
        jS2PassField1 = new javax.swing.JPasswordField();
        jS2PassField2 = new javax.swing.JPasswordField();
        jS2Label8 = new javax.swing.JLabel();
        jS2TACField = new javax.swing.JTextField();
        jS2Status = new javax.swing.JLabel();
        jS2Lion = new javax.swing.JRadioButton();
        jS2Apple = new javax.swing.JRadioButton();
        jS2Request = new javax.swing.JButton();
        jS2PassVal = new javax.swing.JLabel();
        jS2Val = new javax.swing.JLabel();
        jS2PassVal2 = new javax.swing.JLabel();
        jS2EmailVal = new javax.swing.JLabel();
        jS2Secure = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jUserInterface = new javax.swing.JTabbedPane();
        jAdmin = new javax.swing.JPanel();
        jA1Label1 = new javax.swing.JLabel();
        jA1Label2 = new javax.swing.JLabel();
        jA1Update = new javax.swing.JButton();
        jA1UserField = new javax.swing.JTextField();
        jA1OK = new javax.swing.JButton();
        jA1Label3 = new javax.swing.JLabel();
        jA1Combo1 = new javax.swing.JComboBox();
        jA1Label4 = new javax.swing.JLabel();
        jA1Combo2 = new javax.swing.JComboBox();
        jA1Welcome = new javax.swing.JLabel();
        jA1EmailField = new javax.swing.JLabel();
        jA1Label5 = new javax.swing.JLabel();
        jUIChat = new javax.swing.JPanel();
        jCTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jCTextArea1 = new javax.swing.JTextArea();
        jCButton1 = new javax.swing.JButton();
        jCRadioButton1 = new javax.swing.JRadioButton();
        jCRadioButton2 = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCRadioButton3 = new javax.swing.JRadioButton();
        jCRadioButton4 = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jCTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jCTextArea3 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jCTextArea4 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jUISetting = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jUILabel1 = new javax.swing.JLabel();
        jUILabel2 = new javax.swing.JLabel();
        jUIEmailField = new javax.swing.JTextField();
        jUIEmailField2 = new javax.swing.JTextField();
        jUIButton = new javax.swing.JButton();
        JUILabel3 = new javax.swing.JLabel();
        jUILabel4 = new javax.swing.JLabel();
        jUILabel5 = new javax.swing.JLabel();
        jUILabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jUI1Label1 = new javax.swing.JLabel();
        jUI1Label2 = new javax.swing.JLabel();
        jUI1Label3 = new javax.swing.JLabel();
        jUI1PassField1 = new javax.swing.JPasswordField();
        jUI1PassField2 = new javax.swing.JPasswordField();
        jUI1PassField3 = new javax.swing.JPasswordField();
        jUI1Button2 = new javax.swing.JButton();
        jUI1Label6 = new javax.swing.JLabel();
        jUI1Label7 = new javax.swing.JLabel();
        jUI1Label8 = new javax.swing.JLabel();
        jUI1Label9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jUI2Label1 = new javax.swing.JLabel();
        jUI2Label2 = new javax.swing.JLabel();
        jUI2TextField1 = new javax.swing.JTextField();
        jUI2TextField2 = new javax.swing.JTextField();
        jUI2Button1 = new javax.swing.JButton();
        jUI2Label3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLogin1.setOpaque(false);

        jL1Next.setText("Next");
        jL1Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jL1NextActionPerformed(evt);
            }
        });

        jL1UserField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jL1UserFieldKeyPressed(evt);
            }
        });

        jL1Title.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 24)); // NOI18N
        jL1Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL1Title.setText("Secure Login System");

        jL1Text.setText("Username:");

        jL1Signup.setText("Signup");
        jL1Signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jL1SignupActionPerformed(evt);
            }
        });

        jL1Warning.setForeground(java.awt.Color.red);

        jL1FPass.setText("Forgot Password");
        jL1FPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jL1FPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 299, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 174, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLogin1Layout = new javax.swing.GroupLayout(jLogin1);
        jLogin1.setLayout(jLogin1Layout);
        jLogin1Layout.setHorizontalGroup(
            jLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLogin1Layout.createSequentialGroup()
                .addGroup(jLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLogin1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jL1Title, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLogin1Layout.createSequentialGroup()
                                .addComponent(jL1Text)
                                .addGap(18, 18, 18)
                                .addGroup(jLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jL1UserField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(jL1FPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jL1Warning))
                                .addGap(32, 32, 32)
                                .addGroup(jLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jL1Signup)
                                    .addComponent(jL1Next)))))
                    .addGroup(jLogin1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jLogin1Layout.setVerticalGroup(
            jLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLogin1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jL1Title, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(jL1Warning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jL1UserField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jL1Next))
                    .addComponent(jL1Text))
                .addGap(22, 22, 22)
                .addGroup(jLogin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL1FPass)
                    .addComponent(jL1Signup))
                .addGap(44, 44, 44)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        jL2Login.setText("Login");
        jL2Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jL2LoginActionPerformed(evt);
            }
        });

        jL2Title.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 24)); // NOI18N
        jL2Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL2Title.setText("Secure Login System");

        jL2Text1.setText("Username:");

        jL2Text2.setText("Password:");

        jL2PassField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jL2PassFieldKeyPressed(evt);
            }
        });

        jL2Text3.setText("SecureWord: ");

        jL2FPass.setText("Forget Password");
        jL2FPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jL2FPassActionPerformed(evt);
            }
        });

        jL2Back.setText("Back");
        jL2Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jL2BackActionPerformed(evt);
            }
        });

        jL2Label2.setForeground(java.awt.Color.blue);

        jL2Label3.setForeground(java.awt.Color.red);

        jL2Warning.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout jLogin2Layout = new javax.swing.GroupLayout(jLogin2);
        jLogin2.setLayout(jLogin2Layout);
        jLogin2Layout.setHorizontalGroup(
            jLogin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLogin2Layout.createSequentialGroup()
                .addGroup(jLogin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLogin2Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jL2Title, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLogin2Layout.createSequentialGroup()
                        .addGroup(jLogin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLogin2Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(jLogin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jL2Text1)
                                    .addComponent(jL2Text2))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLogin2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jL2Back)
                                .addGap(10, 10, 10)))
                        .addGroup(jLogin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jL2PassField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLogin2Layout.createSequentialGroup()
                                .addComponent(jL2FPass)
                                .addGap(18, 18, 18)
                                .addComponent(jL2Login, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jL2Label)
                            .addGroup(jLogin2Layout.createSequentialGroup()
                                .addComponent(jL2Text3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jL2Label2))
                            .addComponent(jL2Label3)
                            .addComponent(jL2Image, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jLogin2Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(jL2Warning)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jLogin2Layout.setVerticalGroup(
            jLogin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLogin2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jL2Title, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLogin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL2Text1)
                    .addComponent(jL2Label))
                .addGap(18, 18, 18)
                .addComponent(jL2Image, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jLogin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jL2Text3)
                    .addComponent(jL2Label2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jL2Label3)
                .addGap(2, 2, 2)
                .addGroup(jLogin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL2PassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jL2Text2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLogin2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL2Back)
                    .addComponent(jL2FPass)
                    .addComponent(jL2Login))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jL2Warning)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        jRPLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jRPLabel1.setText("Reset Password");

        jRPLabel2.setText("Email Address: ");

        jRPok.setText("OK");
        jRPok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRPokActionPerformed(evt);
            }
        });

        jRPLabel3.setText("TAC:");

        jRPRequest.setText("Request");
        jRPRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRPRequestActionPerformed(evt);
            }
        });

        jRPBack.setText("Back");
        jRPBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRPBackActionPerformed(evt);
            }
        });

        jRPLabel4.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout jRPass1Layout = new javax.swing.GroupLayout(jRPass1);
        jRPass1.setLayout(jRPass1Layout);
        jRPass1Layout.setHorizontalGroup(
            jRPass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jRPass1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jRPLabel4)
                .addGap(294, 294, 294))
            .addGroup(jRPass1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jRPass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRPLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jRPass1Layout.createSequentialGroup()
                        .addGroup(jRPass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jRPLabel2)
                            .addComponent(jRPLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jRPass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRPTACField, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jRPass1Layout.createSequentialGroup()
                                .addComponent(jRPEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRPRequest))
                            .addGroup(jRPass1Layout.createSequentialGroup()
                                .addComponent(jRPBack, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRPok, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jRPass1Layout.setVerticalGroup(
            jRPass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jRPass1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jRPLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jRPass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRPLabel2)
                    .addComponent(jRPEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRPRequest))
                .addGap(24, 24, 24)
                .addGroup(jRPass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRPTACField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRPLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jRPass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRPBack)
                    .addComponent(jRPok))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(jRPLabel4)
                .addGap(186, 186, 186))
        );

        jRP2Label1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jRP2Label1.setText("Reset Password");

        jRP2Label2.setText("Confirm Password: ");

        jRP2Label3.setText("New Password: ");

        jRP2Reset.setText("Reset Password");
        jRP2Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRP2ResetActionPerformed(evt);
            }
        });

        jRP2Back.setText("Back");
        jRP2Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRP2BackActionPerformed(evt);
            }
        });

        jRP2Label4.setForeground(java.awt.Color.red);

        jRP2NewPassField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jRP2NewPassFieldFocusLost(evt);
            }
        });

        jRP2ConfirmPassField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jRP2ConfirmPassFieldFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jRPass2Layout = new javax.swing.GroupLayout(jRPass2);
        jRPass2.setLayout(jRPass2Layout);
        jRPass2Layout.setHorizontalGroup(
            jRPass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jRPass2Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jRP2Label1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jRPass2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jRPass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRP2Label2)
                    .addComponent(jRP2Label3))
                .addGap(18, 18, 18)
                .addGroup(jRPass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jRP2Label4)
                    .addGroup(jRPass2Layout.createSequentialGroup()
                        .addComponent(jRP2Back, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRP2Reset))
                    .addComponent(jRP2NewPassField)
                    .addComponent(jRP2ConfirmPassField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRP2Label5)
                .addGap(80, 80, 80))
        );
        jRPass2Layout.setVerticalGroup(
            jRPass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jRPass2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jRP2Label1)
                .addGap(14, 14, 14)
                .addComponent(jRP2Label4)
                .addGap(62, 62, 62)
                .addGroup(jRPass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRP2Label3)
                    .addComponent(jRP2NewPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jRPass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRP2Label2)
                    .addComponent(jRP2ConfirmPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRP2Label5))
                .addGap(18, 18, 18)
                .addGroup(jRPass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRP2Back)
                    .addComponent(jRP2Reset))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jS2Label1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jS2Label1.setText("SignUp");

        jS2Label2.setText("Username: ");

        jS2Label3.setText("Password:");

        jS2Label4.setText("Confirm Password:");

        jS2Label5.setText("Email: ");

        jS2Label6.setText("SecureImage:");

        jS2OK.setText("OK");
        jS2OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jS2OKActionPerformed(evt);
            }
        });

        jS2Back.setText("Back");
        jS2Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jS2BackActionPerformed(evt);
            }
        });

        jS2UserField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jS2UserFieldFocusLost(evt);
            }
        });

        jS2EmailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jS2EmailFieldFocusLost(evt);
            }
        });

        jS2Label7.setText("SecureWord:");

        jS2SecureField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jS2SecureFieldFocusLost(evt);
            }
        });

        jS2PassField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jS2PassField1MouseEntered(evt);
            }
        });
        jS2PassField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jS2PassField1FocusLost(evt);
            }
        });

        jS2PassField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jS2PassField2FocusLost(evt);
            }
        });

        jS2Label8.setText("TAC:");

        buttonGroup1.add(jS2Lion);
        jS2Lion.setText("Image 1");
        jS2Lion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jS2LionActionPerformed(evt);
            }
        });

        buttonGroup1.add(jS2Apple);
        jS2Apple.setText("Image 2");
        jS2Apple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jS2AppleActionPerformed(evt);
            }
        });

        jS2Request.setText("Request");
        jS2Request.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jS2RequestActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Image 3");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jSignup2Layout = new javax.swing.GroupLayout(jSignup2);
        jSignup2.setLayout(jSignup2Layout);
        jSignup2Layout.setHorizontalGroup(
            jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSignup2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jS2Label1)
                .addGap(173, 173, 173))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSignup2Layout.createSequentialGroup()
                .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jSignup2Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jS2Label5, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jS2Label6, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(jSignup2Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jS2Label8)))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSignup2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jS2Label4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jS2Label3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jS2Label2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jS2Label7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jS2Back, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jSignup2Layout.createSequentialGroup()
                        .addComponent(jS2SecureImage)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jS2UserField)
                    .addComponent(jS2EmailField)
                    .addComponent(jS2SecureField)
                    .addComponent(jS2PassField1)
                    .addComponent(jS2PassField2)
                    .addComponent(jS2TACField)
                    .addGroup(jSignup2Layout.createSequentialGroup()
                        .addComponent(jS2Status, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jS2OK))
                    .addComponent(jS2PassVal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jS2Apple)
                    .addComponent(jS2Request)
                    .addComponent(jS2PassVal2)
                    .addComponent(jS2Val)
                    .addComponent(jS2EmailVal)
                    .addComponent(jS2Secure)
                    .addComponent(jS2Lion))
                .addGap(26, 26, 26))
        );
        jSignup2Layout.setVerticalGroup(
            jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSignup2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jS2Label1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jSignup2Layout.createSequentialGroup()
                        .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jS2UserField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jS2Val))
                        .addGap(2, 2, 2)
                        .addComponent(jS2PassVal)
                        .addGap(2, 2, 2)
                        .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jSignup2Layout.createSequentialGroup()
                                .addComponent(jS2PassField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jSignup2Layout.createSequentialGroup()
                                        .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jS2PassField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jS2PassVal2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jSignup2Layout.createSequentialGroup()
                                                .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jS2Label5)
                                                    .addComponent(jS2EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jS2EmailVal))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jS2SecureImage)
                                                    .addComponent(jS2Label6)))
                                            .addGroup(jSignup2Layout.createSequentialGroup()
                                                .addComponent(jS2Lion)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jS2Apple))))
                                    .addComponent(jS2Label4)))
                            .addComponent(jS2Label3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jS2Secure)
                            .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jS2SecureField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jS2Label7)))
                        .addGap(20, 20, 20)
                        .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jS2TACField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jS2Label8)
                            .addComponent(jS2Request)))
                    .addGroup(jSignup2Layout.createSequentialGroup()
                        .addComponent(jS2Label2)
                        .addGap(273, 273, 273)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jS2Status)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jSignup2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jS2OK)
                    .addComponent(jS2Back))
                .addGap(31, 31, 31))
        );

        jA1Label1.setText("Access: ");

        jA1Label2.setText("Email: ");

        jA1Update.setText("Update");
        jA1Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jA1UpdateActionPerformed(evt);
            }
        });

        jA1OK.setText("OK");
        jA1OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jA1OKActionPerformed(evt);
            }
        });

        jA1Label3.setText("Username:");

        jA1Combo1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "User", "Admin", " " }));

        jA1Label4.setText("Status:");

        jA1Combo2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enable", "Disable" }));

        javax.swing.GroupLayout jAdminLayout = new javax.swing.GroupLayout(jAdmin);
        jAdmin.setLayout(jAdminLayout);
        jAdminLayout.setHorizontalGroup(
            jAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jAdminLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jA1Welcome)
                .addGap(190, 190, 190))
            .addGroup(jAdminLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jAdminLayout.createSequentialGroup()
                        .addComponent(jA1Label2)
                        .addGap(58, 58, 58))
                    .addGroup(jAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jA1Label4)
                        .addComponent(jA1Label1))
                    .addComponent(jA1Label3, javax.swing.GroupLayout.Alignment.LEADING))
                .addGroup(jAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jAdminLayout.createSequentialGroup()
                        .addGroup(jAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jA1Combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jA1EmailField))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jAdminLayout.createSequentialGroup()
                        .addGroup(jAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jA1Label5)
                            .addComponent(jA1Combo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jAdminLayout.createSequentialGroup()
                        .addGroup(jAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jA1Update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jA1UserField, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jA1OK)
                        .addGap(24, 24, 24))))
        );
        jAdminLayout.setVerticalGroup(
            jAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAdminLayout.createSequentialGroup()
                .addComponent(jA1Welcome)
                .addGap(44, 44, 44)
                .addGroup(jAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jA1Label3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jA1UserField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jA1OK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jA1Label2)
                    .addComponent(jA1EmailField))
                .addGap(18, 18, 18)
                .addGroup(jAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jA1Label1)
                    .addComponent(jA1Combo1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jA1Label4)
                    .addComponent(jA1Combo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jA1Update)
                .addGap(47, 47, 47)
                .addComponent(jA1Label5)
                .addContainerGap(173, Short.MAX_VALUE))
        );

        jUserInterface.addTab("Admin", jAdmin);

        jCTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCTextField1KeyPressed(evt);
            }
        });

        jCTextArea1.setColumns(20);
        jCTextArea1.setRows(5);
        jScrollPane1.setViewportView(jCTextArea1);

        jCButton1.setText("Send");
        jCButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCButton1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jCRadioButton1);
        jCRadioButton1.setText("Server");
        jCRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jCRadioButton2);
        jCRadioButton2.setText("Client");
        jCRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCRadioButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("On/Off");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Chatbox");

        jLabel7.setText("<html><u>Client / Server</u></html>");

        jLabel2.setText("<html><u>Caesar/AES</u></html>");

        buttonGroup3.add(jCRadioButton3);
        jCRadioButton3.setText("Caesar");
        jCRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup3.add(jCRadioButton4);
        jCRadioButton4.setText("AES");
        jCRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCRadioButton4ActionPerformed(evt);
            }
        });

        jCTextArea2.setColumns(20);
        jCTextArea2.setRows(5);
        jScrollPane2.setViewportView(jCTextArea2);

        jCTextArea3.setColumns(20);
        jCTextArea3.setRows(5);
        jScrollPane3.setViewportView(jCTextArea3);

        jCTextArea4.setColumns(20);
        jCTextArea4.setRows(5);
        jScrollPane4.setViewportView(jCTextArea4);

        jLabel3.setText("Receive (Plaintext):");

        jLabel4.setText("Receive (Ciphertext)");

        jLabel8.setText("Send (Plaintext)");

        jLabel9.setText("Send (Ciphertext)");

        javax.swing.GroupLayout jUIChatLayout = new javax.swing.GroupLayout(jUIChat);
        jUIChat.setLayout(jUIChatLayout);
        jUIChatLayout.setHorizontalGroup(
            jUIChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jUIChatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jUIChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jUIChatLayout.createSequentialGroup()
                        .addGroup(jUIChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jUIChatLayout.createSequentialGroup()
                                .addGroup(jUIChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jUIChatLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(147, 393, Short.MAX_VALUE))
                            .addGroup(jUIChatLayout.createSequentialGroup()
                                .addComponent(jCTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                                .addGroup(jUIChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCButton1)
                                    .addComponent(jCRadioButton2)
                                    .addComponent(jCRadioButton1)
                                    .addComponent(jButton3)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCRadioButton3)
                                    .addComponent(jCRadioButton4))))
                        .addGap(14, 14, 14))
                    .addGroup(jUIChatLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jUIChatLayout.setVerticalGroup(
            jUIChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jUIChatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jUIChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCButton1)
                    .addComponent(jCTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addGroup(jUIChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jUIChatLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCRadioButton3)
                        .addGap(7, 7, 7)
                        .addComponent(jCRadioButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jUserInterface.addTab("Chat", jUIChat);

        jUILabel1.setText("New Email: ");

        jUILabel2.setText("Confirm Email: ");

        jUIEmailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jUIEmailFieldFocusLost(evt);
            }
        });

        jUIEmailField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jUIEmailField2FocusLost(evt);
            }
        });

        jUIButton.setText("Change Email");
        jUIButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUIButtonActionPerformed(evt);
            }
        });

        JUILabel3.setText("Current Email:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(JUILabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jUILabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jUILabel1, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jUILabel5)
                    .addComponent(jUIEmailField)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jUIEmailField2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jUILabel6))
                    .addComponent(jUILabel4)
                    .addComponent(jUIButton, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JUILabel3)
                    .addComponent(jUILabel4))
                .addGap(26, 26, 26)
                .addComponent(jUILabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUIEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jUILabel1))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jUILabel2)
                            .addComponent(jUIEmailField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jUILabel6)))
                .addGap(28, 28, 28)
                .addComponent(jUIButton)
                .addContainerGap(221, Short.MAX_VALUE))
        );

        jUISetting.addTab("Change Email", jPanel5);

        jUI1Label1.setText("Old Password: ");

        jUI1Label2.setText("New Password: ");

        jUI1Label3.setText("Confirm Password: ");

        jUI1PassField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jUI1PassField1FocusLost(evt);
            }
        });

        jUI1PassField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jUI1PassField2FocusLost(evt);
            }
        });

        jUI1PassField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jUI1PassField3FocusLost(evt);
            }
        });

        jUI1Button2.setText("Change Password");
        jUI1Button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUI1Button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jUI1Label3)
                    .addComponent(jUI1Label2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jUI1Label1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jUI1Label9)
                    .addComponent(jUI1Label7)
                    .addComponent(jUI1Label6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jUI1Button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jUI1PassField1)
                            .addComponent(jUI1PassField2, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(jUI1PassField3, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jUI1Label8)))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jUI1Label6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUI1Label1)
                    .addComponent(jUI1PassField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jUI1Label7)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUI1Label2)
                    .addComponent(jUI1PassField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUI1Label3)
                    .addComponent(jUI1PassField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jUI1Label8))
                .addGap(18, 18, 18)
                .addComponent(jUI1Button2)
                .addGap(18, 18, 18)
                .addComponent(jUI1Label9)
                .addContainerGap(227, Short.MAX_VALUE))
        );

        jUISetting.addTab("Change Password", jPanel1);

        jUI2Label1.setText("First Name: ");

        jUI2Label2.setText("Last Name: ");

        jUI2Button1.setText("Change Name");
        jUI2Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUI2Button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jUI2Label2)
                    .addComponent(jUI2Label1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jUI2Label3, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(jUI2Button1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(jUI2TextField1)
                    .addComponent(jUI2TextField2))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jUI2Label1)
                    .addComponent(jUI2TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUI2Label2)
                    .addComponent(jUI2TextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jUI2Button1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jUI2Label3)
                .addContainerGap(255, Short.MAX_VALUE))
        );

        jUISetting.addTab("Change Details", jPanel3);

        jUserInterface.addTab("Settings", jUISetting);

        jButton1.setText("Logoff");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jButton1)
                .addGap(40, 40, 40)
                .addComponent(jButton2)
                .addContainerGap(195, Short.MAX_VALUE))
        );

        jUserInterface.addTab("Exit", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLogin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLogin2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jRPass1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jRPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(48, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSignup2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(jUserInterface)
                    .addGap(12, 12, 12)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLogin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLogin2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jRPass1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(111, 111, 111)
                    .addComponent(jRPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(171, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSignup2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(97, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jUserInterface, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(69, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jL1NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jL1NextActionPerformed
        String userData = null;
        String secureImage = null;
        String secureWord = null;
        userData = valUser(jL1UserField.getText());
                
        if (userData.equals(jL1UserField.getText())){                          
            secureImage = valSecureImage(userData);
            secureWord = valSecureWord(userData);
            jL2Label.setText(userData);
            jL2Label2.setText(secureWord);
            jL2Image.setIcon(new javax.swing.ImageIcon(getClass().getResource(secureImage)));
            jL1UserField.setText("");            
            jLogin1.setVisible(false);
            jLogin2.setVisible(true);
            jL2PassField.requestFocus();
            System.out.println(valPasswordDate(userData));
            if((valPasswordDate(userData)) > 30){
                jL2Label3.setText("Password expired");
                jL2Login.setVisible(false);
            }else {                
                jL2Label3.setText("Password days left: " + (30 - valPasswordDate(userData)));
                jL2Login.setVisible(true);
            }
            
        } else {
            jL1Warning.setText("Username not found");
            jL1UserField.setText("");
            jL1UserField.requestFocus();
        }    
    }//GEN-LAST:event_jL1NextActionPerformed

    private void jL1SignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jL1SignupActionPerformed
        
        jLogin1.setVisible(false);
        jSignup2.setVisible(true);
        jS2PassVal.setText("");
        jS2Val.setText("");
        jS2Secure.setText("");
        jS2EmailVal.setText("");
        jS2Status.setText("");
//        Sendemail("jtliew_91@hotmail.com");
    }//GEN-LAST:event_jL1SignupActionPerformed

    private void jL2FPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jL2FPassActionPerformed
        jL2PassField.setText("");
        jRPEmailField.requestFocus();
        jLogin2.setVisible(false);
        jRPass1.setVisible(true);
    }//GEN-LAST:event_jL2FPassActionPerformed

    private void jL2LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jL2LoginActionPerformed
//        System.out.println(jL2Label.getText());        

        if (jL2PassField.getText().equals("")){
            jL2Label3.setText("Empty");
        }else{ 
            loginFlag = newLogin(jL2Label.getText(), jL2PassField.getText());
//            System.out.println(loginFlag);
            if (loginFlag == 1){                
                jA1Welcome.setText("Welcome " + jL2Label.getText());
                setTitle("SecureSystem - Admin - " + jL2Label.getText());
               
                session = jL2Label.getText();
                sessionEmail = valAdmin(session, 1);
                System.out.println(sessionEmail);
                log(session, 1);
                jUILabel4.setText(sessionEmail);                
                jL2PassField.setText("");
                jL2Label.setText("");
                jL2Label2.setText("");
                jUserInterface.setVisible(true);                                
                jLogin2.setVisible(false);
              
                
            }else if(loginFlag == 2){
                setTitle("SecureSystem - User - " + jL2Label.getText());
                session = jL2Label.getText();
                sessionEmail = valAdmin(session, 1);
                log(session, 1);
                jL2PassField.setText("");
                jL2Label.setText("");
                jL2Label2.setText("");
                jUserInterface.setVisible(true);
                jUserInterface.remove(jAdmin);
                jLogin2.setVisible(false);
            }else if(loginFlag == 3){
                jL2Login.setVisible(false);
                jL2Warning.setText("<html>Account disabled. <br>"
                        + "Please contact the admin</html>");
            }else {
                if(oldPassDate(jL2Label.getText(), hashPass(jL2PassField.getText())) >= 0){
                    jL2Warning.setText("Your password change " + oldPassDate(jL2Label.getText(), hashPass(jL2PassField.getText())) + " days ago.");
                }else{
                
                
                if(loginLimit > 2){
                    jL2Warning.setText("<html>Wrong Password!<br>You have exceeded 3 times trying to log in. <br>Try again later</html>");
                    jL2Login.setEnabled(false);
                }else {
                    loginLimit = loginLimit + 1;
                    jL2Warning.setText("<html>Wrong Password!<br> You have "+ (4 - loginLimit) +" tries remaining.</html>");                    
                    System.out.println(loginLimit);
                }
                }
            }
        }
    }//GEN-LAST:event_jL2LoginActionPerformed

    private void jRPokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRPokActionPerformed
        if (jRPTACField.getText().equals(tac)){
        jRPass1.setVisible(false);
        jRPass2.setVisible(true);
        }else{
            jRPLabel4.setText("Wrong TAC!");
            jRPTACField.setText("");
            jRPTACField.requestFocus();
        }
        
    }//GEN-LAST:event_jRPokActionPerformed

    private void jRPRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRPRequestActionPerformed
        String rPassEmail = null;
        rPassEmail = valEmail(jRPEmailField.getText(), 1); 
        if (rPassEmail.equals(jRPEmailField.getText())){
            tac = sendEmail(jRPEmailField.getText());
            jRPLabel4.setText("TAC sent to " + jRPEmailField.getText());
            jRPLabel3.setVisible(true);
            jRPTACField.setVisible(true);
            jRPok.setVisible(true);
            tacFlag2++;
            if (tacFlag2 > 3){
                jRPLabel4.setText("You have requested TAC for more than 3 times, please try again later");
                jRPRequest.setEnabled(false);
            }
        } else {
            jRPLabel4.setText("Incorrect Email");
            jRPLabel3.setVisible(false);
            jRPTACField.setVisible(false);
            jRPok.setVisible(false);
        }      
    }//GEN-LAST:event_jRPRequestActionPerformed

    private void jL2BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jL2BackActionPerformed
        jL2PassField.setText("");
        jL2Label3.setText("");
        jL2Warning.setText("");
        jL1UserField.requestFocus();
        jLogin1.setVisible(true);
        jLogin2.setVisible(false);        
    }//GEN-LAST:event_jL2BackActionPerformed

    private void jRPBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRPBackActionPerformed
        jRPEmailField.setText("");
        jRPTACField.setText("");
        jRP2NewPassField.setText("");
        jRP2ConfirmPassField.setText("");
        jRPok.setVisible(false);
        jRPTACField.setVisible(false);
        jRPass1.setVisible(false);        
        jLogin1.setVisible(true);
    }//GEN-LAST:event_jRPBackActionPerformed

    private void jL1FPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jL1FPassActionPerformed
        jL1UserField.setText("");
        jLogin1.setVisible(false);
        jRPass1.setVisible(true);
    }//GEN-LAST:event_jL1FPassActionPerformed

    private void jRP2ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRP2ResetActionPerformed
        secure = valSecurePass(jRP2NewPassField.getText());
        int passtime = oldPassDate(jRPEmailField.getText(), hashPass(jUI1PassField2.getText()));
        if(jRP2NewPassField.getText().equals(jRP2ConfirmPassField.getText()) && secure == true && passtime <= 0){
            upOldPass(jRPEmailField.getText());
            changeNewPass(hashPass(jRP2NewPassField.getText()), jRPEmailField.getText());           
            jLogin1.setVisible(true);
            jRPass2.setVisible(false);
            jRPEmailField.setText("");
            jRPTACField.setText("");
            jRP2NewPassField.setText("");
            jRP2ConfirmPassField.setText("");
            jL1UserField.requestFocus();
            jL1Warning.setText("Password Changed.");
        }
        else if(jRP2NewPassField.getText().equals(jRP2ConfirmPassField.getText()) && secure == false){
            jRP2Label4.setText("Password must contain at least "
                + "\nan uppercase letter, a lowercase letter, "
                + "\na digit and a special character.");
            jRP2Label4.setForeground(Color.red);
            jRP2NewPassField.requestFocus();        
        }
        else jRP2ConfirmPassField.requestFocus();
    }//GEN-LAST:event_jRP2ResetActionPerformed

    private void jRP2NewPassFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRP2NewPassFieldFocusLost
        secure = valSecurePass(jRP2NewPassField.getText());
        int passtime = oldPassEmailDate(jRPEmailField.getText(), hashPass(jUI1PassField2.getText()));
        System.out.println(passtime);
        if(secure == false) {
            jRP2Label4.setText(passReq);
        }
        else {
            jRP2Label4.setText("");
            if (passtime >= 0){                
                jRP2Label4.setText("<html>Your changed password " + passtime + " days ago,<br>Please use different password.</html>");
            }else jRP2Label4.setText("");
        }
    }//GEN-LAST:event_jRP2NewPassFieldFocusLost

    private void jS2RequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jS2RequestActionPerformed
        if (!(jS2EmailField.getText().equals("")) && tacFlag < 3){
            jS2EmailField.getText();
            tac = sendEmail(jS2EmailField.getText());
            jS2Status.setText("TAC sent to " + jS2EmailField.getText());
            tacFlag ++;            
        } else if(jS2EmailField.getText().equals("")){
            jS2Status.setText("Please insert Email Address");
            jS2Status.setForeground(Color.red);
            tacFlag ++;
        }else {
            jS2Status.setText("<html>You have requested more than 3 times, <br>Please try again later</html>");
            jS2Request.setEnabled(false);
        }
    }//GEN-LAST:event_jS2RequestActionPerformed

    private void jS2AppleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jS2AppleActionPerformed
        String imagePath = "/iss/css/images/apple.png";
        jS2SecureImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(imagePath)));
        sImage = 2;
    }//GEN-LAST:event_jS2AppleActionPerformed

    private void jS2LionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jS2LionActionPerformed
        String imagePath = "/iss/css/images/lion.jpg";
        jS2SecureImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(imagePath)));
        sImage = 1;
    }//GEN-LAST:event_jS2LionActionPerformed

    private void jS2PassField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jS2PassField2FocusLost
        if (jS2PassField2.getText().equals(jS2PassField1.getText()) && !(jS2PassField2.getText().equals(""))){
            jS2PassVal2.setText("Match");
            jS2PassVal2.setForeground(Color.blue);
            signUpflag[1] = true;
        }
        else if(jS2PassField2.getText().equals("")){
            jS2PassVal2.setText("Empty");
            jS2PassVal2.setForeground(Color.red);
            signUpflag[1] = false;
        }else{
            jS2PassVal2.setText("Not Match");
            jS2PassVal2.setForeground(Color.red);
            signUpflag[1] = false;
        }
    }//GEN-LAST:event_jS2PassField2FocusLost

    private void jS2PassField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jS2PassField1FocusLost
        secure = valSecurePass(jS2PassField1.getText());
        if(secure == false) {
            jS2PassVal.setText(passReq);
            jS2PassVal.setForeground(Color.red);
            signUpflag[0] = false;
        }
        else {
            jS2PassVal.setText("");
            signUpflag[0] = true;
        }
    }//GEN-LAST:event_jS2PassField1FocusLost

    private void jS2EmailFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jS2EmailFieldFocusLost
        String emailData;
        emailData = valEmail(jS2EmailField.getText(), 1);

        if (!(emailData.equals(jS2EmailField.getText())) && !(jS2EmailField.getText().equals(""))){
            jS2EmailVal.setText("Available");
            jS2EmailVal.setForeground(Color.blue);
            signUpflag[2] = true;
        }
        else if(jS2EmailField.getText().equals("")){
            jS2EmailVal.setText("Empty");
            jS2EmailVal.setForeground(Color.red);
            signUpflag[2] = false;
        }else {
            jS2EmailVal.setText("Not Available");
            jS2EmailVal.setForeground(Color.red);
            signUpflag[2] = false;
        }
    }//GEN-LAST:event_jS2EmailFieldFocusLost

    private void jS2UserFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jS2UserFieldFocusLost
        String userData;
        userData = valUser(jS2UserField.getText());

        if (!(userData.equals(jS2UserField.getText())) && !(jS2UserField.getText().equals(""))){
            jS2Val.setText("Available");
            jS2Val.setForeground(Color.blue);
            signUpflag[3] = true;
        } else if (jS2UserField.getText().equals("")){
            jS2Val.setText("Empty");
            jS2Val.setForeground(Color.red);
            signUpflag[3] = false;
        }
        else {
            jS2Val.setText("Not Available");
            jS2Val.setForeground(Color.red);
            signUpflag[3] = false;
        }
    }//GEN-LAST:event_jS2UserFieldFocusLost

    private void jS2OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jS2OKActionPerformed
        
        if (signUpflag[0] && signUpflag[1] && signUpflag[2] && signUpflag[3] && signUpflag[4] && jS2TACField.getText().equals(tac)){

            if(checkFirstUser() == true){
              newUser(jS2UserField.getText(), jS2EmailField.getText(), 2, sImage, jS2SecureField.getText());  
              jL1Warning.setText("User created");
            }else{
              newUser(jS2UserField.getText(), jS2EmailField.getText(), 1, sImage, jS2SecureField.getText());
              jL1Warning.setText("Admin created");
            }
                        
            newPass(jS2EmailField.getText(), jS2PassField1.getText());           
            jS2UserField.setText("");
            jS2PassField1.setText("");
            jS2PassField2.setText("");
            jS2EmailField.setText("");
            jS2SecureField.setText("");
            jS2TACField.setText("");
            tac = null;
            jSignup2.setVisible(false);
            jLogin1.setVisible(true);
            jL1UserField.requestFocus();
            
            jL1Warning.setForeground(Color.blue);
        }else{
            jS2Status.setText("Wrong details inserted, please try again.");
            jS2Status.setForeground(Color.red);
            jS2PassField1.setText("");
            jS2PassField2.setText("");             
        }
    }//GEN-LAST:event_jS2OKActionPerformed

    private void jS2SecureFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jS2SecureFieldFocusLost
        if (jS2SecureField.getText().equals("")){
            jS2Secure.setText("Empty");
            jS2Secure.setForeground(Color.red);
            signUpflag[4] = false;
        }else signUpflag[4] = true;
        
    }//GEN-LAST:event_jS2SecureFieldFocusLost

    private void jS2BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jS2BackActionPerformed
        jLogin1.setVisible(true);
        jSignup2.setVisible(false);
        jS2UserField.setText("");
        jS2PassField1.setText("");
        jS2PassField2.setText("");
        jS2SecureField.setText("");
        jS2TACField.setText("");
        jL1UserField.requestFocus();
        tac = null;
    }//GEN-LAST:event_jS2BackActionPerformed

    private void jA1OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jA1OKActionPerformed
        jA1EmailField.setText(valAdmin(jA1UserField.getText(), 1));
        jA1Combo1.setSelectedItem(valAdmin(jA1UserField.getText(), 2));
        jA1Combo2.setSelectedItem(valAdmin(jA1UserField.getText(), 3));
    }//GEN-LAST:event_jA1OKActionPerformed

    private void jA1UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jA1UpdateActionPerformed
        changeAdmin(jA1EmailField.getText(), jA1Combo1.getSelectedItem().toString(), jA1Combo2.getSelectedItem().toString());
        jA1Label5.setText("User " + jA1UserField.getText() + " Updated");
    }//GEN-LAST:event_jA1UpdateActionPerformed

    private void jUIEmailFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUIEmailFieldFocusLost
        String emailData;
        emailData = valEmail(jUIEmailField.getText(), 1);

        if (!(emailData.equals(jUIEmailField.getText())) && !(jUIEmailField.getText().equals(""))){
            jUILabel5.setText("Available");
            jUILabel5.setForeground(Color.blue);
            changeEmailflag = true;
        }
        else if(jUIEmailField.getText().equals("")){
            jUILabel5.setText("Empty");
            jUILabel5.setForeground(Color.red);
            changeEmailflag = false;
        }else {
            jUILabel5.setText("Not Available");
            jUILabel5.setForeground(Color.red);
            changeEmailflag = false;
        }
    }//GEN-LAST:event_jUIEmailFieldFocusLost

    private void jUIEmailField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUIEmailField2FocusLost
        if(jUIEmailField.getText().equals(jUIEmailField2.getText())){
            jUILabel6.setText("Match");
            jUILabel6.setForeground(Color.blue);
        }else{
            jUILabel6.setText("Not Match");
            jUILabel6.setForeground(Color.red);
        }
    }//GEN-LAST:event_jUIEmailField2FocusLost

    private void jUIButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUIButtonActionPerformed
        if(jUIEmailField.getText().equals(jUIEmailField2.getText()) && changeEmailflag == true){
            jUILabel6.setText("");
            sessionEmail = jUIEmailField.getText();
            jUILabel4.setText(sessionEmail); 
            changeSettings(jUIEmailField.getText(), session, 1);
            jUIEmailField.setText("");
            jUIEmailField2.setText("");
        }else{
            jUILabel5.setText("Invalid Email");
        }
    }//GEN-LAST:event_jUIButtonActionPerformed

    private void jUI1PassField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUI1PassField1FocusLost
        if(newLogin(session, jUI1PassField1.getText()) == 0){
            jUI1Label6.setText("Wrong Password!");
            jUI1Label6.setForeground(Color.red);
            changePassflag[0] = false;
        }else{
            jUI1Label6.setText("");
            changePassflag[0] = true;
        }
    }//GEN-LAST:event_jUI1PassField1FocusLost

    private void jUI1PassField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUI1PassField2FocusLost
        secure = valSecurePass(jUI1PassField2.getText());
        int passtime = oldPassDate(session, hashPass(jUI1PassField2.getText()));
        if(secure == false) {
            jUI1Label7.setText(passReq);
            jUI1Label7.setForeground(Color.red);
            changePassflag[1] = false;
        }
        else {       
            if(passtime >= 0){
                jUI1Label7.setText("<html>Your changed password " + passtime + " days ago."
                        + "<br>Please use different password</html>");
                
                jUI1Label7.setForeground(Color.red);
                changePassflag[1] = false;
            }else{
                if((jUI1PassField1.getText().equals(jUI1PassField2.getText()))){
                    jUI1Label7.setText("Please choose a different password");
                    changePassflag[1] = false;
                }else{
                    jUI1Label7.setText("");
                    changePassflag[1] = true;  
                }       
            }
        }
    }//GEN-LAST:event_jUI1PassField2FocusLost

    private void jUI1PassField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUI1PassField3FocusLost
        if(jUI1PassField2.getText().equals(jUI1PassField3.getText())){
            jUI1Label8.setText("Match");
            jUI1Label8.setForeground(Color.blue);
        }else{
            jUI1Label8.setText("Not Match");
            jUI1Label8.setForeground(Color.red);
        }
    }//GEN-LAST:event_jUI1PassField3FocusLost

    private void jUI1Button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUI1Button2ActionPerformed
        if(jUI1PassField2.getText().equals(jUI1PassField3.getText()) 
                && changePassflag[0] == true && changePassflag[1] == true){
            changeSettings(hashPass((jUI1PassField2).getText()), session, 2);
            changeSettings(session, hashPass((jUI1PassField2).getText()), 5);
            jUI1Label9.setText("Password Updated");
            jUI1Label9.setForeground(Color.blue);
            jUI1PassField1.setText("");
            jUI1PassField2.setText("");
            jUI1PassField3.setText("");
            jUIEmailField.setText("");
            jUIEmailField2.setText("");
        }else{
            jUI1Label9.setText("Please try again");
            jUI1Label9.setForeground(Color.red);
        }
    }//GEN-LAST:event_jUI1Button2ActionPerformed

    private void jUI2Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUI2Button1ActionPerformed
        changeSettings(jUI2TextField1.getText(),session, 3);
        changeSettings(jUI2TextField2.getText(),session, 4);
        jUI2Label3.setText("Updated");
    }//GEN-LAST:event_jUI2Button1ActionPerformed

    private void jRP2BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRP2BackActionPerformed
        jRPass2.setVisible(false);
        jLogin1.setVisible(true);
        jRP2NewPassField.setText("");
        jRP2ConfirmPassField.setText("");
        jRPEmailField.setText("");
        jRPTACField.setText("");
        
        jL1UserField.requestFocus();
        session = null;
        sessionEmail = null;
    }//GEN-LAST:event_jRP2BackActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        log(session, 2);
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCRadioButton1ActionPerformed
        clientserver = 0;
    }//GEN-LAST:event_jCRadioButton1ActionPerformed

    private void jCRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCRadioButton2ActionPerformed
        clientserver = 1;
    }//GEN-LAST:event_jCRadioButton2ActionPerformed

    private void jCButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCButton1ActionPerformed
        if(!(jCTextField1.getText().equals(""))){
            st = 1;            
            jCTextField1.requestFocus();
            jCTextArea1.setCaretPosition(jCTextArea1.getDocument().getLength());
            }
    }//GEN-LAST:event_jCButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            dosomething();
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCTextField1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(!(jCTextField1.getText().equals(""))){
            st = 1;            
            jCTextField1.requestFocus();
            jCTextArea1.setCaretPosition(jCTextArea1.getDocument().getLength());
            }
        }
    }//GEN-LAST:event_jCTextField1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        log(session, 2);
        setTitle("Secure System");
        jUserInterface.setVisible(false);
        jLogin1.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jS2PassField1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jS2PassField1MouseEntered
        jS2PassField1.setToolTipText(passReq);
    }//GEN-LAST:event_jS2PassField1MouseEntered

    private void jRP2ConfirmPassFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRP2ConfirmPassFieldFocusLost
        if(jRP2ConfirmPassField.getText().equals(jRP2NewPassField.getText())){
            jRP2Label5.setText("Match");
            jRP2Label5.setForeground(Color.blue);
        }else{
            jRP2Label5.setText("Not Match");
            jRP2Label5.setForeground(Color.red);
        }
    }//GEN-LAST:event_jRP2ConfirmPassFieldFocusLost

    private void jCRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCRadioButton3ActionPerformed
        encmethod = 0;
    }//GEN-LAST:event_jCRadioButton3ActionPerformed

    private void jCRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCRadioButton4ActionPerformed
        encmethod = 1;
    }//GEN-LAST:event_jCRadioButton4ActionPerformed

    private void jL1UserFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jL1UserFieldKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jL1Warning.setText("<html>Enter key has been disabled,<br> please use the Next button</html>");
        }
    }//GEN-LAST:event_jL1UserFieldKeyPressed

    private void jL2PassFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jL2PassFieldKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jL2Warning.setText("<html>Enter key has been disabled,<br> please use the Next button</html>");
        }
    }//GEN-LAST:event_jL2PassFieldKeyPressed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        String imagePath = "/iss/css/images/grenade.png";
        jS2SecureImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(imagePath)));
        sImage = 3;
    }//GEN-LAST:event_jRadioButton1ActionPerformed
    
    private void dosomething() throws Exception{                                                 
       
        if(chatonoff == 0){
            chatonoff = 1;
            jCRadioButton1.setEnabled(false);
            jCRadioButton2.setEnabled(false);
            jCRadioButton3.setEnabled(false);
            jCRadioButton4.setEnabled(false);
        }else{ chatonoff = 0;
        jCRadioButton1.setEnabled(true);
            jCRadioButton2.setEnabled(true);
            jCRadioButton3.setEnabled(true);
            jCRadioButton4.setEnabled(true);;
        }
    
        
        if(clientserver == 1 && chatonoff == 1){
            jLabel5.setText("Client ON \n");
            Socket mysock = new Socket("127.0.0.1", 12345);
            DataInputStream input = new DataInputStream(mysock.getInputStream());
            DataOutputStream output = new DataOutputStream(mysock.getOutputStream());
            st = 1;
            sendC = new sendThread(output);
            recvC = new recvThread(input);

            sendC.start();
            recvC.start();
        }else if(clientserver == 0 && chatonoff == 1){

            jLabel5.setText("Server ON \n");
            ServerSocket mysock = new ServerSocket(12345);
            Socket mylink = mysock.accept();
            jLabel6.setText("New Client Connected. \n");

            DataInputStream input = new DataInputStream(mylink.getInputStream());
            DataOutputStream output = new DataOutputStream(mylink.getOutputStream());
            st = 1;
            sendS = new sendThread(output);
            recvS = new recvThread(input);
            
            sendS.start();
            recvS.start();
        }else if(clientserver == 1 && chatonoff == 0){
            jLabel5.setText("Client OFF \n");
            jLabel6.setText("");
            jCTextArea1.setText("");
            sendS = null;
            recvS = null;
            chatswitch = false;
            
        }else if(clientserver == 0 && chatonoff == 0){
            jLabel5.setText("Server OFF \n");
            jLabel6.setText("");
            jCTextArea1.setText("");
            sendS = null;
            recvS = null;
            chatswitch = false;
            
        }       
    }                       
    
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
 
        new Login();
    }
    
    class sendThread extends Thread
{
	private DataOutputStream outstring;
	public sendThread(DataOutputStream output)
	{
		outstring = output;
	}

        public void run()
	{
		chatswitch = true;
                boolean start = false;
                
                String sndTXT;
//		Scanner inp = new Scanner(System.in);
		do
		{
			try
			{
				if(st == 1 && start == true && encmethod == 0){                                    
                                sndTXT=session + " > " + jCTextField1.getText() + "\n";
				jCTextArea1.append(sndTXT);
                                chatlog(session, sender, session, sndTXT);
                                System.out.println("Encryted text(Caesar) sent: " + encCaesar(sndTXT, 12));
                                outstring.writeUTF(encCaesar(sndTXT, 12));                               
                                jCTextField1.setText("");
                                st = 0;
                                }else if(st == 1 && start == true && encmethod == 1){                                    
                                    sndTXT=session + " > " + jCTextField1.getText() + "\n";
                                    jCTextArea1.append(sndTXT);
                                    chatlog(session, sender, session, sndTXT);
                                    System.out.println("Encryted text(AES) sent: " + AES.encrypt(sndTXT));
                                    
                                    outstring.writeUTF(AES.encrypt(sndTXT));
                                    jCTextField1.setText("");
                                st = 0;
                                }else if (st == 1 && start == false && encmethod == 0) {
                                   sndTXT ="---" + session + " is online. ---\n"
                                        + "------------\n";
                                    outstring.writeUTF(encCaesar(sndTXT, 12));
                                    outstring.writeUTF(encCaesar(session, 12));
                                    st = 0;
                                    start = true;                                    
                                }else if(st == 1 && start == false && encmethod == 1){
                                    sndTXT ="---" + session + " is online. ---\n"
                                              + "------------\n";
                                    outstring.writeUTF(AES.encrypt(sndTXT));
                                    outstring.writeUTF(AES.encrypt(session));
                                    st = 0;
                                    start = true; 
                                }
			}catch(IOException e){} 
                        catch (Exception ex) {
                            jCTextArea1.setText("");
                            jCTextArea1.append("ERROR!");
                    }
		}while(chatswitch);
	}
}
    
class recvThread extends Thread 
{
	private boolean x = true;
        private DataInputStream instring;
	public recvThread(DataInputStream input)
	{
		instring = input;
	}

        @Override
	public void run()
	{
		String rcvTXT;
                String rcvCTXT;
                
                boolean start = false;
                chatswitch = true;
		do
		{
			try
			{
                            if(encmethod == 0 && start == true){
                                rcvCTXT=instring.readUTF();
                                System.out.println("Encrypted text(Caesar) received: " + rcvCTXT);
                                rcvTXT = decCaesar(rcvCTXT, 12);
				chatlog(session, sender, sender, rcvTXT);
                                jCTextArea1.append(rcvTXT);
                                jCTextArea1.setCaretPosition(jCTextArea1.getDocument().getLength());
                            }else if(encmethod == 1 && start == true){
                                rcvCTXT=instring.readUTF();
                                System.out.println("Encrypted text(AES) received: " + rcvCTXT);
                                rcvTXT = AES.decrypt(rcvCTXT);
                                chatlog(session, sender, sender, rcvTXT);
				jCTextArea1.append(rcvTXT);
                                jCTextArea1.setCaretPosition(jCTextArea1.getDocument().getLength());
                            }else if(encmethod == 0 && start == false){
                                rcvCTXT=instring.readUTF();
                                sender=instring.readUTF();
                                System.out.println("Encrypted text(Caesar) received: " + rcvCTXT);
                                rcvTXT = decCaesar(rcvCTXT, 12);
                                sender = decCaesar(sender, 12);
                                jCTextArea1.append(chathistory(session, sender));
				jCTextArea1.append(rcvTXT);
                                jCTextArea1.setCaretPosition(jCTextArea1.getDocument().getLength());
                                start = true;
                            }else if(encmethod == 1 && start == false){
                                rcvCTXT=instring.readUTF();
                                sender=instring.readUTF();
                                System.out.println("Encrypted text(AES) received: " + rcvCTXT);
                                sender = AES.decrypt(sender);
                                rcvTXT = AES.decrypt(rcvCTXT);
                                jCTextArea1.append(chathistory(session, sender));
				jCTextArea1.append(rcvTXT);
                                jCTextArea1.setCaretPosition(jCTextArea1.getDocument().getLength());
                                start = true;
                            }    
			}catch(IOException e){} 
                        catch (Exception ex) {
                            jCTextArea1.setText("");
                            jCTextArea1.append("ERROR!");
                    }
		}while(chatswitch);
	}
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JUILabel3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox jA1Combo1;
    private javax.swing.JComboBox jA1Combo2;
    private javax.swing.JLabel jA1EmailField;
    private javax.swing.JLabel jA1Label1;
    private javax.swing.JLabel jA1Label2;
    private javax.swing.JLabel jA1Label3;
    private javax.swing.JLabel jA1Label4;
    private javax.swing.JLabel jA1Label5;
    private javax.swing.JButton jA1OK;
    private javax.swing.JButton jA1Update;
    private javax.swing.JTextField jA1UserField;
    private javax.swing.JLabel jA1Welcome;
    private javax.swing.JPanel jAdmin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jCButton1;
    private javax.swing.JRadioButton jCRadioButton1;
    private javax.swing.JRadioButton jCRadioButton2;
    private javax.swing.JRadioButton jCRadioButton3;
    private javax.swing.JRadioButton jCRadioButton4;
    private javax.swing.JTextArea jCTextArea1;
    private javax.swing.JTextArea jCTextArea2;
    private javax.swing.JTextArea jCTextArea3;
    private javax.swing.JTextArea jCTextArea4;
    private javax.swing.JTextField jCTextField1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JButton jL1FPass;
    private javax.swing.JButton jL1Next;
    private javax.swing.JButton jL1Signup;
    private javax.swing.JLabel jL1Text;
    private javax.swing.JLabel jL1Title;
    private javax.swing.JTextField jL1UserField;
    private javax.swing.JLabel jL1Warning;
    private javax.swing.JButton jL2Back;
    private javax.swing.JButton jL2FPass;
    private javax.swing.JLabel jL2Image;
    private javax.swing.JLabel jL2Label;
    private javax.swing.JLabel jL2Label2;
    private javax.swing.JLabel jL2Label3;
    private javax.swing.JButton jL2Login;
    private javax.swing.JPasswordField jL2PassField;
    private javax.swing.JLabel jL2Text1;
    private javax.swing.JLabel jL2Text2;
    private javax.swing.JLabel jL2Text3;
    private javax.swing.JLabel jL2Title;
    private javax.swing.JLabel jL2Warning;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jLogin1;
    private javax.swing.JPanel jLogin2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JToggleButton jRP2Back;
    private javax.swing.JPasswordField jRP2ConfirmPassField;
    private javax.swing.JLabel jRP2Label1;
    private javax.swing.JLabel jRP2Label2;
    private javax.swing.JLabel jRP2Label3;
    private javax.swing.JLabel jRP2Label4;
    private javax.swing.JLabel jRP2Label5;
    private javax.swing.JPasswordField jRP2NewPassField;
    private javax.swing.JButton jRP2Reset;
    private javax.swing.JButton jRPBack;
    private javax.swing.JTextField jRPEmailField;
    private javax.swing.JLabel jRPLabel1;
    private javax.swing.JLabel jRPLabel2;
    private javax.swing.JLabel jRPLabel3;
    private javax.swing.JLabel jRPLabel4;
    private javax.swing.JButton jRPRequest;
    private javax.swing.JTextField jRPTACField;
    private javax.swing.JPanel jRPass1;
    private javax.swing.JPanel jRPass2;
    private javax.swing.JButton jRPok;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jS2Apple;
    private javax.swing.JButton jS2Back;
    private javax.swing.JTextField jS2EmailField;
    private javax.swing.JLabel jS2EmailVal;
    private javax.swing.JLabel jS2Label1;
    private javax.swing.JLabel jS2Label2;
    private javax.swing.JLabel jS2Label3;
    private javax.swing.JLabel jS2Label4;
    private javax.swing.JLabel jS2Label5;
    private javax.swing.JLabel jS2Label6;
    private javax.swing.JLabel jS2Label7;
    private javax.swing.JLabel jS2Label8;
    private javax.swing.JRadioButton jS2Lion;
    private javax.swing.JButton jS2OK;
    private javax.swing.JPasswordField jS2PassField1;
    private javax.swing.JPasswordField jS2PassField2;
    private javax.swing.JLabel jS2PassVal;
    private javax.swing.JLabel jS2PassVal2;
    private javax.swing.JButton jS2Request;
    private javax.swing.JLabel jS2Secure;
    private javax.swing.JTextField jS2SecureField;
    private javax.swing.JLabel jS2SecureImage;
    private javax.swing.JLabel jS2Status;
    private javax.swing.JTextField jS2TACField;
    private javax.swing.JTextField jS2UserField;
    private javax.swing.JLabel jS2Val;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jSignup2;
    private javax.swing.JButton jUI1Button2;
    private javax.swing.JLabel jUI1Label1;
    private javax.swing.JLabel jUI1Label2;
    private javax.swing.JLabel jUI1Label3;
    private javax.swing.JLabel jUI1Label6;
    private javax.swing.JLabel jUI1Label7;
    private javax.swing.JLabel jUI1Label8;
    private javax.swing.JLabel jUI1Label9;
    private javax.swing.JPasswordField jUI1PassField1;
    private javax.swing.JPasswordField jUI1PassField2;
    private javax.swing.JPasswordField jUI1PassField3;
    private javax.swing.JButton jUI2Button1;
    private javax.swing.JLabel jUI2Label1;
    private javax.swing.JLabel jUI2Label2;
    private javax.swing.JLabel jUI2Label3;
    private javax.swing.JTextField jUI2TextField1;
    private javax.swing.JTextField jUI2TextField2;
    private javax.swing.JButton jUIButton;
    private javax.swing.JPanel jUIChat;
    private javax.swing.JTextField jUIEmailField;
    private javax.swing.JTextField jUIEmailField2;
    private javax.swing.JLabel jUILabel1;
    private javax.swing.JLabel jUILabel2;
    private javax.swing.JLabel jUILabel4;
    private javax.swing.JLabel jUILabel5;
    private javax.swing.JLabel jUILabel6;
    private javax.swing.JTabbedPane jUISetting;
    private javax.swing.JTabbedPane jUserInterface;
    // End of variables declaration//GEN-END:variables
}


