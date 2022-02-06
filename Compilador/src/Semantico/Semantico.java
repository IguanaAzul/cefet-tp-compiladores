package Semantico;

import Utils.Token;
import Utils.Word;

import java.util.ArrayList;
import java.util.Hashtable;



public class Semantico {
    private final Hashtable<Token, Integer> tabelaSimbolos = new Hashtable<>();


    private int assignType;
    private final Hashtable<String, Word> words;

    public Semantico(Hashtable<String, Word> words) {
        this.words = words;
    }

    public void appendToSymbolTable(ArrayList<Token> identifiers, int type) throws SemanticError{
        for (Token i: identifiers) {
            if (tabelaSimbolos.containsKey(i)){
                throw new SemanticError("Variable " + i + ", got declared twice", i.line);
            }
            tabelaSimbolos.put(i, type);
        }
    }

    public void checkType(Token identifier) {
    }
}


//    Checar se o tipo atribuido é válido.
//    Checar reuso de variável.
//    Checar se a variável atribuída existe.
//    Checar os tipos na operação.
