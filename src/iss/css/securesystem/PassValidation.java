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
public class PassValidation {
    public boolean printPasswordStats(String password) {
        int numOfSpecial = 0;
        int numOfLetters = 0;
        int numOfUpperLetters = 0;
        int numOfLowerLetters = 0;
        int numOfDigits = 0;

        byte[] bytes = password.getBytes();
        for (byte tempByte : bytes) {
            if (tempByte >= 33 && tempByte <= 47) {
                numOfSpecial++;
            }

            char tempChar = (char) tempByte;
            if (Character.isDigit(tempChar)) {
                numOfDigits++;
            }

            if (Character.isLetter(tempChar)) {
                numOfLetters++;
            }

            if (Character.isUpperCase(tempChar)) {
                numOfUpperLetters++;
            }

            if (Character.isLowerCase(tempChar)) {
                numOfLowerLetters++;
            }
        }

        if ((numOfSpecial < 1 || numOfUpperLetters < 1 || 
                numOfLowerLetters < 1 || numOfDigits < 1) && 
                ((numOfLetters + numOfDigits + numOfSpecial) < 5)){
            return false;
        } else {        
            return true;
    }
    }
}

