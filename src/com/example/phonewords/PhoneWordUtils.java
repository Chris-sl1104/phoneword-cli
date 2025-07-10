package com.example.phonewords;

import java.util.*;

public class PhoneWordUtils {
    public static Map<Character, Character> getCharToDigitMap() {
        Map<Character, Character> charToDigit = new HashMap<>();
        Map<Character, String> digitToChars = getDigitToCharsMap();
        for (Map.Entry<Character, String> e : digitToChars.entrySet()) {
            for (char c : e.getValue().toCharArray()) {
                charToDigit.put(c, e.getKey());
            }
        }
        return charToDigit;
    }

    public static Map<Character, String> getDigitToCharsMap() {
        Map<Character, String> digitToChars = new HashMap<>();
        digitToChars.put('2', "ABC");
        digitToChars.put('3', "DEF");
        digitToChars.put('4', "GHI");
        digitToChars.put('5', "JKL");
        digitToChars.put('6', "MNO");
        digitToChars.put('7', "PQRS");
        digitToChars.put('8', "TUV");
        digitToChars.put('9', "WXYZ");
        return digitToChars;
    }

    public static String sanitizeDigits(String s) {
        return s.replaceAll("[^0-9]", "");
    }
}
