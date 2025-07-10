package com.example.phonewords;

import java.io.*;
import java.util.*;

public class CommandLineProcessor {
    public void process(String[] args) throws IOException {
        String dictPath = null;
        String numbersPath = null;

        for (int i = 0; i < args.length; i++) {
            if ("-dictionary".equalsIgnoreCase(args[i]) && i + 1 < args.length) {
                dictPath = args[++i];
            } else {
                numbersPath = args[i];
            }
        }

        if (dictPath == null) {
            System.err.println("Error: dictionary file must be specified with -dictionary <file>");
            System.exit(1);
        }

        DictionaryLoader dictionaryLoader = new DictionaryLoader();
        Map<String, List<String>> digitSeqToWords = dictionaryLoader.loadDictionary(dictPath);

        PhoneWordEncoder encoder = new PhoneWordEncoder(digitSeqToWords);

        try (BufferedReader reader = numbersPath == null ?
                new BufferedReader(new InputStreamReader(System.in)) :
                new BufferedReader(new FileReader(numbersPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = PhoneWordUtils.sanitizeDigits(line);
                if (line.isEmpty()) continue;
                List<String> encodings = encoder.encodeNumber(line);
                for (String e : encodings) {
                    System.out.println(e);
                }
            }
        }
    }
}
