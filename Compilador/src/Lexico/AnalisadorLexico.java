package Lexico;

import Utils.Token;
import Utils.Lexema;

import java.io.*;


public class AnalisadorLexico {
    private FileReader file;
    public int linha = 1;
    private char c = ' ';

    public AnalisadorLexico(String fileName) throws FileNotFoundException {
        try {
            file = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("O caminho inserido não existe!");
            throw e;
        }
    }

    public Token scan() throws IOException {
        return switch (c) {
            case ' ' -> new Token(1);
            default -> new Token(0);
        };
    }

}