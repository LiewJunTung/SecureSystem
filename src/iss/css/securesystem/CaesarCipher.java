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
public class CaesarCipher {

	public String decode(String enc, int offset) {
		return encode(enc, -offset);
	}

	public String encode(String enc, int offset) {
		offset = offset % 26 + 26;
		StringBuilder encoded = new StringBuilder();
		for (char i : enc.toLowerCase().toCharArray()) {
			if (Character.isLetter(i)) {
				int j = (i - 'a' + offset) % 26;
				encoded.append((char) (j + 'a'));
			} else {
				encoded.append(i);
			}
		}
		return encoded.toString();
	}
}