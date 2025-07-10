package com.example.phonewords;

import java.io.*;
import java.util.*;

public class DictionaryLoader {
    private static final Map<Character, Character> CHAR_TO_DIGIT = PhoneWordUtils.getCharToDigitMap();

    public Map<String, List<String>> loadDictionary(String path) throws IOException {
        Map<String, List<String>> digitSeqToWords = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String w;
            while ((w = br.readLine()) != null) {
                w = w.replaceAll("[^A-Za-z]", "").toUpperCase();
                if (w.isEmpty()) continue;
                String digits = wordToDigits(w);
                digitSeqToWords.computeIfAbsent(digits, k -> new ArrayList<>()).add(w);
            }
        }
        return digitSeqToWords;
    }

    private String wordToDigits(String word) {
        StringBuilder sb = new StringBuilder(word.length());
        for (char c : word.toCharArray()) {
            Character d = CHAR_TO_DIGIT.get(c);
            if (d == null) return "";
            sb.append(d);
        }
        return sb.toString();
    }
}
