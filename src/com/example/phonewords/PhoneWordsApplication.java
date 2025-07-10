package com.example.phonewords;

import java.io.IOException;

public class PhoneWordsApplication {
    public static void main(String[] args) throws IOException {
        CommandLineProcessor processor = new CommandLineProcessor();
        processor.process(args);
    }
}
