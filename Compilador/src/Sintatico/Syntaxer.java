package Sintatico;

import java.util.ArrayList;

import Utils.Token;

public class Syntaxer {
    private final ArrayList<Token> tokenList;

    public Syntaxer(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public void scanCode() throws Exception {

        Validator validator = new Validator(tokenList);

        validator.validateProgram();

        System.out.println("Success!! The code is valid");
    }
}
