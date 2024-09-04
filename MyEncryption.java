/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.myencryption;

/**
 *
 * @author katbassett
 */
public class MyEncryption {
    private static final String PLAIN_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CIPHER_ALPHABET1 = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
    private static final String CIPHER_ALPHABET2 = "MNOPQRSTUVWXYZABCDEFGHIJKL";
    
    public static String encrypt(String plaintext) {
        String firstSubstitution = applySubstitution(plaintext, PLAIN_ALPHABET, CIPHER_ALPHABET1) ;
        String secondSubstitution = applySubstitution(firstSubstitution, PLAIN_ALPHABET, CIPHER_ALPHABET2);
        return secondSubstitution;
    }

    public static String applySubstitution(String text, String plainAlphabet, String cipherAlphabet) {
        StringBuilder ciphertext = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray() ) {
            if (Character.isLetter(c)) {
                int index = plainAlphabet.indexOf(c);
                ciphertext.append(index != -1 ? cipherAlphabet.charAt(index) : c);
            } else {
              ciphertext.append(c);  
            }
        }
        return ciphertext.toString();
    }
  
    public static void main(String[] args) {
        String original = " The quick brown fox jumps over the lazy dog";
        String encrypted = encrypt(original);
        
        System.out.println("Original: " + original);
        System.out.println("Encrypted: " + encrypted);
        
    }
}
