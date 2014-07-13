A demonstration of a secure application 
1) Password hashing with SHA-1 
2) SQL injection prevention with Prepared Statement 
3) Access Control for different users (e.g. Admin/User) 
4) Double authentication using Email 
5) Password strength requirement 
7) User log 
8) Forgot password 
9) Password will expire 
10) Prohibit the usage of old passwords 
11) Phishing prevention by using SecureImage and SecureWord 
12) Encryption using Caesar Cipher and AES 
13) Chat system log 
14) User and email duplication prevention 
15) Disable Enter key to prevent keylogging 
16) Password confirmation 
17) TAC request limit

How to use
1) Install MySQL
2) Load css.sql into your MySQL database ==> "mysql -u root -p css < css.sql"
3) Insert email and your email password in src/Email.txt
4) Optional - insert your server IP address for MySQL/Chat
5) Build and Run Project

Recommendation
Use vagrant to host MySQL database