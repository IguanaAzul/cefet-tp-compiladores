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
	
    public boolean checkIdExists(Word identifier) {
	if this.words.containsKey(identifier.getLexeme()) {
	   return true;
	}
        return false;
    }

    public void checkAtbType(Word identifier, int type) {
	if (identifier.getTag() == type) {
		return true;
	}
	return false;
    }
}


//    Checar se o tipo atribuido é válido. ok
//    Checar reuso de variável. ok
//    Checar se a variável atribuída existe. ok
//    Checar os tipos na operação.
