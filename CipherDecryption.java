
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cipherdecryption;

/**
 *
 * @author katbassett
 */
public class CipherDecryption {

    public static void main(String[] args) {
        String encryptedText = "NRMMV NVHY FD WVFSW";
         char[] map = "THEQUICKBROWNFXJMPSVLAZYDG".toCharArray();
         char[] chars = encryptedText.toCharArray();
         
         
         String decryptedText = decrypt(chars, map);
         System.out.println(decryptedText);
    }
   
   
    public static String decrypt(char[] chars, char[] map) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                for (int j = 0; j < map.length; j++) {
                    if (chars [i] == map[j]) {
                        chars[i] = (char) ('A' + j);
                        break;
                    }
                }
            }
        }
    for (int i = 0; i < chars.length; i++) {
        if (chars[i] !=' ') {
            chars[i] = (char) (((chars[i] - 'A') + 21) % 26 + 'A');
        }
    }
   
    return new String(chars);
}
    
}
